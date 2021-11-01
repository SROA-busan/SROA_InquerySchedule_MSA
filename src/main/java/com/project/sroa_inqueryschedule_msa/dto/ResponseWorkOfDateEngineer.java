package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseWorkOfDateEngineer {
    private String productName;
    private String startTime;
    private Integer status;

    public ResponseWorkOfDateEngineer(String productName, String startTime, Integer status) {
        this.productName = productName;
        this.startTime = startTime;
        this.status = status;
    }
}
