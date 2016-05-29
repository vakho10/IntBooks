package ge.books.intbooks.services;

import java.util.List;

import ge.books.intbooks.entities.User;

public interface UserService {

	User registerUser(User user);

	boolean isUserLoggeIn();

	boolean isUserWithUsernameRegistered(String username);

	boolean isUserWithUsernameAndPasswordRegistered(String username, String password);

	void logUserOut();

	User getCurrentUser();

	User getUserById(long userId);

	User getUserByUsernameAndPassword(String username, String password);

	long countUsers();

	List<User> getAllUsers();

	List<User> getUsers(int pageNumber, int pageSize);
}
