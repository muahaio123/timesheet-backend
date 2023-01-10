package com.beaconfire.timesheetdetailservice.service;

import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetSummary;
import com.beaconfire.timesheetdetailservice.repository.TimesheetDefaultRepository;
import com.beaconfire.timesheetdetailservice.repository.TimesheetDetailRepository;
import com.beaconfire.timesheetdetailservice.repository.TimesheetSummaryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {
  TimesheetDetailRepository timesheetDetailRepository;
  private final TimesheetSummaryRepository timesheetSummaryRepository;
  private final TimesheetDefaultRepository timesheetDefaultRepository;

  public TimesheetService(TimesheetSummaryRepository timesheetSummaryRepository,
      TimesheetDefaultRepository timesheetDefaultRepository) {
    this.timesheetSummaryRepository = timesheetSummaryRepository;
    this.timesheetDefaultRepository = timesheetDefaultRepository;
  }

  @Autowired
  public void setTimesheetDetailRepository(TimesheetDetailRepository timesheetDetailRepository) {
    this.timesheetDetailRepository = timesheetDetailRepository;
  }

  public void addTimesheetDetail(TimesheetDetail timesheetDetail){
    timesheetDetailRepository.save(timesheetDetail);
  }

  public void updateTimesheetDetail(TimesheetDetail timesheetDetail){
    timesheetDetailRepository.save(timesheetDetail);
  }
  public void deleteTimesheetDetail(TimesheetDetail timesheetDetail){
    timesheetDetailRepository.delete(timesheetDetail);
  }
  public String deleteByWeekEndingAndIdTimesheetDetail(String id,String weekEnding){

    Optional<TimesheetDetail> timesheetDetail=timesheetDetailRepository.findTimesheetDetailByEmployeeIdAndWeekEnding(id,weekEnding);
    if(timesheetDetail.isPresent()){
      timesheetDetailRepository.deleteById(timesheetDetail.get().getId());
      return "delete";
    }
    return "not found";
  }
  public TimesheetDetail findById(String id){
    Optional<TimesheetDetail> timesheetDetail=timesheetDetailRepository.findById(id);
    if(!timesheetDetail.isPresent()){
      throw new RuntimeException("not found timesheet with id: "+id);
    }
    return timesheetDetail.get();
  }
  public Optional<TimesheetDetail> findByEmployeeId(String id){
    return timesheetDetailRepository.findTimesheetDetailByEmployeeId(id);
  }

  public Optional<TimesheetDetail> findByEmployeeIdAndWeekEnding(String id,String weekEnding){
    return timesheetDetailRepository.findTimesheetDetailByEmployeeIdAndWeekEnding(id,weekEnding);
  }
  public Optional<TimesheetDetail> findByWeekEnding(String weekEnding){
    return timesheetDetailRepository.findTimesheetDetailByWeekEnding(weekEnding);
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

  public List<TimesheetDetail> findAll(){
    return timesheetDetailRepository.findAll();
  }
  public List<TimesheetDetail> findAllByEmployeeId(String employeeId){
    return timesheetDetailRepository.findTimesheetDetailsByEmployeeId(employeeId);
  }


}
