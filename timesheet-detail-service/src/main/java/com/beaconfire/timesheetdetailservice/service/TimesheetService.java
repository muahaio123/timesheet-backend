package com.beaconfire.timesheetdetailservice.service;

import com.beaconfire.timesheetdetailservice.domain.entity.TimesheetDetail;
import com.beaconfire.timesheetdetailservice.repository.TimesheetDetailRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {
  TimesheetDetailRepository timesheetDetailRepository;
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
  public void deleteByIdTimesheetDetail(String id){
    timesheetDetailRepository.deleteById(id);
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

  public List<TimesheetDetail> findAll(){
    return timesheetDetailRepository.findAll();
  }

}
