package ge.books.intbooks.components;

import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;

import ge.books.intbooks.entities.Genre;
import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.BookService;
import ge.books.intbooks.services.UserService;

@Import(module = { "bootstrap/collapse", "bootstrap/dropdown" })
public class Layout {

	@Inject
	private ComponentResources resources;

	@InjectService("UserService")
	private UserService userService;

	@InjectService("BookService")
	private BookService bookService;
	
	/**
	 * The page title, for the <title> element and the
	 * <h1>element.
	 */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	@Parameter(required = false, value = "false")
	private Boolean sidebar;

	@Property
	private Genre genre;
	
	@Property
	private List<Genre> genres;
	
	void onActivate() {
		this.genres = bookService.getAllGenres();
	}
	
	public boolean isUserLoggedIn() {
		return userService.isUserLoggeIn();
	}

	public String getUsername() {
		User currentUser = userService.getCurrentUser();
		return currentUser.getUsername();
	}
	
	public boolean isSidebar() {
		return sidebar;
	}
}
