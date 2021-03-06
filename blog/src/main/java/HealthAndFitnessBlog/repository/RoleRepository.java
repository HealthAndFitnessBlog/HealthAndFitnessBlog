package HealthAndFitnessBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import HealthAndFitnessBlog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}