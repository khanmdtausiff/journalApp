package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAll();
        if (!allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
