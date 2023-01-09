package com.beaconfire.timesheetdetailservice.repository;

import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesheetDetailRepository extends MongoRepository<TimesheetDetail, String> {
    Optional<TimesheetDetail> findTimesheetDetailByEmployeeId(String id);
    Optional<TimesheetDetail> findTimesheetDetailByEmployeeIdAndWeekEnding(String employeeId,String weekEnding);
}
