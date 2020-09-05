package prasun.springboot.gateway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import prasun.springboot.gateway.api.security.JWTUtil;
import prasun.springboot.gateway.dao.IncorrectDaoOperation;
import prasun.springboot.gateway.dao.UserDao;
import prasun.springboot.gateway.entity.User;
import prasun.springboot.gateway.entity.UserPrincipal;
import prasun.springboot.gateway.entity.UserRegistry;

@Service
@Configuration
@Slf4j
public class UserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ReactiveAuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil util;

	@Autowired
	private UserDao userDao;

	public UserService() {
		super();
	}

	/**
	 * Creates an user from a given UserRegistry object. Encodes the password and
	 * saves the user in the database.
	 *
	 * @param register - user register object
	 * @param errors   - map contain error information if it occurs
	 * @return null in case of failure, the new user object in case of success.
	 */
	public User createUser(UserRegistry register, Map<String, String> errors) {
		User user = getUserFromRegistry(register);
		return createUser(user, errors);
	}

	/**
	 * Creates an admin user.
	 *
	 * @param register - registration data for this user.
	 * @param errors   - map to access any errors.
	 * @return null if user creation fails. User object otherwise.
	 */
	public User createAdminUser(UserRegistry register, Map<String, String> errors) {
		User user = getUserFromRegistry(register);
		user.setAdmin(true);
		return createUser(user, errors);
	}

	private User getUserFromRegistry(UserRegistry register) {
		User user = new User();
		// encode password
		user.setHashedpw(passwordEncoder.encode(register.getPassword()));
		user.setEmail(register.getEmail());
		user.setName(register.getName());
		return user;
	}

	private User createUser(User user, Map<String, String> errors) {
		try {
			return userDao.addUser(user) ? user : null;
		} catch (IncorrectDaoOperation ex) {
			errors.put("msg", ex.getMessage());
		}
		return null;
	}

	public User loadUser(String email) {
		return userDao.getUser(email);
	}

	private String generateUserToken(String email, String password) {
		// Lets first authenticate the user and generate token
		UserDetails userDetails = loadUserByUsername(email);
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new RuntimeException();
		}

		return util.generateToken((UserPrincipal) userDetails);
	}

	/**
	 * Authenticates the user by storing an entry in the sessions collection. In
	 * case authentication is unsuccessful, return null User.
	 *
	 * @param email    - identifies the user
	 * @param password - user password
	 * @param results  - map to collect any relevant message
	 * @return User object that matches the provided email and password.
	 */
	public User authenticate(String email, String password, Map<String, String> results) {
		String jwt = generateUserToken(email, password);
		if (!userDao.createUserSession(email, jwt)) {
			results.put("msg", "unable to login user");
			return null;
		}
		results.put("auth_token", jwt);
		return userDao.getUser(email);
	}

	/**
	 * Removes user sessions from database.
	 *
	 * @param email - email that identifies the user session
	 * @return true if no more sessions are found for this user
	 */
	public boolean logoutUser(String email) {
		return userDao.deleteUserSessions(email);
	}

	/**
	 * If passwords match then we remove the user from the system.
	 *
	 * @param email    - email that identifies the user
	 * @param password - user password
	 * @param results  - hash map to collect any error message
	 * @return true if successful deletes the user from mflix
	 */
	public boolean deleteUser(String email, String password, Map<String, String> results) {
		// check if hashed password matches
		String hpwd = passwordEncoder.encode(password);
		if (userDao.getUser(email) == null) {
			results.put("msg", "user does not exist");
			return false;
		}

		if (!passwordEncoder.matches(password, hpwd)) {
			results.put("msg", "passwords do not match");
			return false;
		}

		return userDao.deleteUser(email);
	}

	/**
	 * Updates the user preferences settings.
	 *
	 * @param email           - string that identifies the user
	 * @param userPreferences - map of key value pairs that define the user
	 *                        preferences.
	 * @return true in case of success.
	 */
	@SuppressWarnings("unchecked")
	public boolean updateUserPreferences(String email, Map<String, ?> userPreferences, Map<String, Object> results)
			throws UsernameNotFoundException {

		Map<String, String> preferences = (Map<String, String>) userPreferences.get("preferences");
		if (userDao.updateUserPreferences(email, preferences)) {
			User user = userDao.getUser(email);
			if (user == null) {

				throw new UsernameNotFoundException("Cannot find username.");
			}
			results.put("info", user);
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loadUser(username);
		log.info("UserName passed is:" + username);
		if (user == null || user.isEmpty()) {
			throw new UsernameNotFoundException("Cannot find username.");
		}
		return UserPrincipal.create(user);
	}

}
