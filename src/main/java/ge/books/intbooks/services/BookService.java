package ge.books.intbooks.services;

import java.util.List;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.entities.BookReview;
import ge.books.intbooks.entities.Genre;
import ge.books.intbooks.entities.User;

public interface BookService {

	Book createBook(Book book, List<Genre> genres);
	Book getBookById(long bookId);
	List<Book> getAllBooks();	
	BookReview writeBookReview(long bookId, long userId, int rating, String text);
	boolean hasUserReviewedBook(User user, Book book);
	List<BookReview> getNonEmptyReviewsForBook(Book book);
	List<BookReview> getReviewsByUser(User user);
	BookReview getReviewById(long reviewId);
	BookReview getReviewForBookByUser(Book book, User user);
	void deleteReviewById(long reviewId);
	List<Genre> getAllGenres();
	void deleteBook(long bookId);
}
