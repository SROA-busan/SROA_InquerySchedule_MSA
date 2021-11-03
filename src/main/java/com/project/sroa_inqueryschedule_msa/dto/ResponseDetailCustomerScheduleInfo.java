package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseDetailCustomerScheduleInfo {
    private Long scheduleNum;
    private String productName;
    private String content;
    private String startDate;
    private String endDate;
    private String customerAddress;
    private String centerName;
    private String engineerName;
    private String engineerPhoneNum;
    private Integer status;

    public ResponseDetailCustomerScheduleInfo(Long scheduleNum, String productName, String content, String startDate, String endDate, String customerAddress, String centerName,
                                              String engineerName, String engineerPhoneNum, Integer status) {
        this.scheduleNum=scheduleNum;
        this.productName = productName;
        this.content = content;
        this.startDate = startDate;
        this.endDate=endDate;
        this.customerAddress = customerAddress;
        this.centerName = centerName;
        this.engineerName = engineerName;
        this.engineerPhoneNum = engineerPhoneNum;
        this.status=status;
    }
}
