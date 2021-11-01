package com.project.sroa_inqueryschedule_msa.service;

import com.project.sroa_inqueryschedule_msa.dto.EngineerBrieflySchedule;
import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.Schedule;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface EngineerInqueryService {
    Map<String, Object> findScheduleAtDateByStartDate(Long engineerNum, String month);

    EngineerInfo findEngineerInfo(UserInfo userInfo);

    UserInfo findEngineerName(String id);

    List<EngineerBrieflySchedule> findEngineerSchedulesDate(Long engineerNum, String today);

    

    Map<String, Object> findScheduleAtDateByEndDate(Long engineerNum, String month);

    Map<String, Integer> findScheduleCntOfEachDay(Map<String, Object> scheduleOfMonthByStartDate, Map<String, Object> scheduleOfMonthByEndDate);

    List<Schedule> findScheduleOfWarehousingProject(Long engineerNum);
}
