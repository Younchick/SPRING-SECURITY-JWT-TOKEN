package com.example.Spring.Security.Api;

import com.example.Spring.Security.Model.MyUser;
import com.example.Spring.Security.Model.Role;
import com.example.Spring.Security.Service.MyUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyUserResources {

    private final MyUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getMyUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<MyUser> saveUsers(@RequestBody MyUser myUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveMyUser(myUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToMyUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


}

@Data
class RoleToUserForm {
    private String userName;
    private String roleName;
}

