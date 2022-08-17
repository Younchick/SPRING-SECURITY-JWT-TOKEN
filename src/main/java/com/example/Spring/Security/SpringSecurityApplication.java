package com.example.Spring.Security;

import com.example.Spring.Security.Model.MyUser;
import com.example.Spring.Security.Model.Role;
import com.example.Spring.Security.Service.MyUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}


	@Bean
	CommandLineRunner run(MyUserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveMyUser(new MyUser(null, "Ivan", "Montana", "1234", new ArrayList<>()));
			userService.saveMyUser(new MyUser(null, "Mykhailo", "Hviloviy", "1234", new ArrayList<>()));
			userService.saveMyUser(new MyUser(null, "Mykola", "HardCoder", "1234", new ArrayList<>()));
			userService.saveMyUser(new MyUser(null, "Coder", "Ivan", "codeIsFun", new ArrayList<>()));

			userService.addRoleToMyUser("Montana", "ROLE_USER");
			userService.addRoleToMyUser("Montana", "ROLE_MANAGER");
			userService.addRoleToMyUser("Hviloviy", "ROLE_USER");
			userService.addRoleToMyUser("HardCoder", "ROLE_MANAGER");
			userService.addRoleToMyUser("Ivan", "ROLE_SUPER_ADMIN");
		};
	}
}
