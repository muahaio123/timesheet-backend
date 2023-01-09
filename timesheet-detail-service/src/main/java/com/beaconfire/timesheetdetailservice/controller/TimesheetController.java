package com.beaconfire.timesheetdetailservice.controller;

import com.beaconfire.timesheetdetailservice.domain.ServiceStatus;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdetailservice.domain.entity.DefaultTimesheet;
import com.beaconfire.timesheetdetailservice.domain.response.MessageResponse;
import com.beaconfire.timesheetdetailservice.service.TimesheetDefaultService;
import com.beaconfire.timesheetdetailservice.service.TimesheetService;
import java.sql.Time;
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
  void setTimesheetService(TimesheetService timesheetService){
    this.timesheetService=timesheetService;
  }
  @Autowired
  void setTimesheetDefaultService(TimesheetDefaultService timesheetDefaultService){
    this.timesheetDefaultService=timesheetDefaultService;
  }
  @PostMapping("/add")
  public MessageResponse addTimesheetDetail(@RequestBody TimesheetDetail timesheetDetail) {

    //TODO maybe need to getId from token rather than get from request body
    //the following code is used in previous team project to get Id
//    String header = request.getHeader(BeaconFireHrConstant.BEACONFIRE_SESSION);
//    Gson gson = new Gson();
//    BasicDataModel basicDataModel = gson.fromJson(header, JwtModel.class).getBasicDataModel();

    TimesheetDetail timesheetDetail1=TimesheetDetail.builder()
        .id(timesheetDetail.getId())
        .employeeId(timesheetDetail.getEmployeeId())
        .weekEnding(timesheetDetail.getWeekEnding())
        .day1(timesheetDetail.getDay1())
        .day2(timesheetDetail.getDay2())
        .day3(timesheetDetail.getDay3())
        .day4(timesheetDetail.getDay4())
        .day5(timesheetDetail.getDay5())
        .day6(timesheetDetail.getDay6())
        .day7(timesheetDetail.getDay7())
        .build();

    timesheetService.addTimesheetDetail(timesheetDetail1);

    if(!timesheetDefaultService.findByEmployeeId(timesheetDetail.getEmployeeId()).isPresent()){
      DefaultTimesheet defaultTimesheet=DefaultTimesheet.builder()
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
  @PostMapping("/update")
  public MessageResponse updateTimesheetDetail(@RequestBody TimesheetDetail timesheetDetail) {
    //TODO maybe need to getId from token rather than get from request body
    String employeeId=timesheetDetail.getEmployeeId();
    String weekEnding=timesheetDetail.getWeekEnding();
    Optional<TimesheetDetail> timesheetDetail1=timesheetService.findByEmployeeIdAndWeekEnding(employeeId,weekEnding);
    if(timesheetDetail1.isPresent()){
      TimesheetDetail timesheetDetail2=TimesheetDetail.builder()
          .id(timesheetDetail1.get().getId())
          .employeeId(timesheetDetail.getEmployeeId())
          .weekEnding(timesheetDetail.getWeekEnding())
          .day1(timesheetDetail.getDay1())
          .day2(timesheetDetail.getDay2())
          .day3(timesheetDetail.getDay3())
          .day4(timesheetDetail.getDay4())
          .day5(timesheetDetail.getDay5())
          .day6(timesheetDetail.getDay6())
          .day7(timesheetDetail.getDay7())
          .build();
      timesheetService.updateTimesheetDetail(timesheetDetail2);

      return MessageResponse.builder()
          .serviceStatus(
              ServiceStatus.builder()
                  .success(true)
                  .build()
          )
          .message("TimesheetDetail updated")
          .build();
    }
    else{
      TimesheetDetail timesheetDetail2=TimesheetDetail.builder()
          .id(timesheetDetail.getId())
          .employeeId(timesheetDetail.getEmployeeId())
          .weekEnding(timesheetDetail.getWeekEnding())
          .day1(timesheetDetail.getDay1())
          .day2(timesheetDetail.getDay2())
          .day3(timesheetDetail.getDay3())
          .day4(timesheetDetail.getDay4())
          .day5(timesheetDetail.getDay5())
          .day6(timesheetDetail.getDay6())
          .day7(timesheetDetail.getDay7())
          .build();

      timesheetService.addTimesheetDetail(timesheetDetail2);

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
  public List<TimesheetDetail> findAllTimesheetDetails(){
    return (List<TimesheetDetail>) timesheetService.findAll();
  }
  @GetMapping("/employee/{id}")
  public TimesheetDetail findTimesheetByEmployeeId(@PathVariable String id){
    if(timesheetService.findByEmployeeId(id).isPresent()){
      return timesheetService.findByEmployeeId(id).get();
    }
    else{
      throw new RuntimeException("not found employeeId related data");
    }
  }
  @DeleteMapping("/delete/{id}")
  public String deleteTimesheetServiceById(@PathVariable String id) {
    timesheetService.deleteByIdTimesheetDetail(id);
    return "success";
  }
}
