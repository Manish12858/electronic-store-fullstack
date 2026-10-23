package com.manish.electronic.store.repositories;

import com.manish.electronic.store.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
