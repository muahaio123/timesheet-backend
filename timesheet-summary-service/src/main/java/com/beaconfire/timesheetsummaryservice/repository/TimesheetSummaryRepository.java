package com.beaconfire.timesheetsummaryservice.repository;

import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetSummary;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TimesheetSummaryRepository extends MongoRepository<TimesheetSummary,String> {
  Optional<TimesheetSummary> findTimesheetSummaryByEmployeeId(String employeeId);

}
