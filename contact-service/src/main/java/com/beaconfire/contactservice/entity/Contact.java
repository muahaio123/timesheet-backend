package com.beaconfire.contactservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity(name="contacts")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name="contacts")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer employeeId;
    private String name;
    private String phone;
    private String address;
    private Boolean isEmergency;
}
