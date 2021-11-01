package com.project.sroa_inqueryschedule_msa.service;

import com.project.sroa_inqueryschedule_msa.dto.ResponseBrieflyCustomerScheduleInfo;
import com.project.sroa_inqueryschedule_msa.dto.ResponseDetailCustomerScheduleInfo;

import java.util.List;

public interface CustomerInqueryService {
    List<String> findUserScheduleMonth(String id, String date);

    List<ResponseBrieflyCustomerScheduleInfo> findUserScheduleBriefly(String id, String date);

    ResponseDetailCustomerScheduleInfo findUserScheduleDetail(Long scheduleNum);
}
