package com.project.sroa_inqueryschedule_msa.controller;

import com.project.sroa_inqueryschedule_msa.dto.EngineerBrieflySchedule;
import com.project.sroa_inqueryschedule_msa.dto.ResponseLoginEngineer;
import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.ServiceCenter;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;
import com.project.sroa_inqueryschedule_msa.service.EngineerInqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@ResponseBody
public class EngineerInqueryController {
    EngineerInqueryService engineerInqueryService;

    @Autowired
    public EngineerInqueryController(EngineerInqueryService engineerInqueryService){
        this.engineerInqueryService=engineerInqueryService;
    }

    //메인페이지
    //엔지니어 정보,당일 일정을 간략히 출력
    @GetMapping("/schedule/Engineer/MainPage/{id}")
    public ResponseLoginEngineer EngineeerMainPage(@PathVariable("id") String id){
        UserInfo userInfo= engineerInqueryService.findEngineerName(id);
        EngineerInfo engineer = engineerInqueryService.findEngineerInfo(userInfo);

        String today= LocalDateTime.now().toString().substring(0,9);
        List<EngineerBrieflySchedule> list= engineerInqueryService.findEngineerSchedulesDate(engineer.getEngineerNum(), today);

        Integer avgScore=engineer.getAvgScore();
        String centerName= engineer.getServiceCenter().getCenterName();

        return new ResponseLoginEngineer(centerName, avgScore, list);
    }
    //월간 일정 조회
    //월간 일정 에서 날짜 클릭

}
