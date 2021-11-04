package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseBrieflyCustomerScheduleInfo {
    private Long scheduleNum;
    private String productName;
    private String startDate;
    private String endDate;
    private String content;
    //true : 수리, false : 반납
    private Integer flag;

    public ResponseBrieflyCustomerScheduleInfo(Long scheduleNum, String productName, String startDate, String endDate, String content, Integer b) {
        this.scheduleNum=scheduleNum;
        this.productName=productName;
        this.startDate=startDate;
        this.endDate=endDate;
        this.content=content;
        this.flag=b;
    }
}
