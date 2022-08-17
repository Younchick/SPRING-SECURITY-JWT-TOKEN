package com.example.Spring.Security.Service;

import com.example.Spring.Security.Model.MyUser;
import com.example.Spring.Security.Model.Role;
import com.example.Spring.Security.Repository.RoleRepositorty;
import com.example.Spring.Security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MyUserServiceImp implements MyUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepositorty roleRepositorty;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUserName(userName);
        if(user == null){
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in data base");
        } else {
            log.info("User {} found in database", userName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    @Override
    public MyUser saveMyUser(MyUser myUser) {
        log.info("Saving new user {} to the database.", myUser.getName());
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return userRepository.save(myUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database.", role.getName());
        return roleRepositorty.save(role);
    }

    @Override
    public void addRoleToMyUser(String userName, String roleName) {

        log.info("Adding role {} to user {}.", roleName, userName);
        MyUser myUser = userRepository.findByUserName(userName);
        Role role = roleRepositorty.findByName(roleName);
        myUser.getRoles().add(role);
    }

    @Override
    public MyUser getMyUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<MyUser> getMyUsers() {
        log.info("Fetching all user");
        return userRepository.findAll();
    }

}
