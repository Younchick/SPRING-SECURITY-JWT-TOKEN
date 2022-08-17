package com.example.Spring.Security.Repository;

import com.example.Spring.Security.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositorty extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
