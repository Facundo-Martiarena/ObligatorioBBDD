package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.model.LoginRequestDTO;
import com.ucu.BBDD.model.RequestBodyUser;
import com.ucu.BBDD.model.UserResponseDTO;
import com.ucu.BBDD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


        // Registro de usuario
    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserResponseDTO addUser(@RequestBody AppUser appUser){
         boolean accepted = userService.saveUser(appUser);
         String email = userService.getUserEmail(appUser);
         return new UserResponseDTO(accepted, email);
    }


        // Login de usuario
    @PostMapping("/loginUser")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserResponseDTO loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
        boolean login = userService.loginCheck(loginRequestDTO);
        String email = loginRequestDTO.getEmail();
        return new UserResponseDTO(login, email);
    }


    @GetMapping("/users")
    public List<AppUser> findAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Boolean changePasswordUser(@RequestBody RequestBodyUser requestBodyUser){
        return userService.changePassword(requestBodyUser.getEmail(),requestBodyUser.getPassword());
    }


}
