package com.beaconfire.profilecompositeservice.entity.DocumentService;

import lombok.*;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    private Integer id;
    private Integer employeeID;
    private Integer timeSheetId;
    private String type;
    private String link;
}
