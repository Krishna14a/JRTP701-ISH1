package com.JRTP.service;

import com.JRTP.binding.DashBoardCard;
import com.JRTP.binding.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public String login(LoginForm loginForm) {
        return null;
    }

    @Override
    public boolean recoverPwd(String email) {
        return false;
    }

    @Override
    public DashBoardCard fetchDashboardInfo() {
        return null;
    }
}
