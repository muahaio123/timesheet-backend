package com.beaconfire.timesheetdetailservice.repository;

import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetSummary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesheetDetailRepository extends MongoRepository<TimesheetDetail, String> {
    Optional<TimesheetDetail> findTimesheetDetailByEmployeeId(String id);
    List<TimesheetDetail> findTimesheetDetailsByEmployeeId(String employeeId);
    Optional<TimesheetDetail> findTimesheetDetailByEmployeeIdAndWeekEnding(String employeeId,String weekEnding);
    Optional<TimesheetDetail> findTimesheetDetailByWeekEnding(String weekEnding);
}
