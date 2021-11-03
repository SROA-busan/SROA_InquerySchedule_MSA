package com.project.sroa_inqueryschedule_msa.repository;

import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EngineerInfoRepository extends JpaRepository<EngineerInfo, Long> {


    EngineerInfo findByEngineerNum(long engineerNum);


    @Query(nativeQuery = true, value = "SELECT e.* FROM engineer_info e WHERE e.user_num=?1")
    EngineerInfo findByUserNum(Long userNum);
}
