package com.beaconfire.timesheetsummaryservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class TimesheetSummary {
  @Id
  String id;
  String employeeId;
  List<TimesheetStatus> timesheetStatusList;
  String comment;


}
