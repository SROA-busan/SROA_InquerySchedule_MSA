package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseWorkCntOfMonthEngineer {
    private String date;
    private Integer cnt;

    public ResponseWorkCntOfMonthEngineer(String date, Integer cnt) {
        this.date = date;
        this.cnt = cnt;
    }
}
