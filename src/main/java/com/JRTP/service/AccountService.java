package com.JRTP.service;

import com.JRTP.binding.UnlockAccForm;
import com.JRTP.binding.UserAccForm;

import java.util.List;

public interface AccountService {

   public boolean createUserAccount(UserAccForm accForm);

   public List<UserAccForm> fetchUserAccounts();

   public UserAccForm getUserAccountById(Integer accId);

   public String changeAccStatus(Integer userId,String status);

   public String unlockUserAccount(UnlockAccForm unlockAccForm);
}
