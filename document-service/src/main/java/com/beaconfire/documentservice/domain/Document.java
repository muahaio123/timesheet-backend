package com.beaconfire.documentservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="documents")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer ID;

    @Column(name="employeeId")
    private Integer EmployeeID;

    @Column(name="timesheetId")
    private Integer TimeSheetId;

    @Column(name="type")
    private String Type;

    @Column(name="link")
    private String Link;
}
