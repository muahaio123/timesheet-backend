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
public class TimesheetDetail {
    private String id;
    private String employeeId;
    private String weekEnding;
    private String totalHours;
    private Day day1;
    private Day day2;
    private Day day3;
    private Day day4;
    private Day day5;
    private Day day6;
    private Day day7;
    private List<Link> linkList;
}
