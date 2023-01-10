package com.beaconfire.timesheetsummaryservice.controller;

import com.beaconfire.timesheetsummaryservice.domain.ServiceStatus;
import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetSummary;
import com.beaconfire.timesheetsummaryservice.domain.response.MessageResponse;
import com.beaconfire.timesheetsummaryservice.service.TimesheetService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timesheet/summary")
public class TimesheetController {
  TimesheetService timesheetService;
  @Autowired
  void setTimesheetService(TimesheetService timesheetService){
    this.timesheetService=timesheetService;
  }
  @GetMapping("/{id}")
  public TimesheetSummary getTimesheetSummaryById(@PathVariable String id){
    if (timesheetService.findTimesheetSummaryByEmployeeId(id).isPresent()){
      return timesheetService.findTimesheetSummaryByEmployeeId(id).get();
    }
    else{
      return new TimesheetSummary();
    }
  }
  @PostMapping("/save")
  public MessageResponse saveTimesheetSummary(@RequestBody TimesheetSummary timesheetSummary){
    timesheetService.saveTimesheetSummary(timesheetSummary);
    return MessageResponse.builder()
        .serviceStatus(
            ServiceStatus.builder()
                .success(true)
                .build()
        )
        .message("New TimeSheet summary saved")
        .build();
  }
  @PostMapping("/")
  @DeleteMapping("/delete/{id}")
  public MessageResponse deleteTimesheetSummary(@PathVariable String id){
    TimesheetSummary timesheetSummary=timesheetService.findTimesheetSummaryByEmployeeId(id).get();
    timesheetService.deleteTimesheetSummary(timesheetSummary);
    return MessageResponse.builder()
        .serviceStatus(
            ServiceStatus.builder()
                .success(true)
                .build()
        )
        .message("Sucuessfully deleted")
        .build();
  }
}
