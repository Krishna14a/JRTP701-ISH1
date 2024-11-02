package com.JRTP.service;

import com.JRTP.binding.DashBoardCard;
import com.JRTP.binding.LoginForm;

public interface UserService {

    public String login(LoginForm loginForm);

    public boolean recoverPwd(String email);

    public DashBoardCard fetchDashboardInfo();
}
