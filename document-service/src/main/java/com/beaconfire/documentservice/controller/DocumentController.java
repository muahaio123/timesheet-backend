package com.beaconfire.documentservice.controller;

import com.beaconfire.documentservice.domain.Document;
import com.beaconfire.documentservice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/all")
    public List<Document> getAllDocuments(){
        return documentService.getAllPersonalDocument();
    }
}
