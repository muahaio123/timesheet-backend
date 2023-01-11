package com.beaconfire.timesheetcompositeservice.entity;

import lombok.*;

import java.util.List;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimesheetSummary {
    private String id;
    private String employeeId;
    private List<TimeSheetStatus> timesheetStatusList;
    private String comment;
}
