package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
