package app.english.server.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.english.server.entity.Role;
import app.english.server.entity.RoleName;
import app.english.server.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(RoleName role_name);

	Set<Role> findAllByUsers(User user);
}
