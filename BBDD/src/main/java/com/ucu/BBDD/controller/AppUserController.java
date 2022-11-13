package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public AppUser addUser(@RequestBody AppUser appUser){
        return userService.saveUser(appUser);
    }


    @GetMapping("/users")
    public List<AppUser> findAllUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/deleteUser/{email}")
    public String deleteUser(@PathVariable String email){
        return userService.deleteUser(email);
    }

    @PutMapping("/updateUser")
    public AppUser updateAppUser(@RequestBody AppUser appUser){
        return userService.updateUser(appUser);
    }
}
