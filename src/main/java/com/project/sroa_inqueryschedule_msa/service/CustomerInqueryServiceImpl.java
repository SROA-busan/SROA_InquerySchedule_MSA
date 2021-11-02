package com.project.sroa_inqueryschedule_msa.service;

import com.project.sroa_inqueryschedule_msa.dto.ResponseBrieflyCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.dto.ResponseDetailCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.Product;
import com.project.sroa_inqueryschedule_msa.model.Schedule;
import com.project.sroa_inqueryschedule_msa.repository.EngineerInfoRepository;
import com.project.sroa_inqueryschedule_msa.repository.ProductRepository;
import com.project.sroa_inqueryschedule_msa.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerInqueryServiceImpl implements CustomerInqueryService{
    ScheduleRepository scheduleRepository;
    ProductRepository productRepository;
    EngineerInfoRepository engineerInfoRepository;

    @Autowired
    public CustomerInqueryServiceImpl(ScheduleRepository scheduleRepository,
                                      ProductRepository productRepository,
                                      EngineerInfoRepository engineerInfoRepository){
        this.scheduleRepository=scheduleRepository;
        this.productRepository=productRepository;
        this.engineerInfoRepository=engineerInfoRepository;
    }

    @Override
    public List<String> findUserScheduleMonth(String id, String date) {
        List<Schedule> Schedules= scheduleRepository.findAllStartDateByUserIdAndDate(id, date);
        List<String> res= new ArrayList<>();

        for(Schedule schedule:Schedules){
            String str=schedule.getStartDate().toString().substring(0,10);
            if(schedule.getStatus()!=0 && !(schedule.getStatus()==3 && schedule.getEndDate()==null ))continue;
            if(!res.contains(str))
                res.add(str);
        }

        Schedules= scheduleRepository.findAllEndDateByUserIdAndDate(id, date);
        for(Schedule schedule:Schedules){
            String str=schedule.getStartDate().toString().substring(0,10);
            if(schedule.getStatus()!=0 && !(schedule.getStatus()==3 && schedule.getEndDate()==null ))continue;
            if(!res.contains(str))
                res.add(str);
        }
        return res;
    }

    @Override
    public List<ResponseBrieflyCustomerScheduleInfo> findUserScheduleBriefly(String id, String date) {
        List<ResponseBrieflyCustomerScheduleInfo> res =new ArrayList<>();
        List<Schedule> Schedules= scheduleRepository.findAllStartDateByUserIdAndDate(id, date);
        for(Schedule schedule:Schedules){
            if(schedule.getStatus()!=0 && !(schedule.getStatus()==3 && schedule.getEndDate()==null ))continue;
            Product product= productRepository.findByScheduleNum(schedule.getScheduleNum());
            res.add(new ResponseBrieflyCustomerScheduleInfo(schedule.getScheduleNum(), product.getClassifyName(), schedule.getStartDate().toString().substring(0,16), product.getClassifyName(), schedule.getStatus()));
        }

        Schedules= scheduleRepository.findAllEndDateByUserIdAndDate(id, date);
        for(Schedule schedule:Schedules) {
            if(schedule.getStatus()!=0 && !(schedule.getStatus()==3 && schedule.getEndDate()==null ))continue;
            Product product= productRepository.findByScheduleNum(schedule.getScheduleNum());
            res.add(new ResponseBrieflyCustomerScheduleInfo(schedule.getScheduleNum(), product.getClassifyName(), schedule.getStartDate().toString().substring(0,16), product.getClassifyName(), schedule.getStatus()));
        }
        return res;
    }

    @Override
    public ResponseDetailCustomerScheduleInfo findUserScheduleDetail(Long scheduleNum) {
        Product product=productRepository.findByScheduleNum(scheduleNum);
        Schedule schedule=scheduleRepository.findByScheduleNum(scheduleNum);
        EngineerInfo engineerInfo = engineerInfoRepository.findByEngineerNum(schedule.getEngineerInfo().getEngineerNum());
        String date= new String();
        if(schedule.getStatus()==0)
            date = schedule.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        else
            date = schedule.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new ResponseDetailCustomerScheduleInfo(
                scheduleNum,
                product.getClassifyName(), product.getProblem(),
                date, schedule.getAddress(), engineerInfo.getServiceCenter().getCenterName(), engineerInfo.getUserInfo().getName(),
                engineerInfo.getUserInfo().getPhoneNum(), schedule.getStatus());
    }
}
