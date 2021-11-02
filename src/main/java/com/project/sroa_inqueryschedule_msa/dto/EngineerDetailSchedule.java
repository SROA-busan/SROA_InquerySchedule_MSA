package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class EngineerDetailSchedule {
    private Long ScheduleNum;
    private String startTime;
    private String endTime;
    private String customerAddress;
    private String customerPhoneNum;
    private String productName;
    private Integer state;
    private String content;

    public EngineerDetailSchedule(Long scheduleNum, String startTime, String endTime, String customerAddress, String customerPhoneNum, String productName, Integer state, String content) {
        this.ScheduleNum = scheduleNum;
        this.startTime = startTime;
        this.endTime=endTime;
        this.customerAddress = customerAddress;
        this.customerPhoneNum = customerPhoneNum;
        this.productName = productName;
        this.state = state;
        this.content=content;
    }
}
