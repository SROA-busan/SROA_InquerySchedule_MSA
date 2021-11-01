package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseDetailCustomerScheduleInfo {
    private String productName;
    private String content;
    private String date;
    private String customerAddress;
    private String centerName;
    private String engineerName;
    private String engineerPhoneNum;
    private Integer status;

    public ResponseDetailCustomerScheduleInfo(String productName, String content, String date, String customerAddress, String centerName,
                                              String engineerName, String engineerPhoneNum, Integer status) {
        this.productName = productName;
        this.content = content;
        this.date = date;
        this.customerAddress = customerAddress;
        this.centerName = centerName;
        this.engineerName = engineerName;
        this.engineerPhoneNum = engineerPhoneNum;
        this.status=status;
    }
}
