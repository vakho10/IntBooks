package ge.books.intbooks.pages;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.RequestGlobals;

import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.UserService;

public class Register {

	@InjectComponent
	private Form register;

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
	
	@CommitAfter
	void onValidateFromRegister() {
		
		boolean isRegistered = userService.isUserWithUsernameRegistered(usernameValue);
		if (isRegistered) {
			register.recordError("მსგ�?ვსი მ�?მხმ�?რებელი უკვე დ�?რეგისტრირებული�?!");
			return;
		}
		
		User user = new User();
		user.setIsAdmin(false);
		user.setUsername(usernameValue);
		user.setPassword(passwordValue);
		user.setRegDate(new Date());
		
		User regUser = userService.registerUser(user);
		if (regUser == null) {
			register.recordError("მ�?ხდ�? შედც�?მ�?! მ�?მხმ�?რებლის დ�?რეგისტრირებ�? ვერ მ�?ხერხდ�?.");
			return;
		}
		
		// Save registered user in session
		HttpServletRequest httpServletRequest = requestGlobals.getHTTPServletRequest();
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("user_object", regUser);		
	}

	Object onSuccessFromRegister() {		
		return "profile/index";
	}

	void onFailureFromRegister() {
		// Nothing...
	}

}
