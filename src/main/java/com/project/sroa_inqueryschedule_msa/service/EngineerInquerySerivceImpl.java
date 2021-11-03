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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EngineerInquerySerivceImpl implements EngineerInqueryService {
    EngineerInfoRepository engineerInfoRepository;
    UserInfoRepository userInfoRepository;
    ScheduleRepository scheduleRepository;

    @Autowired
    public EngineerInquerySerivceImpl(EngineerInfoRepository engineerInfoRepository,
                                      UserInfoRepository userInfoRepository,
                                      ScheduleRepository scheduleRepository) {
        this.engineerInfoRepository = engineerInfoRepository;
        this.userInfoRepository = userInfoRepository;
        this.scheduleRepository = scheduleRepository;

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
        List<EngineerBrieflySchedule> res = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAllStartDateByEngineerNumAndDate(engineerNum, today);

        for (Schedule schedule : schedules) {
            res.add(new EngineerBrieflySchedule(
                    schedule.getScheduleNum(),
                    schedule.getStartDate().toString().substring(11, 16),
                    schedule.getProduct().getClassifyName(),
                    schedule.getStatus()
            ));
        }

        schedules = scheduleRepository.findAllEndDateByEngineerNumAndDate(engineerNum, today);
        for (Schedule schedule : schedules) {
            res.add(new EngineerBrieflySchedule(
                    schedule.getScheduleNum(),
                    schedule.getEndDate().toString().substring(11, 16),
                    schedule.getProduct().getClassifyName(),
                    schedule.getStatus()
            ));
        }
        return res;
    }

    @Override
    public Schedule findScheduleByScheduleNum(Long scheduleNum) {
        return scheduleRepository.findByScheduleNum(scheduleNum);
    }

    //시작일 기준 일정 찾기
    @Override
    public Map<String, Object> findScheduleAtDateByStartDate(Long engineerNum, String month) {

        Map<String, Object> map = new HashMap<>();

        List<Schedule> list = scheduleRepository.findAllStartDateByEngineerNumAndDate(engineerNum, month);
        for (Schedule schedule : list) {
            map.put(schedule.getStartDate().toString(), schedule);
        }
        return map;
    }

    // 마감일 기준 일정 찾기
    @Override
    public Map<String, Object> findScheduleAtDateByEndDate(Long engineerNum, String month) {
        Map<String, Object> map = new HashMap<>();

        List<Schedule> list = scheduleRepository.findAllEndDateByEngineerNumAndDate(engineerNum, month);

        for (Schedule schedule : list) {
            String startDate = schedule.getStartDate().toString().substring(0, 10);
            String endDate = schedule.getEndDate().toString().substring(0, 10);

            // 시작/완료일이 같다면 당일 처리했다는 의미 -> startDate기준의 map에 이미 담겨있기 때문에 pass
            if (startDate.equals(endDate)) continue;


            map.put(schedule.getEndDate().toString(), schedule);

            if (schedule.getStartDate().getMonth().toString() != month.substring(5, 7)) continue;
            map.put(schedule.getStartDate().toString(), schedule);
        }
        return map;
    }


    // 해달 날짜의 일정 갯수
    @Override
    public Map<String, Integer> findScheduleCntOfEachDay(Map<String, Object> scheduleOfMonthByStartDate,
                                                         Map<String, Object> scheduleOfMonthByEndDate) {
        Map<String, Integer> res = new HashMap<>();

        for (String key : scheduleOfMonthByStartDate.keySet()) {
            String newKey = key.substring(0, 10);
            if (res.containsKey(newKey))
                res.put(newKey, res.get(newKey) + 1);
            else
                res.put(newKey, 1);
        }
        for (String key : scheduleOfMonthByEndDate.keySet()) {
            String newKey = key.substring(0, 10);
            if (res.containsKey(newKey))
                res.put(newKey, res.get(newKey) + 1);
            else
                res.put(newKey, 1);
        }
        return res;
    }

    //

    @Override
    public List<Schedule> findScheduleOfWarehousingProject(Long engineerNum) {
        return scheduleRepository.findAllWarehousingSchedule(engineerNum);
    }
}
