package app.english.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import app.english.server.entity.User;
import app.english.server.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User findByUsername(String username) throws NotFoundException {

		try {
			return this.userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException());
		} catch (NotFoundException e) {
			return null;
		}

	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

}
