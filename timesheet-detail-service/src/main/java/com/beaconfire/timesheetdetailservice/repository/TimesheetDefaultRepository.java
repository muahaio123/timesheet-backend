package com.beaconfire.timesheetdetailservice.repository;

import com.beaconfire.timesheetdetailservice.domain.entity.DefaultTimesheet;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesheetDefaultRepository extends MongoRepository<DefaultTimesheet, String> {

  Optional<DefaultTimesheet> findByEmployeeId(String employeeId);
}
