package com.example.Spring.Security.Service;

import com.example.Spring.Security.Model.MyUser;
import com.example.Spring.Security.Model.Role;

import java.util.List;

public interface MyUserService {

    MyUser saveMyUser(MyUser myUser);
    Role saveRole(Role role);
    void addRoleToMyUser(String userName, String roleName);
    MyUser getMyUser(String userName);
    List<MyUser> getMyUsers();

}
