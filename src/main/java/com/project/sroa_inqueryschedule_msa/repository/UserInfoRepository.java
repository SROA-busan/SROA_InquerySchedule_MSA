package com.project.sroa_inqueryschedule_msa.repository;

import com.project.sroa_inqueryschedule_msa.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


    UserInfo findById(String id);
}
