package ge.books.intbooks.pages;

import org.apache.tapestry5.ioc.annotations.InjectService;

import ge.books.intbooks.services.UserService;

public class Logout {	

	@InjectService("UserService")
	private UserService userService;
	
	Object onActivate() {
		userService.logUserOut();
		return "index";
	}

}
