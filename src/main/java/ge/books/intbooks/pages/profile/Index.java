package ge.books.intbooks.pages.profile;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.hibernate.Session;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.entities.BookReview;
import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.BookService;
import ge.books.intbooks.services.UserService;

public class Index {

	@Property
	@Persist(PersistenceConstants.FLASH)
	private String errorMessage;

	@Inject
	private AlertManager alertManager;

	@Inject
	private Session session;
	
	@InjectService("UserService")
	private UserService userService;

	@InjectService("BookService")
	private BookService bookService;

	@Property
	private BookReview review;

	@Property
	private List<BookReview> reviews;
	
	@Property
	private Book book;
	
	@Property
	private List<Book> books;	

	private User currentUser;

	void onActivate() {
		this.currentUser = userService.getCurrentUser();
		this.reviews = bookService.getReviewsByUser(currentUser);
		// alertManager.alert(Duration.SINGLE, Severity.INFO, "Greetings, " +
		// currentUser.getUsername());
	}

	public boolean isOneOrMoreReviewAvailable() {
		List<BookReview> reviewsByUser = bookService.getReviewsByUser(currentUser);
		if (reviewsByUser == null || reviewsByUser.size() == 0) {
			return false;
		}
		return true;
	}

	@CommitAfter
	Object onDeleteReview(long reviewId) {
		bookService.deleteReviewById(reviewId);
		return null;
	}

	public boolean isUserAdmin() {
		return currentUser.getIsAdmin();
	}
	
	void setupRender() {		
        books = bookService.getAllBooks();
    }

	@CommitAfter
    void onDelete(long id) {
        try {
        	bookService.deleteBook(id);
        }
        catch (Exception e) {            
            errorMessage = e.getMessage();
        }
    }
}
