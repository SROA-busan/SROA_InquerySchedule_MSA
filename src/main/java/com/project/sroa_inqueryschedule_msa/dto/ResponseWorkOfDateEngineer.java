package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseWorkOfDateEngineer {
    private Long scheduleNum;
    private String productName;
    private String startTime;
    private Integer status;

    public ResponseWorkOfDateEngineer(Long scheduleNum, String productName, String startTime, Integer status) {
        this.scheduleNum=scheduleNum;
        this.productName = productName;
        this.startTime = startTime;
        this.status = status;
    }
}
