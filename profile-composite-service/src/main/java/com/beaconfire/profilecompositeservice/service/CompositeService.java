package com.beaconfire.profilecompositeservice.service;

import com.beaconfire.profilecompositeservice.service.remote.RemoteContactService;
import com.beaconfire.profilecompositeservice.service.remote.RemoteDocumentService;
import com.beaconfire.profilecompositeservice.service.remote.RemoteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Zhang
 */
@Service
public class CompositeService {

    private RemoteContactService contactService;
    private RemoteDocumentService documentService;
    private RemoteEmployeeService employeeService;

    @Autowired
    public void setContactService(RemoteContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setDocumentService(RemoteDocumentService documentService) {
        this.documentService = documentService;
    }


    @Autowired
    public void setEmployeeService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
