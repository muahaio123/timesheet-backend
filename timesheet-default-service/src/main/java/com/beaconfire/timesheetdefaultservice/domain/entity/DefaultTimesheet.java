package com.beaconfire.timesheetdefaultservice.domain.entity;

import com.beaconfire.timesheetdefaultservice.domain.entity.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class DefaultTimesheet {
  @Id
  String id;
  String employeeId;
  String totalHours;
  Day day1;
  Day day2;
  Day day3;
  Day day4;
  Day day5;
  Day day6;
  Day day7;

}
