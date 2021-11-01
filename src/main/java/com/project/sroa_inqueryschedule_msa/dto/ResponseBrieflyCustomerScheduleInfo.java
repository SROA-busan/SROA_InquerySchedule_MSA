package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseBrieflyCustomerScheduleInfo {
    Long scheduleNum;
    String productName;
    String date;
    String content;
    //true : 수리, false : 반납
    Integer flag;

    public ResponseBrieflyCustomerScheduleInfo(Long scheduleNum, String productName, String date, String content, Integer b) {
        this.scheduleNum=scheduleNum;
        this.productName=productName;
        this.date=date;
        this.content=content;
        this.flag=b;
    }
}
