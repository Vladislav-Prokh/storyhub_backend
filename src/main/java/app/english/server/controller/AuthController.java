package app.english.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.english.server.DTO.LoginRequest;
import app.english.server.DTO.RegistrationRequest;
import app.english.server.exceptions.InvalidCredentialsException;
import app.english.server.exceptions.UserAlreadyExistsException;
import app.english.server.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegistrationRequest request) throws NotFoundException {
		try {
			return ResponseEntity.ok(authService.register(request));
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) throws NotFoundException {
		try {
			return ResponseEntity.ok(authService.login(request));
		} catch (InvalidCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
}
