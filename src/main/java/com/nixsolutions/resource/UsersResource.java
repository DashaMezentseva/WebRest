package com.nixsolutions.resource;

import com.nixsolutions.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Api(tags = "users", description = "Users API")
public class UsersResource {

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "Find user", notes = "Find User by userId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User found"),
        @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<User> findOne(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(new User());
    }

    @GetMapping
    @ApiOperation(value = "List users", notes = "Find all users")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users found"),
        @ApiResponse(code = 404, message = "Users not found")})
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(Arrays.asList(new User(), new User()));
    }

    @PostMapping
    @ApiOperation(value = "Create user", notes = "It permit to create a new user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User created successfully"),
        @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<User> newUser(User user) {
        return new ResponseEntity<>(new User(), HttpStatus.CREATED);
    }

    @DeleteMapping("/userId")
    @ApiOperation(value = "Remove user", notes = "It permit to remove a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User removed successfully"),
        @ApiResponse(code = 404, message = "User not found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable("userId") Long userId) {

    }

    @PutMapping("/userId")
    @ApiOperation(value = "Update user", notes = "It permit to update a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User updated successfully"),
        @ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, User user) {
        return new ResponseEntity<>(new User(), HttpStatus.OK);
    }
}
