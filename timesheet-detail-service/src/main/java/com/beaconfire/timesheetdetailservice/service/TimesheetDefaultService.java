package com.beaconfire.timesheetdetailservice.service;


import com.beaconfire.timesheetdetailservice.domain.entity.DefaultTimesheet;
import com.beaconfire.timesheetdetailservice.repository.TimesheetDefaultRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetDefaultService {
  TimesheetDefaultRepository timesheetDefaultRepository;
  @Autowired
  public void setTimesheetDefaultRepository(TimesheetDefaultRepository timesheetDefaultRepository) {
    this.timesheetDefaultRepository = timesheetDefaultRepository;
  }
  public void addOrUpdateTimesheetDefault(DefaultTimesheet defaultTimesheet){
    timesheetDefaultRepository.save(defaultTimesheet);
  }
  public Optional<DefaultTimesheet> findByEmployeeId(String employeeId){
    return timesheetDefaultRepository.findByEmployeeId(employeeId);
  }

}
