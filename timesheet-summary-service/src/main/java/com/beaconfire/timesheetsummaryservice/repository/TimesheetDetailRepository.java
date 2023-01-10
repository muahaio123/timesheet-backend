package com.beaconfire.timesheetsummaryservice.repository;

import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesheetDetailRepository extends MongoRepository<TimesheetDetail, String> {
    Optional<TimesheetDetail> findTimesheetDetailByEmployeeId(String id);
    List<TimesheetDetail> findTimesheetDetailsByEmployeeId(String employeeId);
    Optional<TimesheetDetail> findTimesheetDetailByEmployeeIdAndWeekEnding(String employeeId,String weekEnding);
    Optional<TimesheetDetail> findTimesheetDetailByWeekEnding(String weekEnding);
}
