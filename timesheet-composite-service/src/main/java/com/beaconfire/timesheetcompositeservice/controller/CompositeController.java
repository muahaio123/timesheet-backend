package com.beaconfire.timesheetcompositeservice.controller;

import com.beaconfire.timesheetcompositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Zhang
 */

@RestController
@RequestMapping("timesheet-composite")
public class CompositeController {
    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }
}
