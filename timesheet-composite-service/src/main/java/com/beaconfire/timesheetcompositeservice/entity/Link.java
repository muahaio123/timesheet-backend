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
public class Link {
    private String link;
    private String type;
}
