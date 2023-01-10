package com.beaconfire.timesheetdefaultservice.repository;

import com.beaconfire.timesheetdefaultservice.domain.entity.DefaultTimesheet;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesheetDefaultRepository extends MongoRepository<DefaultTimesheet, String> {

  Optional<DefaultTimesheet> findByEmployeeId(String employeeId);
}
