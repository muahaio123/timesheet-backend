package com.beaconfire.timesheetdetailservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TimesheetStatus {
  String weekEnding;
  String totalHours;
  String submissionStatus;
  String approvalStatus;
}
