package com.beaconfire.timesheetdefaultservice.service;


import com.beaconfire.timesheetdefaultservice.domain.entity.DefaultTimesheet;
import com.beaconfire.timesheetdefaultservice.domain.response.MessageResponse;
import com.beaconfire.timesheetdefaultservice.repository.TimesheetDefaultRepository;
import java.util.Optional;
import lombok.Builder.Default;
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
