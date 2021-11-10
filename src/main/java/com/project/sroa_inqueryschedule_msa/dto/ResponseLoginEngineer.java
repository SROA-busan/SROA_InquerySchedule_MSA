package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseLoginEngineer {
    private String centerName;
    private Integer avgScore;
    private String name;
    private List<EngineerBrieflySchedule> list;

    public ResponseLoginEngineer(String centerName, Integer avgScore, String name, List<EngineerBrieflySchedule> list) {
        this.centerName = centerName;
        this.avgScore = avgScore;
        this.list = list;
        this.name=name;
    }
}
