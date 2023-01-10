package com.beaconfire.timesheetsummaryservice.domain.response;

import com.beaconfire.timesheetsummaryservice.domain.ServiceStatus;
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
