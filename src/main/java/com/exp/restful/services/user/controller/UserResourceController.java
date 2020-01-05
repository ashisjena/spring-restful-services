package com.exp.restful.services.user.controller;

import com.exp.restful.services.user.beans.User;
import com.exp.restful.services.user.exception.UserNotFoundException;
import com.exp.restful.services.user.services.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResourceController {
  private UserDaoService service;

  @Autowired
  public UserResourceController(UserDaoService service) {
    this.service = service;
  }

  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return service.findAll();
  }

  @GetMapping("/users/{id}")
  public User getUser(@PathVariable int id) throws UserNotFoundException {
    return service.findOne(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d is not found", id)));
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = service.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
    // A header was sent in the response.... location:http://localhost:8080/users/4
    return ResponseEntity.created(location).build();
  }
}
