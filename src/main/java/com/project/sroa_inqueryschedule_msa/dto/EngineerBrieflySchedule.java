package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class EngineerBrieflySchedule {
    Long ScheduleNum;
    String startTime;
    String customerAddress;
    String customerPhoneNum;
    String productName;
    Integer state;

    public EngineerBrieflySchedule(Long scheduleNum, String startTime,  String customerAddress, String customerPhoneNum, String productName, Integer state) {
        ScheduleNum = scheduleNum;
        this.startTime = startTime;
        this.customerAddress = customerAddress;
        this.customerPhoneNum = customerPhoneNum;
        this.productName = productName;
        this.state = state;
    }
}
