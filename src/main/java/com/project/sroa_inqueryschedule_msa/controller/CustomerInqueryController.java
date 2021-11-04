package com.project.sroa_inqueryschedule_msa.controller;

import com.project.sroa_inqueryschedule_msa.dto.ResponseBrieflyCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.dto.ResponseDetailCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.dto.ResponseReservationPage;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;
import com.project.sroa_inqueryschedule_msa.service.CustomerInqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
public class CustomerInqueryController {
    // status : 0-> 예약완료 , 1 -> 처리 완료, 2 -> 수령, 3 -> 수리 완료, 4 -> 반납예약완료, 5-> 평가 완료
    CustomerInqueryService customerInqueryService;

    @Autowired
    public CustomerInqueryController(CustomerInqueryService customerInqueryService){
        this.customerInqueryService=customerInqueryService;
    }

    //고객이 로그인 성공시, 메인페이지 이동시 이번달 일정에 대해 요청
    // 예약 완료 상태, 반납 예약 완료 상태인 일정만 리턴
    // date = yyyy-mm
    @GetMapping("/schedule/customer/MainPage/{id}/{date}")
    public List<String> CustomerMainPage(@PathVariable("id") String id,
                                         @PathVariable("date") String date){
        System.out.println("메인페이지 접속");

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

    // 고객 어플 수리현황 클릭 -> 현재 처리 완료되지않은 일정 간략히 출력
    @GetMapping("/schedule/customer/requestCurrentRepair/{id}")
    public List<ResponseBrieflyCustomerScheduleInfo> requestCurrentRepair(@PathVariable("id") String id){
        UserInfo user=customerInqueryService.findUserInfo(id);


        return customerInqueryService.findCurrentScheduleByState(user.getUserNum());
    }

    // 고객 어플 지난수리현황 클릭 -> 처리 완료된 일정 간략히 출력
    @GetMapping("/schedule/customer/requestLastRepair/{id}")
    public List<ResponseBrieflyCustomerScheduleInfo> requestLastRepair(@PathVariable("id") String id){
        UserInfo user=customerInqueryService.findUserInfo(id);

        return customerInqueryService.findLastScheduleByState(user.getUserNum());
    }



    // 고객 하나의 일정에 대해 정보 요청
    // 제품 상태가 0 예약, 3 입고 이면서 enddate!=null (반납예약완료) 인 물품만 받음
    @GetMapping("/schedule/customer/requestDetailSchedule/{scheduleNum}")
    public ResponseDetailCustomerScheduleInfo requestDetailSchedule(@PathVariable("scheduleNum") Long scheduleNum){
        return customerInqueryService.findUserScheduleDetail(scheduleNum);
    }

    @GetMapping("/schedule/customer/requestReservationPage/{id}")
    public ResponseReservationPage requestReservationPage(@PathVariable("id") String id){
        UserInfo user= customerInqueryService.findUserInfo(id);
        return new ResponseReservationPage(user.getName(), user.getAddress(), user.getPhoneNum());
    }

}
