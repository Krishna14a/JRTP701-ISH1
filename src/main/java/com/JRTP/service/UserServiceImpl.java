package com.JRTP.service;

import com.JRTP.binding.DashBoardCard;
import com.JRTP.binding.LoginForm;
import com.JRTP.entity.EligEntity;
import com.JRTP.entity.UserEntity;
import com.JRTP.repository.EligRepo;
import com.JRTP.repository.PlanRepo;
import com.JRTP.repository.UserRepo;
import com.JRTP.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private EligRepo eligRepo;

    @Override
    public String login(LoginForm loginForm) {

        UserEntity entity = userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());
        if (entity==null){
            return "Invalid Credential...";
        }
        if ("y".equals(entity.getActiveSw()) && "UNLOCKED".equals(entity.getAccStatus())){
            return "success";
        }else {
            return "Account Locked/Inactive";
        }
    }

    @Override
    public boolean recoverPwd(String email) {
        UserEntity userEntity = userRepo.findByEmail(email);
        if (null==userEntity){
            return false;
        }else {
            String subject="";
            String body="";
          return   emailUtils.sendEmail(subject,body,email);
        }
    }

    @Override
    public DashBoardCard fetchDashboardInfo() {
        long planCount = planRepo.count();

        List<EligEntity> eligList = eligRepo.findAll();
        long approvedCnt =
                eligList.stream().filter(ed -> ed.getPlanStatus().equals("AP")).count();
        long deniedCnt =
                eligList.stream().filter(ed -> ed.getPlanStatus().equals("DN")).count();
        Double total = eligList.stream().mapToDouble(ed -> ed.getBenefitAmt()).sum();
        DashBoardCard card = new DashBoardCard();


        card.setPlansCnt(planCount);

        card.setApprovedCnt(approvedCnt);
        card.setDeniedCnt(deniedCnt);
        card.setBeniftAmtGiven(total);

        return card;
    }
}
