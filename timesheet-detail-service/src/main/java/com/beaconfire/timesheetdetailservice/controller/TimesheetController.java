package com.beaconfire.timesheetdetailservice.controller;

import com.beaconfire.timesheetdetailservice.domain.ServiceStatus;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdetailservice.domain.entity.DefaultTimesheet;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetStatus;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetSummary;
import com.beaconfire.timesheetdetailservice.domain.response.MessageResponse;
import com.beaconfire.timesheetdetailservice.service.TimesheetDefaultService;
import com.beaconfire.timesheetdetailservice.service.TimesheetService;
import java.util.ArrayList;
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
@RequestMapping("/timesheet/detail")
public class TimesheetController {

  TimesheetService timesheetService;
  TimesheetDefaultService timesheetDefaultService;

  @Autowired
  void setTimesheetService(TimesheetService timesheetService) {
    this.timesheetService = timesheetService;
  }

  @Autowired
  void setTimesheetDefaultService(TimesheetDefaultService timesheetDefaultService) {
    this.timesheetDefaultService = timesheetDefaultService;
  }

  @PostMapping("/save")
  public MessageResponse saveTimesheetDetail(@RequestBody TimesheetDetail timesheetDetail) {
//    TODO maybe need to getId from token rather than get from request body
    String employeeId = timesheetDetail.getEmployeeId();
    String weekEnding = timesheetDetail.getWeekEnding();
    Optional<TimesheetDetail> timesheetDetail1 = timesheetService.findByEmployeeIdAndWeekEnding(
        employeeId, weekEnding);
    if (timesheetDetail1.isPresent()) {
      TimesheetDetail timesheetDetail2 = TimesheetDetail.builder()
          .id(timesheetDetail1.get().getId())
          .employeeId(timesheetDetail.getEmployeeId())
          .weekEnding(timesheetDetail.getWeekEnding())
          .totalHours(timesheetDetail.getTotalHours())
          .day1(timesheetDetail.getDay1())
          .day2(timesheetDetail.getDay2())
          .day3(timesheetDetail.getDay3())
          .day4(timesheetDetail.getDay4())
          .day5(timesheetDetail.getDay5())
          .day6(timesheetDetail.getDay6())
          .day7(timesheetDetail.getDay7())
          .build();
      timesheetService.updateTimesheetDetail(timesheetDetail2);

      if (timesheetService.findTimesheetSummaryByEmployeeId(timesheetDetail.getEmployeeId())
          .isPresent()) {

      }
      ArrayList<TimesheetStatus> statusArrayList = new ArrayList<>();
      if (timesheetService.findTimesheetSummaryByEmployeeId(timesheetDetail.getEmployeeId())
          .isPresent()) {
        TimesheetSummary timesheetSummary = timesheetService.findTimesheetSummaryByEmployeeId(
            timesheetDetail.getEmployeeId()).get();
        statusArrayList = (ArrayList<TimesheetStatus>) timesheetSummary.getTimesheetStatusList();
        for (TimesheetStatus timesheetStatus : statusArrayList) {
          if (timesheetStatus.getWeekEnding().equals(timesheetDetail.getWeekEnding())) {
            timesheetStatus.setTotalHours(timesheetDetail.getTotalHours());
            break;
          }
        }
        timesheetSummary.setTimesheetStatusList(statusArrayList);
        timesheetService.saveTimesheetSummary(timesheetSummary);
      }

      return MessageResponse.builder()
          .serviceStatus(
              ServiceStatus.builder()
                  .success(true)
                  .build()
          )
          .message("TimesheetDetail updated")
          .build();
    } else {
      TimesheetDetail timesheetDetail2 = TimesheetDetail.builder()
          .id(timesheetDetail.getId())
          .employeeId(timesheetDetail.getEmployeeId())
          .weekEnding(timesheetDetail.getWeekEnding())
          .totalHours(timesheetDetail.getTotalHours())
          .day1(timesheetDetail.getDay1())
          .day2(timesheetDetail.getDay2())
          .day3(timesheetDetail.getDay3())
          .day4(timesheetDetail.getDay4())
          .day5(timesheetDetail.getDay5())
          .day6(timesheetDetail.getDay6())
          .day7(timesheetDetail.getDay7())
          .build();

      timesheetService.addTimesheetDetail(timesheetDetail2);

      ArrayList<TimesheetStatus> statusArrayList = new ArrayList<>();
      if (timesheetService.findTimesheetSummaryByEmployeeId(timesheetDetail.getEmployeeId())
          .isPresent()) {
        TimesheetSummary timesheetSummary = timesheetService.findTimesheetSummaryByEmployeeId(
            timesheetDetail.getEmployeeId()).get();
        statusArrayList = (ArrayList<TimesheetStatus>) timesheetSummary.getTimesheetStatusList();
        for (TimesheetStatus timesheetStatus : statusArrayList) {
          if (timesheetStatus.getWeekEnding().equals(timesheetDetail.getWeekEnding())) {
            timesheetStatus.setTotalHours(timesheetDetail.getTotalHours());
            break;
          }
        }
        statusArrayList.add(TimesheetStatus.builder()
            .weekEnding(timesheetDetail.getWeekEnding())
            .approvalStatus("N/A")
            .submissionStatus("Not Started")
            .totalHours(timesheetDetail.getTotalHours())
            .build());
        timesheetSummary.setTimesheetStatusList(statusArrayList);
        timesheetService.saveTimesheetSummary(timesheetSummary);
      } else {
        statusArrayList.add(TimesheetStatus.builder()
            .weekEnding(timesheetDetail.getWeekEnding())
            .approvalStatus("N/A")
            .submissionStatus("Not Started")
            .totalHours(timesheetDetail.getTotalHours())
            .build());
        TimesheetSummary timesheetSummary = TimesheetSummary.builder()
            .employeeId(timesheetDetail.getEmployeeId())
            .timesheetStatusList(statusArrayList)
            .build();
        timesheetService.saveTimesheetSummary(timesheetSummary);
      }
      if (!timesheetDefaultService.findByEmployeeId(timesheetDetail.getEmployeeId()).isPresent()) {
        DefaultTimesheet defaultTimesheet = DefaultTimesheet.builder()
            .id(timesheetDetail.getId())
            .employeeId(timesheetDetail.getEmployeeId())
            .day1(timesheetDetail.getDay1())
            .day2(timesheetDetail.getDay2())
            .day3(timesheetDetail.getDay3())
            .day4(timesheetDetail.getDay4())
            .day5(timesheetDetail.getDay5())
            .day6(timesheetDetail.getDay6())
            .day7(timesheetDetail.getDay7())
            .build();

        timesheetDefaultService.addOrUpdateTimesheetDefault(defaultTimesheet);

      }

      return MessageResponse.builder()
          .serviceStatus(
              ServiceStatus.builder()
                  .success(true)
                  .build()
          )
          .message("New TimesheetDetail created")
          .build();

    }

  }

  @GetMapping("/all")
  public List<TimesheetDetail> findAllTimesheetDetails() {
    return (List<TimesheetDetail>) timesheetService.findAll();
  }

  @GetMapping("/employee/{id}")
  public List<TimesheetDetail> findTimesheetByEmployeeId(@PathVariable String id) {
    return timesheetService.findAllByEmployeeId(id);
  }

  @GetMapping("/employee/weekEnding/{id}")
  public TimesheetDetail findTimesheetByWeekEnding(@PathVariable String id) {
    //TODO Get the employeeId
    String employeeId = "1";
    return timesheetService.findByEmployeeIdAndWeekEnding(employeeId, id).get();
  }

  @DeleteMapping("/delete/{weekEnding}")
  public String deleteTimesheetById(@PathVariable String weekEnding) {
    //TODO also delete summary part
    //TODO Get the employeeId
    String employeeId = "1";
    timesheetService.deleteByWeekEndingAndIdTimesheetDetail(employeeId, weekEnding);
    Optional<TimesheetSummary> timesheetSummary = timesheetService.findTimesheetSummaryByEmployeeId(
        employeeId);
    System.out.println("here" + timesheetSummary.get());
    if (timesheetSummary.isPresent()) {
      ArrayList<TimesheetStatus> statusArrayList = (ArrayList<TimesheetStatus>) timesheetSummary.get()
          .getTimesheetStatusList();
      for (TimesheetStatus timesheetStatus : statusArrayList) {
        if (timesheetStatus.getWeekEnding().equals(weekEnding)) {
          System.out.println("i'm here to delete" + weekEnding);
          statusArrayList.remove(timesheetStatus);
          if (statusArrayList.size() == 0) {
            timesheetService.deleteTimesheetSummary(timesheetSummary.get());
          }
          timesheetSummary.get().setTimesheetStatusList(statusArrayList);
          timesheetService.saveTimesheetSummary(timesheetSummary.get());
          break;
        }
      }
    }
    return "success";
  }
}
