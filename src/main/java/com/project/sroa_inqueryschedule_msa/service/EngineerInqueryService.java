package com.project.sroa_inqueryschedule_msa.service;

import com.project.sroa_inqueryschedule_msa.dto.EngineerBrieflySchedule;
import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;

import java.util.List;

public interface EngineerInqueryService {
    EngineerInfo findEngineerInfo(UserInfo userInfo);

    UserInfo findEngineerName(String id);

    List<EngineerBrieflySchedule> findEngineerSchedulesDate(Long engineerNum, String today);
}
