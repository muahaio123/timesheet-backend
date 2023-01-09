package com.beaconfire.timesheetdetailservice.domain.response;

import com.beaconfire.timesheetdetailservice.domain.ServiceStatus;
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
