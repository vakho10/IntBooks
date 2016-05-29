package ge.books.intbooks.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ge.books.intbooks.entities.User;
import ge.books.intbooks.services.UserService;

public class UserServiceImpl implements UserService {

	@Inject
	private RequestGlobals requestGlobals;

	@Inject
	private Session session;

	@Override
	public User registerUser(User user) {
		if (isUserWithUsernameAndPasswordRegistered(user.getUsername(), user.getPassword())) {
			return null;
		}
		session.save(user); // Save new user in database
		return user;
	}

	@Override
	public boolean isUserWithUsernameRegistered(String username) {
		return isUserWithUsernameAndPasswordRegistered(username, null);
	}

	@Override
	public boolean isUserWithUsernameAndPasswordRegistered(String username, String password) {
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("username", username));

		if (password != null) {
			cr.add(Restrictions.eq("password", password));
		}

		@SuppressWarnings("unchecked")
		List<User> users = cr.list();

		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserLoggeIn() {
		HttpServletRequest httpServletRequest = requestGlobals.getHTTPServletRequest();
		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession == null || httpSession.getAttribute("user_object") == null) {
			return false;
		}
		return true;
	}

	@Override
	public void logUserOut() {
		HttpServletRequest httpServletRequest = requestGlobals.getHTTPServletRequest();
		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession == null) {
			return;
		}
		httpSession.invalidate();
	}

	@Override
	public User getCurrentUser() {
		HttpServletRequest httpServletRequest = requestGlobals.getHTTPServletRequest();
		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession != null) {
			User user = (User) httpSession.getAttribute("user_object");
			return user; // Might be null (which is OK)
		}
		return null;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("username", username));
		cr.add(Restrictions.eq("password", password));

		@SuppressWarnings("unchecked")
		List<User> userList = cr.list();

		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public long countUsers() {
		Number amount = (Number) session.createCriteria(User.class).setProjection(Projections.rowCount())
				.uniqueResult();
		return amount.longValue();
	}

	@Override
	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> userList = session.createCriteria(User.class).list();
		return userList;
	}

	@Override
	public List<User> getUsers(int pageNumber, int pageSize) {
		Criteria cr = session.createCriteria(User.class);
		cr.setFirstResult((pageNumber - 1) * pageSize);
		cr.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) cr.list();

		return users; // Can be null
	}

	@Override
	public User getUserById(long userId) {
		return (User) session.load(User.class, userId);
	}

}
