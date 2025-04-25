package com.openlab.biblio.controller;

import com.openlab.biblio.entity.User;
import com.openlab.biblio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "CRUD operations for users")
public class UserController {

    private final UserService  userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> getAllUsers(){
       return userService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public User creatUser(@RequestBody User user){
        return userService.save(user);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(userService.update(id, user));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
         userService.delete(id);
         return  ResponseEntity.noContent().build();
    }

}
