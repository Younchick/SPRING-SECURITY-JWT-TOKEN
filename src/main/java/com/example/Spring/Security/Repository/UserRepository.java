package com.example.Spring.Security.Repository;

import com.example.Spring.Security.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUserName(String userName);
}
