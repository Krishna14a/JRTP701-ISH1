package com.JRTP.service;

import com.JRTP.binding.UnlockAccForm;
import com.JRTP.binding.UserAccForm;
import com.JRTP.entity.UserEntity;
import com.JRTP.repository.UserRepo;
import com.JRTP.utils.EmailUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;
    @Override
    public boolean createUserAccount(UserAccForm accForm) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(accForm,entity);
        //set Rendom Password
        entity.setPwd(generatePwd());

        //Set accStatus

        entity.setAccStatus("LOCKED");
        entity.setActiveSw("Y");

        userRepo.save(entity);

        //send Email...
        String subject="";
        String body="";

      return emailUtils.sendEmail(subject,body,accForm.getEmail());

    }

    @Override
    public List<UserAccForm> fetchUserAccounts() {
        List<UserEntity> userEntities = userRepo.findAll();
        List<UserAccForm> users = new ArrayList<UserAccForm>();
        for (UserEntity userEntity : userEntities) {
            UserAccForm user = new UserAccForm();
            BeanUtils.copyProperties(userEntity, user);
            users.add(user);
        }

        return users;
    }

    @Override
    public UserAccForm getUserAccountById(Integer accId) {

        Optional<UserEntity> optional = userRepo.findById(accId);
        if (optional.isPresent()){
            UserEntity userEntity = optional.get();
            UserAccForm user = new UserAccForm();
            BeanUtils.copyProperties(userEntity,user);
            return user;
        }

        return null;
    }

    @Override
    public String changeAccStatus(Integer userId, String status) {

        Integer cnt = userRepo.updateAccStatus(userId, status);
        if (cnt>0){
            return "status changed...";
        }
        return "Failed to changed...";
    }

    @Override
    public String unlockUserAccount(UnlockAccForm unlockAccForm) {
        UserEntity entity = userRepo.findByEmail(unlockAccForm.getEmail());
        entity.setPwd(unlockAccForm.getNewPwd());
        entity.setAccStatus("UNLOCKED");
        userRepo.save(entity);
        return "Unlocked Account....";
    }

    //generate rendom password.....
    private String generatePwd() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 6;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
