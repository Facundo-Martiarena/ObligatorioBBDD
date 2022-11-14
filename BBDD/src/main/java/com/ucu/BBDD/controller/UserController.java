package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.model.LoginRequestDTO;
import com.ucu.BBDD.model.UserResponseDTO;
import com.ucu.BBDD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


//    @GetMapping("/users")
//    public List<AppUser> findAllUsers(){
//        return userService.getUsers();
//    }


    //TODO: ver si agregar al final
//    @DeleteMapping("/deleteUser/{email}")
//    public String deleteUser(@PathVariable String email){
//        return userService.deleteUser(email);
//    }

//    @PutMapping("/updateUser")
//    public AppUser updateAppUser(@RequestBody AppUser appUser){
//        return userService.updateUser(appUser);
//    }
}
