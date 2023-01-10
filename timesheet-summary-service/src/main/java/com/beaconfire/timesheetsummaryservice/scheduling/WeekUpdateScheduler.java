package com.beaconfire.timesheetsummaryservice.scheduling;

import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetStatus;
import com.beaconfire.timesheetsummaryservice.domain.entity.TimesheetSummary;
import com.beaconfire.timesheetsummaryservice.service.TimesheetService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class WeekUpdateScheduler {
  TimesheetService timesheetService;
  @Autowired
  void setTimesheetService(TimesheetService timesheetService){
    this.timesheetService=timesheetService;
  }
  public static String dateToStr(java.util.Date dateDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = formatter.format(dateDate);
    return dateString;
  }
  @Scheduled(cron = "* 1 * * 6 ?")
  public void scheduleTaskUsingCronExpression() {
    Date date=new Date();
    //TODO hardcode employeeId;
    //TODO should get all employee;
    String employeeId="1";
    Optional<TimesheetSummary> timesheetStatusList =timesheetService.findTimesheetSummaryByEmployeeId(employeeId);
    timesheetStatusList.get().getTimesheetStatusList().add(TimesheetStatus.builder().weekEnding(dateToStr(date)).approvalStatus("N/A").submissionStatus("not started").build());
    timesheetService.saveTimesheetSummary(timesheetStatusList.get());
  }

}
