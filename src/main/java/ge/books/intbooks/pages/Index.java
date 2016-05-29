package ge.books.intbooks.pages;

import java.text.DateFormat;
import java.text.Format;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.services.BookService;
import ge.books.intbooks.services.UserService;

/**
 * Start page of application ibdb.
 */
public class Index {

	@Persist(PersistenceConstants.SESSION)
	@Property
	private List<Book> books;
	
	@Property
	private Book book;
	
	@Property
	private String pageNumber;
	
	@InjectService("UserService")
	private UserService userService;
	
	@InjectService("BookService")
	private BookService bookService;
	
	@Inject
    private Locale currentLocale;
	
	void onActivate(@RequestParameter(allowBlank = true, value = "page") Long page) {
		if (page == null || page < 1) {
			page = 1L;

		}
		pageNumber = String.valueOf(page);
		
		this.books = bookService.getAllBooks();
	}
	
	public Format getDateFormat() {
        return DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
    }
	
	public boolean isDataAvailable() {
		return books != null && books.size() > 0;
	}

}
