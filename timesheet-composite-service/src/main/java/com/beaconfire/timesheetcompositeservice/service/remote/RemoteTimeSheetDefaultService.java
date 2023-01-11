package com.beaconfire.timesheetcompositeservice.service.remote;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Richard Zhang
 */
@FeignClient("timesheet-default-service")
public interface RemoteTimeSheetDefaultService {
}
