package com.beaconfire.timesheetcompositeservice.entity;

import lombok.*;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeSheetStatus {
    private String weekEnding;
    private String totalHours;
    private String submissionStatus;
    private String approvalStatus;
}
