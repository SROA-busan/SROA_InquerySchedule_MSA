package com.project.sroa_inqueryschedule_msa.service;

import com.project.sroa_inqueryschedule_msa.dto.ResponseBrieflyCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.dto.ResponseDetailCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.model.UserInfo;

import java.util.List;

public interface CustomerInqueryService {


    UserInfo findUserInfo(String id);

    List<String> findUserScheduleMonth(String id, String date);

    List<ResponseBrieflyCustomerScheduleInfo> findUserScheduleBriefly(String id, String date);

    ResponseDetailCustomerScheduleInfo findUserScheduleDetail(Long scheduleNum);
}
