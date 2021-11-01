package com.project.sroa_inqueryschedule_msa.repository;

import com.project.sroa_inqueryschedule_msa.model.EngineerInfo;
import com.project.sroa_inqueryschedule_msa.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByScheduleNum(Long scheduleNum);


    @Query(nativeQuery = true, value = "SELECT s.* FROM schedule s WHERE ( s.start_date like concat('%', ?2, '%') AND s.end_date is null )AND s.user_num = (\n" +
            "SELECT u.user_num FROM user_info u WHERE u.id=?1 ); ")
    List<Schedule> findAllStartDateByUserIdAndDate(String id, String date);


    @Query(nativeQuery = true, value = "SELECT s.* FROM schedule s WHERE s.end_date like concat('%', ?2, '%') AND s.user_num = (\n" +
            "SELECT u.user_num FROM user_info u WHERE u.id=?1 )")
    List<Schedule> findAllEndDateByUserIdAndDate(String id, String date);

    @Query(nativeQuery = true, value = "SELECT s.* FROM schedule s WHERE s.start_date like concat('%', ?2, '%')  AND s.engineer_num = (\n" +
            "SELECT e.engineer_num FROM engineer_info e WHERE e.engineer_num=?1 ) ")
    List<Schedule> findAllStartDateByEngineerNumAndDate(Long engineerNum, String today);

    @Query(nativeQuery = true, value = "SELECT s.* FROM schedule s WHERE s.end_date like concat('%', ?2, '%') AND s.engineer_num = (\n" +
            "SELECT e.engineer_num FROM engineer_info e WHERE e.engineer_num=?1 )")
    List<Schedule> findAllEndDateByEngineerNumAndDate(Long engineerNum, String today);

    // 현재 창고에 있는 물품 -> 입고, 수리완료, 반납예약완료
    @Query(nativeQuery = true, value = "SELECT s.* FROM schedule s WHERE s.status IN(2,3,4) AND s.engineer_num =?1")
    List<Schedule> findAllWarehousingSchedule(Long engineerNum);
}
