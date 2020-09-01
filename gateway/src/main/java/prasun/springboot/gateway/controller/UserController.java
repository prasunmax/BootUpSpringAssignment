package prasun.springboot.gateway.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prasun.springboot.gateway.entity.Login;
import prasun.springboot.gateway.entity.User;
import prasun.springboot.gateway.entity.UserRegistry;
import prasun.springboot.gateway.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/user")
@RestController
public class UserController extends ApiController {

	@Autowired
	UserService userService;

	public UserController() {
		super();
	}

	private ResponseEntity<Map<?,?>> buildSuccess(String authToken, User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("auth_token", authToken);
		response.put("info", user);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<?,?>> authenticateUser(@RequestBody Login login) {

		Map<String, String> results = new HashMap<>();

		User user = userService.authenticate(login.getEmail(), login.getPassword(), results);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(results);
		}

		return buildSuccess(results.get("auth_token"), user);
	}

	@PostMapping("/register")
	public ResponseEntity<Map<?,?>> register(@Valid @RequestBody UserRegistry register) {

		Map<String, String> results = new HashMap<>();

		User user = userService.createUser(register, results);
		if (user == null || user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(results);
		}

		return authenticateUser(new Login(register.getEmail(), register.getPassword()));
	}

	@PostMapping("/logout")
	public ResponseEntity<Map<?,?>> logout(@NotNull @RequestHeader("Authorization") String logoutRequest) {
		String email = getEmailFromRequest(logoutRequest);

		if (userService.logoutUser(email)) {
			Map<String, String> response = new HashMap<>();
			response.put("status", "logged out");
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Map<?,?>> delete(@RequestHeader("Authorization") String authorizationToken,
			@NotNull @Size(min = 8) @RequestBody String password) {
		String email = getEmailFromRequest(authorizationToken);
		Map<String, String> results = new HashMap<String, String>();
		if (!userService.deleteUser(email, password, results)) {
			return ResponseEntity.badRequest().body(results);
		}
		Map<String, String> response = new HashMap<>();
		response.put("success", "deleted");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/make-admin")
	public ResponseEntity<Map<?,?>> makeUserAdmin(@RequestBody UserRegistry registry) {
		Map<String, String> results = new HashMap<>();
		User user = userService.createAdminUser(registry, results);

		if (user == null || user.isEmpty()) {
			results.put("status", "fail");
			return ResponseEntity.badRequest().body(results);
		}

		return authenticateUser(new Login(registry.getEmail(), registry.getPassword()));
	}

	@Override
	ResponseEntity<Map<String, String>> index() {
		return ResponseEntity.ok(Collections.emptyMap());
	}
}
