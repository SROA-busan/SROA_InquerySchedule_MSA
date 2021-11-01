package com.project.sroa_inqueryschedule_msa.controller;

import com.project.sroa_inqueryschedule_msa.dto.ResponseBrieflyCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.dto.ResponseDetailCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.service.CustomerInqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@ResponseBody
public class CustomerInqueryController {
    CustomerInqueryService customerInqueryService;

    @Autowired
    public CustomerInqueryController(CustomerInqueryService customerInqueryService){
        this.customerInqueryService=customerInqueryService;
    }

    //고객이 로그인 성공시, 메인페이지 이동시 이번달 일정에 대해 요청
    // 예약 완료 상태, 반납 예약 완료 상태인 일정만 리턴
    // date = yyyy-mm
    @GetMapping("/schedule/customer/MainPage/{id}")
    public List<String> CustomerMainPage(@PathVariable("id") String id){
        System.out.println("메인페이지 접속");
        String date= LocalDateTime.now().toString().substring(0,7);

        return customerInqueryService.findUserScheduleMonth(id, date);
    }

    //고객이 달력 클릭 등 해당 날짜에 대한 일정 요청
    //date = yyyy-mm-dd
    // schedule_num, 제품, 날짜+시간, 문제사항, 수리예약인지 반납예약인지
    @GetMapping("/schedule/customer/requestDateSchedules/{id}/{date}")
    public List<ResponseBrieflyCustomerScheduleInfo> requestDateSchedule(@PathVariable("id") String id, @PathVariable("date") String date){
        System.out.println(date+"날짜의 일정");
        return customerInqueryService.findUserScheduleBriefly(id, date);
    }

    // 고객 하나의 일정에 대해 정보 요청
    // 제품 상태가 0 예약, 3 입고 이면서 enddate!=null (반납예약완료) 인 물품만 받음
    @GetMapping("/schedule/customer/requestDetailSchedule/{scheduleNum}")
    public ResponseDetailCustomerScheduleInfo requestDetailSchedule(@PathVariable("scheduleNum") Long scheduleNum){
        return customerInqueryService.findUserScheduleDetail(scheduleNum);
    }
}
