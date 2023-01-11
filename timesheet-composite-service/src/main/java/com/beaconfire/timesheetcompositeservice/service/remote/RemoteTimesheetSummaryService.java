package com.beaconfire.timesheetcompositeservice.service.remote;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Richard Zhang
 */
@FeignClient("timesheet-summary-service")
public interface RemoteTimesheetSummaryService {
}
