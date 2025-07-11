package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }
    @PostMapping("/create-user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser = userService.saveNewUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
