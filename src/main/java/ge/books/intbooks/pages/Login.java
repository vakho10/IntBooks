package ge.books.intbooks.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.RequestGlobals;

import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.UserService;

public class Login {

	@InjectComponent
	private Form login;
	
	@InjectPage
	private Index index;

	@Property
	private String usernameValue;
	@Property
	private String passwordValue;

	@InjectService("UserService")
	private UserService userService;
	
	@Inject
	private RequestGlobals requestGlobals;
	
	void onValidateFromLogin() {		
		User user = userService.getUserByUsernameAndPassword(usernameValue, passwordValue);		
		if (user == null) {
			login.recordError("მ�?მხმ�?რებელი მსგ�?ვსი ს�?ხელით�? დ�? პ�?რ�?ლით �?რ �?რსებ�?ბს!");
			return;
		}
		
		// Save logged in user in session
		HttpServletRequest httpServletRequest = requestGlobals.getHTTPServletRequest();
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("user_object", user);
	}

	Object onSuccessFromLogin() {
		return "profile/index";
	}

	void onFailureFromLogin() {
		// Nothing...
	}

}
