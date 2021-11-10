package com.project.sroa_inqueryschedule_msa.controller;

import com.project.sroa_inqueryschedule_msa.dto.*;
import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.Schedule;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;
import com.project.sroa_inqueryschedule_msa.service.EngineerInqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class EngineerInqueryController {
    // status : 0-> 예약완료 , 1 -> 처리 완료, 2 -> 수령, 3 -> 수리 완료, 4 -> 반납예약완료, 5-> 평가 완료
    EngineerInqueryService engineerInqueryService;

    @Autowired
    public EngineerInqueryController(EngineerInqueryService engineerInqueryService){
        this.engineerInqueryService=engineerInqueryService;
    }

    //메인페이지
    //엔지니어 정보,당일 일정을 간략히 출력,
    // 클릭시 일정조회 자세히 준비를 위한 데이터
    // 요청 파라미터 : 아이디
    @GetMapping("/schedule/Engineer/MainPage/{id}")
    public ResponseLoginEngineer EngineeerMainPage(@PathVariable("id") String id){
        UserInfo userInfo= engineerInqueryService.findEngineerName(id);
        EngineerInfo engineer = engineerInqueryService.findEngineerInfo(userInfo);

        String today= LocalDateTime.now().toString().substring(0,10);
        List<EngineerBrieflySchedule> list= engineerInqueryService.findEngineerSchedulesDate(engineer.getEngineerNum(), today);

        Integer avgScore=engineer.getAvgScore();
        String centerName= engineer.getServiceCenter().getCenterName();

        return new ResponseLoginEngineer(centerName, avgScore, list);
    }
    // 하나의 일정에 대해 상세 조회
    @GetMapping("/schedule/Engineer/SelectOneSchedule/{scheduleNum}")
    public EngineerDetailSchedule SelectOneSchedule(@PathVariable("scheduleNum") Long scheduleNum){
        Schedule schedule = engineerInqueryService.findScheduleByScheduleNum(scheduleNum);

        String endTime= new String();
        if(schedule.getEndDate()==null){
            endTime=null;
        }
        else
            endTime=schedule.getEndDate().toString().substring(0,16);

        return new EngineerDetailSchedule(scheduleNum,
                schedule.getStartDate().toString().substring(0,16),
                endTime,
                schedule.getAddress(),
                schedule.getPhoneNum(),
                schedule.getProduct().getClassifyName(),
                schedule.getStatus(),
                schedule.getProduct().getProblem());
    }


    //월간 일정 조회
    // 요청 파라미터 : 아이디, yyyy-mm
    @GetMapping("/schedule/Engineer/InqueryWorkOfMonth/{id}/{month}")
    public List<ResponseWorkCntOfMonthEngineer> InqueryWorkCntOfMonth(@PathVariable("id") String id, @PathVariable("month") String month){
        UserInfo userInfo= engineerInqueryService.findEngineerName(id);
        EngineerInfo engineer = engineerInqueryService.findEngineerInfo(userInfo);

        List<ResponseWorkCntOfMonthEngineer> res= new ArrayList<>();
        Map<String, Object> scheduleOfMonthByStartDate= engineerInqueryService.findScheduleAtDateByStartDate(engineer.getEngineerNum(), month);
        Map<String, Object> scheduleOfMonthByEndDate= engineerInqueryService.findScheduleAtDateByEndDate(engineer.getEngineerNum(), month);
        Map<String, Integer> scheduleCntOfEachDay = engineerInqueryService.findScheduleCntOfEachDay(scheduleOfMonthByStartDate, scheduleOfMonthByEndDate);

        for(String key:scheduleCntOfEachDay.keySet()){
            res.add(new ResponseWorkCntOfMonthEngineer(key, scheduleCntOfEachDay.get(key)));
        }
        return res;
    }

    //월간 일정 에서 날짜 클릭
    // 해당 날짜의 일정 조회
    // 요청 파라미터 : 아이디, yyyy-mm-dd
    @GetMapping("/schedule/Engineer/InqueryWorkOfDate/{id}/{date}")
    public List<ResponseWorkOfDateEngineer> InqueryWorkOfDate(@PathVariable("id") String id, @PathVariable("date") String date) {
        UserInfo userInfo= engineerInqueryService.findEngineerName(id);
        EngineerInfo engineer = engineerInqueryService.findEngineerInfo(userInfo);

        List<ResponseWorkOfDateEngineer> res = new ArrayList<>();
        Map<String, Object> scheduleOfDateByStartDate = engineerInqueryService.findScheduleAtDateByStartDate(engineer.getEngineerNum(), date);
        Map<String, Object> scheduleOfDateByEndDate = engineerInqueryService.findScheduleAtDateByEndDate(engineer.getEngineerNum(), date);

        for(String key : scheduleOfDateByStartDate.keySet() ){
            Schedule schedule= (Schedule) scheduleOfDateByStartDate.get(key);
            res.add(new ResponseWorkOfDateEngineer(schedule.getProduct().getClassifyName(), schedule.getStartDate().toString().substring(11, 16), schedule.getStatus()));
        }
        for(String key : scheduleOfDateByEndDate.keySet()){
            Schedule schedule = (Schedule) scheduleOfDateByEndDate.get(key);
            res.add(new ResponseWorkOfDateEngineer(schedule.getProduct().getClassifyName(), schedule.getEndDate().toString().substring(11, 16), schedule.getStatus()));
        }
        return res;
    }

    // 입고 내역 조회
    @GetMapping("/schedule/Engineer/InqueryWarehousingProduct/{id}")
    public List<ResponseWorkOfDateEngineer> InqueryWarehousingProduct(@PathVariable("id") String id){
        List<ResponseWorkOfDateEngineer> res = new ArrayList<>();
        UserInfo userInfo= engineerInqueryService.findEngineerName(id);
        EngineerInfo engineer = engineerInqueryService.findEngineerInfo(userInfo);
        System.out.println(engineer.getEngineerNum());

        List<Schedule> list = engineerInqueryService.findScheduleOfWarehousingProject(engineer.getEngineerNum());
        System.out.println(list.size());
        for(Schedule schedule : list){
            res.add(new ResponseWorkOfDateEngineer(schedule.getProduct().getClassifyName(),
                    schedule.getStartDate().toString().substring(0,16),
                    schedule.getStatus()));
        }

        return res;
    }

}
