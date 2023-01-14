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
public class Day {
    private String id;
    private String day;
    private String date;
    private String startTime;
    private String endTime;
    private String totalHours;
    private String dayType;
}
