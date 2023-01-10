package com.beaconfire.timesheetdetailservice.repository;

import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetSummary;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesheetSummaryRepository extends MongoRepository<TimesheetSummary,String> {
  Optional<TimesheetSummary> findTimesheetSummaryByEmployeeId(String employeeId);


}
