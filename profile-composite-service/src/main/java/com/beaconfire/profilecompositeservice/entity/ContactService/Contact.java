package com.beaconfire.profilecompositeservice.entity.ContactService;

import lombok.*;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private Integer id;
    private Integer employeeId;
    private String name;
    private String phoneNumber;
    private String address;
    private Boolean isEmergency;
}
