package com.beaconfire.documentservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseStatus {
    private Boolean success;
    private String kind;
    private String message;
}