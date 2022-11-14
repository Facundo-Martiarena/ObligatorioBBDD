package com.ucu.BBDD.service;

import com.fabdelgado.ciuy.*;
import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.model.LoginRequestDTO;
import com.ucu.BBDD.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    Validator validator = new Validator();

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

    private AppUser getUser(AppUser appUser) {
        return appUserRepository.findById(appUser.getEmail()).orElse(null);
    }

    private AppUser getUser(String email) {
        return appUserRepository.findById(email).orElse(null);
    }

    //TODO cambiar returns por mensaje
    public boolean saveUser(AppUser appUser){
        if(validator.validateCi(appUser.getCi())){
            if (getUser(appUser)==null){
                 appUserRepository.save(appUser);
                 return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public String deleteUser(String email){
        if(this.getUser(email)!=null){
            appUserRepository.deleteById(email);
            return "User removed";
        }else{
            return "ERROR";
        }

    }

    //TODO dependiendo si el user existe o no, cambiar mensaje de return
//    public AppUser updateUser(AppUser appUser){
//        AppUser existingUser = this.getUser(appUser);
//        if (existingUser != null){
//            existingUser.setCi(appUser.getCi());
//            existingUser.setName(appUser.getName());
//            existingUser.setLastname(appUser.getLastname());
//            existingUser.setPassword(appUser.getPassword());
//            existingUser.setPhone(appUser.getPhone());
//        }
//        return appUserRepository.save(existingUser);
//    }

    public String getUserEmail(AppUser appUser) {
        return appUser.getEmail();
    }

    public boolean loginCheck(LoginRequestDTO loginRequestDTO) {

        boolean userExist = appUserRepository.findById(loginRequestDTO.getEmail()).isPresent();
        if (userExist){
            AppUser user = appUserRepository.findById(loginRequestDTO.getEmail()).get();
            return user.getPassword().equals(loginRequestDTO.getPassword());
        }
        return false;
    }
}
