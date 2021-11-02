package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class EngineerBrieflySchedule {
    private Long ScheduleNum;
    private String startTime;
    private String productName;
    private Integer state;

    public EngineerBrieflySchedule(Long scheduleNum, String startTime,  String productName, Integer state) {
        ScheduleNum = scheduleNum;
        this.startTime = startTime;
        this.productName = productName;
        this.state = state;
    }
}
