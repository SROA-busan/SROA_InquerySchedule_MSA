package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class EngineerBrieflySchedule {
    private Long ScheduleNum;
    private String startTime;
    private String customerAddress;
    private String customerPhoneNum;
    private String productName;
    private Integer state;
    private String content;

    public EngineerBrieflySchedule(Long scheduleNum, String startTime,  String customerAddress, String customerPhoneNum, String productName, Integer state, String content) {
        ScheduleNum = scheduleNum;
        this.startTime = startTime;
        this.customerAddress = customerAddress;
        this.customerPhoneNum = customerPhoneNum;
        this.productName = productName;
        this.state = state;
        this.content=content;
    }
}
