package com.project.sroa_inqueryschedule_msa.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseLoginEngineer {
    String centerName;
    Integer avgScore;
    List<EngineerBrieflySchedule> list;

    public ResponseLoginEngineer(String centerName, Integer avgScore, List<EngineerBrieflySchedule> list) {
        this.centerName = centerName;
        this.avgScore = avgScore;
        this.list = list;
    }
}
