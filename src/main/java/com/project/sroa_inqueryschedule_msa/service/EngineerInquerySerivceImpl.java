package com.project.sroa_inqueryschedule_msa.service;

import com.project.sroa_inqueryschedule_msa.dto.EngineerBrieflySchedule;
import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.Schedule;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;
import com.project.sroa_inqueryschedule_msa.repository.EngineerInfoRepository;
import com.project.sroa_inqueryschedule_msa.repository.ScheduleRepository;
import com.project.sroa_inqueryschedule_msa.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EngineerInquerySerivceImpl implements  EngineerInqueryService{
    EngineerInfoRepository engineerInfoRepository;
    UserInfoRepository userInfoRepository;
    ScheduleRepository scheduleRepository;

    @Autowired
    public EngineerInquerySerivceImpl(EngineerInfoRepository engineerInfoRepository,
                                      UserInfoRepository userInfoRepository,
                                      ScheduleRepository scheduleRepository){
        this.engineerInfoRepository=engineerInfoRepository;
        this.userInfoRepository=userInfoRepository;
        this.scheduleRepository=scheduleRepository;

    }

    @Override
    public UserInfo findEngineerName(String id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public EngineerInfo findEngineerInfo(UserInfo userInfo) {
       return engineerInfoRepository.findByUserNum(userInfo.getUserNum());
    }

    @Override
    public List<EngineerBrieflySchedule> findEngineerSchedulesDate(Long engineerNum, String today) {
        List<EngineerBrieflySchedule> res= new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAllStartDateByEngineerNumAndDate(engineerNum, today);

        for(Schedule schedule: schedules){
            res.add(new EngineerBrieflySchedule(
                    schedule.getScheduleNum(),
                    schedule.getStartDate().toString().substring(11,16),
                    schedule.getAddress(),
                    schedule.getPhoneNum().toString(),
                    schedule.getProduct().getClassifyName(),
                    schedule.getStatus()
            ));
        }

        schedules=scheduleRepository.findAllEndDateByEngineerNumAndDate(engineerNum, today);
        for(Schedule schedule: schedules){
            res.add(new EngineerBrieflySchedule(
                    schedule.getScheduleNum(),
                    schedule.getEndDate().toString().substring(11,16),
                    schedule.getAddress(),
                    schedule.getPhoneNum().toString(),
                    schedule.getProduct().getClassifyName(),
                    schedule.getStatus()
            ));
        }
        return res;
    }
}
