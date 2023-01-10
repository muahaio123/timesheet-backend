package com.beaconfire.timesheetsummaryservice.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalStatus {
  String weekEnding;
  String approvalStatus;
}
