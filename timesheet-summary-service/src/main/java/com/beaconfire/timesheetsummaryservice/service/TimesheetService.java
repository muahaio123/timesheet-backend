package com.beaconfire.timesheetsummaryservice.service;

import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetSummary;
import com.beaconfire.timesheetsummaryservice.repository.TimesheetDetailRepository;
import com.beaconfire.timesheetsummaryservice.repository.TimesheetSummaryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {
  TimesheetDetailRepository timesheetDetailRepository;
  TimesheetSummaryRepository timesheetSummaryRepository;
  @Autowired
  public void setTimesheetDetailRepository(TimesheetDetailRepository timesheetDetailRepository) {
    this.timesheetDetailRepository = timesheetDetailRepository;
  }
  @Autowired
  public void setTimesheetSummaryRepository(TimesheetSummaryRepository timesheetSummaryRepository){
    this.timesheetSummaryRepository=timesheetSummaryRepository;
  }


  public Optional<TimesheetSummary> findTimesheetSummaryByEmployeeId(String employeeId){
    return timesheetSummaryRepository.findTimesheetSummaryByEmployeeId(employeeId);
  }
  public void saveTimesheetSummary(TimesheetSummary timesheetSummary){
    timesheetSummaryRepository.save(timesheetSummary);
  }
  public void deleteTimesheetSummary(TimesheetSummary timesheetSummary){
    timesheetSummaryRepository.delete(timesheetSummary);
  }
}
