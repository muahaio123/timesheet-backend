package com.beaconfire.timesheetcompositeservice.service;

import com.beaconfire.timesheetcompositeservice.service.remote.RemoteTimeSheetDefaultService;
import com.beaconfire.timesheetcompositeservice.service.remote.RemoteTimesheetDetailService;
import com.beaconfire.timesheetcompositeservice.service.remote.RemoteTimesheetSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Zhang
 */
@Service
public class CompositeService {

    private RemoteTimeSheetDefaultService defaultService;
    private RemoteTimesheetDetailService detailService;
    private RemoteTimesheetSummaryService summaryService;

    @Autowired
    public void setDefaultService(RemoteTimeSheetDefaultService defaultService) {
        this.defaultService = defaultService;
    }

    @Autowired
    public void setDetailService(RemoteTimesheetDetailService detailService) {
        this.detailService = detailService;
    }

    @Autowired
    public void setSummaryService(RemoteTimesheetSummaryService summaryService) {
        this.summaryService = summaryService;
    }
}
