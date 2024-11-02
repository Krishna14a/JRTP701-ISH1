package com.JRTP.service;

import com.JRTP.binding.PlanForm;

import java.util.List;

public interface PlanService {

    public boolean createPlan(PlanForm planForm);

    public List<PlanForm> fetchPlans();

    public PlanForm getPlanById(Integer planId);

    public String changePlanStatus(Integer planId,String status);
}
