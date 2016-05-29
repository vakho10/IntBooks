package ge.books.intbooks.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.entities.BookGenre;
import ge.books.intbooks.entities.BookReview;
import ge.books.intbooks.entities.Genre;
import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.BookService;
import ge.books.intbooks.services.UserService;

public class BookServiceImpl implements BookService {

	@Inject
	private Session session;

	@InjectService("UserService")
	private UserService userService;

	@Override
	public Book createBook(Book book, List<Genre> genres) {
		book.setPostDate(new Date());
		session.save(book);
		if (genres != null && genres.size() > 0) {
			for (Genre genre : genres) {
				BookGenre bookGenre = new BookGenre();
				bookGenre.setBook(book);
				bookGenre.setGenre(genre);
				session.save(bookGenre);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		return session.createCriteria(Book.class).addOrder(Order.desc("postDate")).list();
	}

	@Override
	public BookReview writeBookReview(long bookId, long userId, int rating, String text) {
		if (rating < 1 || rating > 10) {
			throw new RuntimeException("Rating can't exceed 10 or be less than 1!");
		}

		Book book = getBookById(bookId);
		User user = userService.getUserById(userId);

		BookReview review = getUserBookReview(user, book);
		if (review == null) {
			review = new BookReview();
			review.setBook(book);
			review.setUser(user);
			review.setRating(rating);
			review.setText(text);
			session.save(review);
		} else {
			review.setRating(rating);
			review.setText(text);
			session.update(review);
		}
		return review;
	}

	@Override
	public Book getBookById(long bookId) {
		return (Book) session.load(Book.class, bookId);
	}

	@Override
	public boolean hasUserReviewedBook(User user, Book book) {
		BookReview userBookReview = getUserBookReview(user, book);
		if (userBookReview != null) {
			return true;
		}
		return false;
	}

	public BookReview getUserBookReview(User user, Book book) {
		@SuppressWarnings("unchecked")
		List<BookReview> reviews = session.createCriteria(BookReview.class).add(Restrictions.eq("user", user))
				.add(Restrictions.eq("book", book)).list();
		if (reviews.size() > 0) {
			return reviews.get(0); // Should have one review for a single book!
		}
		return null;
	}

	@Override
	public List<BookReview> getNonEmptyReviewsForBook(Book book) {
		@SuppressWarnings("unchecked")
		List<BookReview> reviews = session.createCriteria(BookReview.class).add(Restrictions.eq("book", book)).list();
		if (reviews != null && reviews.size() > 0) {
			for (int i = reviews.size() - 1; i >= 0; i--) {
				if (reviews.get(i).getText() == null || reviews.get(i).getText().trim().equals("")) {
					reviews.remove(i);
				}
			}
		}
		return reviews;
	}

	@Override
	public List<BookReview> getReviewsByUser(User user) {
		@SuppressWarnings("unchecked")
		List<BookReview> bookReviews = session.createCriteria(BookReview.class).add(Restrictions.eq("user", user))
				.list();
		return bookReviews;
	}

	@Override
	public BookReview getReviewForBookByUser(Book book, User user) {
		@SuppressWarnings("unchecked")
		List<BookReview> reviews = session.createCriteria(BookReview.class).add(Restrictions.eq("user", user))
				.add(Restrictions.eqOrIsNull("book", book)).list();
		if (reviews == null || reviews.size() == 0) {
			return null;
		}
		return reviews.get(0);
	}

	@Override
	public void deleteReviewById(long reviewId) {
		BookReview review = getReviewById(reviewId);
		if (review == null) {
			return;
		}
		session.delete(review);
	}

	@Override
	public BookReview getReviewById(long reviewId) {
		@SuppressWarnings("unchecked")
		List<BookReview> list = session.createCriteria(BookReview.class).add(Restrictions.eq("id", reviewId)).list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> getAllGenres() {
		return (List<Genre>) session.createCriteria(Genre.class).list();
	}

	@Override
	public void deleteBook(long bookId) {
		Book book = getBookById(bookId);
		List<BookReview> reviews = session.createCriteria(BookReview.class).add(Restrictions.eq("book", book)).list();
		for (BookReview bookReview : reviews) {
			session.delete(bookReview);
		}
		List<BookGenre> genres = session.createCriteria(BookGenre.class).add(Restrictions.eq("book", book)).list();
		for (BookGenre bookGenre : genres) {
			session.delete(bookGenre);
		}
		session.delete(book);
	}

}
