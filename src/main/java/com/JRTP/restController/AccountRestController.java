package com.JRTP.restController;

import com.JRTP.binding.UserAccForm;
import com.JRTP.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {

    @Autowired
    public AccountService accountService;

    @PostMapping("/user")
    public ResponseEntity<String> createAccount(@RequestBody UserAccForm userAccForm) {

        boolean status = accountService.createUserAccount(userAccForm);
        if (status) {
            return new ResponseEntity<>("Account is created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Account creation Failed...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserAccForm>> getUsers(){
        List<UserAccForm> userAccForms = accountService.fetchUserAccounts();
        return new ResponseEntity<>(userAccForms,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserAccForm> getUser(@PathVariable("userId") Integer userId){
        UserAccForm userAccountById = accountService.getUserAccountById(userId);
        return  new ResponseEntity<>(userAccountById,HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/{status}")
    public ResponseEntity<List<UserAccForm>> updateUserAcc(@PathVariable("userId") Integer userId,@PathVariable("status") String status){
       accountService.changeAccStatus(userId,status);
        List<UserAccForm> userAccForms = accountService.fetchUserAccounts();
        return new ResponseEntity<>(userAccForms,HttpStatus.OK);
    }
}

