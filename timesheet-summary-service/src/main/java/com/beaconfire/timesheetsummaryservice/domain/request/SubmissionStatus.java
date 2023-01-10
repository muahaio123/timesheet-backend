package com.beaconfire.timesheetsummaryservice.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionStatus {
  String weekEnding;
  String submissionStatus;
}
