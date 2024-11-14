package app.english.server.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.english.server.entity.Role;
import app.english.server.entity.RoleName;
import app.english.server.entity.User;
import app.english.server.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role findByRoleName(RoleName roleName) {
		return this.roleRepository.findByRoleName(roleName);
	}

	public Set<Role> findAllByUser(User user) {
		return roleRepository.findAllByUsers(user);
	}

}
