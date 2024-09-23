package io.github.muhammadredin.securenotesbackend.user.repositories;

import io.github.muhammadredin.securenotesbackend.user.models.Role;
import io.github.muhammadredin.securenotesbackend.user.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(UserRole name);
}
