package com.beaconfire.timesheetdetailservice.scheduling;

import com.beaconfire.timesheetdetailservice.domain.entity.DefaultTimesheet;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetStatus;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetSummary;
import com.beaconfire.timesheetdetailservice.service.TimesheetDefaultService;
import com.beaconfire.timesheetdetailservice.service.TimesheetService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeekUpdateScheduler {

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

  public static String dateToStr(Date dateDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = formatter.format(dateDate);
    return dateString;
  }

  @Scheduled(cron = "* 1 * * 6 ?")
  public void scheduleTaskUsingCronExpression() {
    Date date = new Date();
    //TODO hardcode employeeId;
    //TODO should get all employee;
    String employeeId = "1";
    System.out.println("datetoStr"+dateToStr(date));
    String day6=dateToStr(new Date(date.getTime()- (24 * 3600000)));
    String day5=dateToStr(new Date(date.getTime()- (2 * 24 * 3600000)));
    String day4=dateToStr(new Date(date.getTime()- (3 * 24 * 3600000)));
    String day3=dateToStr(new Date(date.getTime()- (4 * 24 * 3600000)));
    String day2=dateToStr(new Date(date.getTime()- (5 * 24 * 3600000)));
    String day1=dateToStr(new Date(date.getTime()- (6 * 24 * 3600000)));
    Optional<DefaultTimesheet> timesheet = timesheetDefaultService.findByEmployeeId(employeeId);
    timesheet.get().getDay1().setDate(day1);
    timesheet.get().getDay2().setDate(day2);
    timesheet.get().getDay3().setDate(day3);
    timesheet.get().getDay4().setDate(day4);
    timesheet.get().getDay5().setDate(day5);
    timesheet.get().getDay6().setDate(day6);
    timesheet.get().getDay7().setDate(dateToStr(date));
    TimesheetDetail timesheetDetail=TimesheetDetail.builder()
        .employeeId(employeeId)
        .totalHours(timesheet.get().getTotalHours())
        .weekEnding(dateToStr(date))
        .day1(timesheet.get().getDay1())
        .day2(timesheet.get().getDay2())
        .day3(timesheet.get().getDay3())
        .day4(timesheet.get().getDay4())
        .day5(timesheet.get().getDay5())
        .day6(timesheet.get().getDay6())
        .day7(timesheet.get().getDay7())
        .build();

    timesheetService.addTimesheetDetail(timesheetDetail);
  }

}
