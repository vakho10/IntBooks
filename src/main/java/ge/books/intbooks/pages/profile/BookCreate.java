package ge.books.intbooks.pages.profile;

import java.util.Calendar;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.InjectService;

import ge.books.intbooks.entities.Book;
import ge.books.intbooks.services.BookService;
import ge.books.intbooks.services.UserService;

public class BookCreate {

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
    
    @InjectService("UserService")
    private UserService userService;
    
    // The code
    void onPrepareForRender() throws Exception {
        // If fresh start, make sure there's a Person object available.
        if (form.isValid()) {
            book = new Book();
        }
    }

    void onPrepareForSubmit() throws Exception {
        // Instantiate a Person for the form data to overlay.
        book = new Book();
    }

    Object onCanceled() {
        return indexPage;
    }

    @CommitAfter
    void onValidateFromForm() {

//        if (person.getFirstName() != null && person.getFirstName().equals("Acme")) {
//            form.recordError("First Name must not be Acme.");
//        }

        if (form.getHasErrors()) {
            return;
        }

        try {
        	book.setPublisher(userService.getCurrentUser());
        	book.setPostDate(Calendar.getInstance().getTime());
            bookService.createBook(book, null);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            form.recordError(e.getMessage());
            e.printStackTrace();
        }
    }

    Object onSuccess() {
        return indexPage;
    }

}
