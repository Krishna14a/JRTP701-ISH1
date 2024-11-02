package com.JRTP.service;

import com.JRTP.binding.UnlockAccForm;
import com.JRTP.binding.UserAccForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Override
    public boolean createUserAccount(UserAccForm accForm) {
        return false;
    }

    @Override
    public List<UserAccForm> fetchUserAccounts() {
        return null;
    }

    @Override
    public UserAccForm getUserAccountById(Integer accId) {
        return null;
    }

    @Override
    public String changeAccStatus(Integer accId, String status) {
        return null;
    }

    @Override
    public String unlockUserAccount(UnlockAccForm unlockAccForm) {
        return null;
    }
}
