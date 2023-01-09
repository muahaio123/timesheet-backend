package com.beaconfire.timesheetdefaultservice.controller;

import com.beaconfire.timesheetdefaultservice.domain.ServiceStatus;
import com.beaconfire.timesheetdefaultservice.domain.entity.DefaultTimesheet;
import com.beaconfire.timesheetdefaultservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdefaultservice.domain.response.MessageResponse;
import com.beaconfire.timesheetdefaultservice.service.TimesheetDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/defaultTimesheet")
public class TimesheetDefaultController {
  TimesheetDefaultService timesheetDefaultService;
  @Autowired
  void setTimesheetDefaultService(TimesheetDefaultService timesheetDefaultService){
    this.timesheetDefaultService=timesheetDefaultService;
  }

  @PostMapping("/save")
  public MessageResponse saveTimesheetDetail(@RequestBody TimesheetDetail timesheetDetail) {

    //TODO maybe need to getId from token rather than get from request body
    //the following code is used in previous team project to get Id
//    String header = request.getHeader(BeaconFireHrConstant.BEACONFIRE_SESSION);
//    Gson gson = new Gson();
//    BasicDataModel basicDataModel = gson.fromJson(header, JwtModel.class).getBasicDataModel();
    if(timesheetDefaultService.findByEmployeeId(timesheetDetail.getEmployeeId()).isPresent()){
      DefaultTimesheet defaultTimesheet=timesheetDefaultService.findByEmployeeId(
          timesheetDetail.getEmployeeId()).get();
      defaultTimesheet.setEmployeeId(timesheetDetail.getEmployeeId());
      defaultTimesheet.setDay1(timesheetDetail.getDay1());
      defaultTimesheet.setDay2(timesheetDetail.getDay2());
      defaultTimesheet.setDay3(timesheetDetail.getDay3());
      defaultTimesheet.setDay4(timesheetDetail.getDay4());
      defaultTimesheet.setDay5(timesheetDetail.getDay5());
      defaultTimesheet.setDay6(timesheetDetail.getDay6());
      defaultTimesheet.setDay7(timesheetDetail.getDay7());

      timesheetDefaultService.addOrUpdateTimesheetDefault(defaultTimesheet);

      return MessageResponse.builder()
          .serviceStatus(
              ServiceStatus.builder()
                  .success(true)
                  .build()
          )
          .message("DefaultTimesheetDetail updated")
          .build();
    }
    else{
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


      return MessageResponse.builder()
          .serviceStatus(
              ServiceStatus.builder()
                  .success(true)
                  .build()
          )
          .message("New DefaultTimesheetDetail created")
          .build();
    }


  }

  @GetMapping("/employee/{id}")
  public DefaultTimesheet findTimesheetByEmployeeId(@PathVariable String id){
    if(timesheetDefaultService.findByEmployeeId(id).isPresent()){
      return timesheetDefaultService.findByEmployeeId(id).get();
    }
    else{
      throw new RuntimeException("not found employeeId related data");
    }
  }

}
