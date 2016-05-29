package ge.books.intbooks.pages.profile;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.hibernate.Session;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.services.BookService;

public class BookUpdate {

	// The activation context
    @Property
    private long bookId;

    // Screen fields
    @Property
    private Book book;

    // Other pages
    @InjectPage
    private Index indexPage;

    // Generally useful bits and pieces
    @InjectComponent
    private BeanEditForm form;

    @InjectService("BookService")
    private BookService bookService;
    
    @Inject
    private Session session;
    
    // The code
    void onActivate(long bookId) {
        this.bookId = bookId;
    }

    Long onPassivate() {
        return bookId;
    }

    void setupRender() {

        // We're doing this here instead of in onPrepareForRender() because person is used outside the form...

        // If fresh start, make sure there's a Person object available.

        if (form.isValid()) {
            book = bookService.getBookById(bookId);
            // Handle null person in the template.
        }

    }

    void onPrepareForSubmit() {

        // Get Person object for the form fields to overlay.
    	book = bookService.getBookById(bookId);

        if (book == null) {
            form.recordError("Person has been deleted by another process.");
            // Instantiate an empty person to avoid NPE in the BeanEditForm.
            book = new Book();
        }
    }

    Object onCanceled() {
        return indexPage;
    }

    @CommitAfter
    void onValidateFromForm() {
    	
        if (form.getHasErrors()) {
            return;
        }

        try {
            session.update(book);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            form.recordError(e.getMessage());
        }
    }

    Object onSuccess() {
        return indexPage;
    }

}
