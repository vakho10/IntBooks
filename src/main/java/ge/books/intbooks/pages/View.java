package ge.books.intbooks.pages;

import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.entities.BookReview;
import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.BookService;
import ge.books.intbooks.services.UserService;

/**
 * Start page of application ibdb.
 */
public class View {

	@InjectService("UserService")
	private UserService userService;

	@InjectService("BookService")
	private BookService bookService;

	@Inject
	private Locale currentLocale;

	private long bookId;

	@Property
	private Book book;

	@Property
	private BookReview bookReview;

	@Property
	private List<BookReview> bookReviews;

	@InjectComponent
	private Form reviewForm;

	@Property
	private String textareaValue;
	@Property
	private int ratingValue = 1;

	@CommitAfter
	void onValidateFromReviewForm() {
		String text = null;
		if (textareaValue != null) {
			if (!textareaValue.trim().equals("")) {
				text = textareaValue.trim();
			}
		}

		User currentUser = userService.getCurrentUser();
		BookReview review = bookService.writeBookReview(book.getId(), currentUser.getId(), ratingValue, text);
		if (review == null) {
			reviewForm.recordError("დ�?ფიქსირდ�? შეცდ�?მ�?! მიმ�?ხილვ�? ვერ გ�?იგზ�?ვნ�?.");
			return;
		}

	}

	void onSuccessFromReviewForm() {
		// Nothing...
	}

	void onFailureFromReviewForm() {
		// Nothing...
	}

	Object onActivate(long bookId) {
		this.bookId = bookId;
		this.book = bookService.getBookById(bookId);
		if (book == null) {
			return Index.class;
		}
		
		User currentUser = userService.getCurrentUser();
		if (currentUser != null) {
			BookReview reviewByUser = bookService.getReviewForBookByUser(book, currentUser);
			if (reviewByUser != null) {
				ratingValue = reviewByUser.getRating();
			}
		}
		

		this.bookReviews = bookService.getNonEmptyReviewsForBook(book);
		return null;
	}

	long onPassivate() {
		// Will force to save book id for page after form submition!!!!!
		return this.bookId;
	}

	public boolean isReviewsPresent() {
		if (bookReviews != null && bookReviews.size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isUserLoggedIn() {
		return userService.isUserLoggeIn();
	}

	public boolean getHasUserPostedReview() {
		User currentUser = userService.getCurrentUser();
		if (currentUser == null) {
			return false;
		}
		return bookService.hasUserReviewedBook(currentUser, book);
	}
}
