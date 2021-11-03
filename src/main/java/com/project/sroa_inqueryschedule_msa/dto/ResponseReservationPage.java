package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

@Getter
public class ResponseReservationPage {
    private String name;
    private String address;
    private String phoneNum;

    public ResponseReservationPage(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }
}
