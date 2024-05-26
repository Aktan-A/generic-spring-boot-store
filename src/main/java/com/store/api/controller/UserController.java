package com.store.api.controller;

import com.store.api.dto.UserDto;
import com.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User successfully deleted.");
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUserById(userId, userDto));
    }

}
