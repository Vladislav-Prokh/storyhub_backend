package app.english.server.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.english.server.DTO.JwtLoginResponse;
import app.english.server.DTO.JwtRegisterResponse;
import app.english.server.DTO.LoginRequest;
import app.english.server.DTO.RegistrationRequest;
import app.english.server.entity.Role;
import app.english.server.entity.RoleName;
import app.english.server.entity.User;
import app.english.server.exceptions.InvalidCredentialsException;
import app.english.server.exceptions.UserAlreadyExistsException;

@Service
public class AuthService {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtRegisterResponse register(RegistrationRequest request)
			throws UserAlreadyExistsException, NotFoundException {
		String username = request.getUsername();
		String password = request.getPassword();

		User existingUser = userService.findByUsername(username);
		if (existingUser != null) {
			throw new UserAlreadyExistsException("User with this username already exists");
		}

		password = passwordEncoder.encode(password);
		Role roleUser = roleService.findByRoleName(RoleName.USER);
		User user = User.createUser(username, password, Set.of(roleUser));

		userService.saveUser(user);
		return new JwtRegisterResponse("CREATED");
	}

	public JwtLoginResponse login(LoginRequest request) throws InvalidCredentialsException, NotFoundException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userService.findByUsername(request.getUsername());
		if (user == null) {
			throw new InvalidCredentialsException("Invalid username or password");
		}

		List<String> roles = userRolesAsListString(user.getRoles());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtService.generateToken(authentication.getName());

		return new JwtLoginResponse(token, roles);
	}

	private List<String> userRolesAsListString(Set<Role> roles) {
		return roles.stream().map(role -> role.getRoleName().toString()).collect(Collectors.toList());
	}
}
