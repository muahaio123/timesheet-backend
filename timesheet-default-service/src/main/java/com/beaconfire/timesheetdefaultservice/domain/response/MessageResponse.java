package com.beaconfire.timesheetdefaultservice.domain.response;

import com.beaconfire.timesheetdefaultservice.domain.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageResponse {
    ServiceStatus serviceStatus;
    String message;
}
