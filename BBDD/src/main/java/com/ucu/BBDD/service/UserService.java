package com.ucu.BBDD.service;

import com.fabdelgado.ciuy.*;
import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.model.LoginRequestDTO;
import com.ucu.BBDD.model.UsersResponse;
import com.ucu.BBDD.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Validator validator = new Validator();

    public UsersResponse getUsers(){
        List<AppUser> listUsers = jdbcTemplate.query("SELECT * FROM public.appuser",
                ((rs, rowNum) -> new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6))));

        return new UsersResponse(listUsers);
    }

//    private AppUser getUser(AppUser appUser) {
//        String sql = String.format("SELECT * FROM public.appuser WHERE email = '%s'",appUser.getEmail());
//        AppUser user = jdbcTemplate.queryForObject(sql, ((rs, rowNum) -> new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6))));
//        return user;
//    }

    public AppUser getUser(String email) {
        String sql = String.format("SELECT * FROM public.appuser WHERE email = '%s' AND EXISTS (SELECT email FROM public.appuser WHERE email = '%s');",email,email);
        return jdbcTemplate.queryForObject(sql, ((rs, rowNum) -> new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6))));
    }

    //TODO cambiar returns por mensaje
    public boolean saveUser(AppUser appUser){
        if(validator.validateCi(appUser.getCi())){
                String sql = String.format("INSERT INTO public.appuser(email, ci, lastname, name, password, phone) SELECT '%s', '%s', '%s', '%s','%s','%s' WHERE NOT EXISTS (SELECT email FROM public.appuser WHERE email = '%s')"
                        ,appUser.getEmail(),appUser.getCi(),appUser.getLastname(),appUser.getName(),appUser.getPassword(),appUser.getPhone(),appUser.getEmail());
                jdbcTemplate.update(sql);
                return true;
        }
        return false;
    }

    public String deleteUser(String email){
        if(this.getUser(email)!=null){
            String sql = String.format("DELETE FROM public.appuser WHERE email = '%s'",email);
            jdbcTemplate.update(sql);
            return "User removed";
        }else{
            return "ERROR";
        }

    }


    public String getUserEmail(AppUser appUser) {
        return appUser.getEmail();
    }

    public boolean loginCheck(LoginRequestDTO loginRequestDTO) {

        String email = loginRequestDTO.getEmail();
        String sql = String.format("SELECT * FROM public.appuser WHERE email = '%s'",email);
        AppUser appuser = jdbcTemplate.queryForObject(sql, ((rs, rowNum) -> new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6))));
        if (appuser != null){
            if(appuser.getPassword().equals(loginRequestDTO.getPassword())){
                return true;
            }
        }
        return false;

    }

    public boolean changePassword(String email, String password) {

        String sql = String.format("UPDATE public.appuser as usr" +
                "SET password= '%s' " +
                "WHERE usr.email = '%s';",password,email);

        return jdbcTemplate.update(sql) != 0;

    }


}
