package com.beaconfire.documentservice.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.beaconfire.documentservice.domain.Document;
import com.beaconfire.documentservice.domain.DocumentResponse;
import com.beaconfire.documentservice.domain.ResponseStatus;
import com.beaconfire.documentservice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/document")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/all")
    public DocumentResponse getAllDocuments(){
        List<Document> documents = documentService.getAllPersonalDocument();
        if (documents.size()>0) {
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Document List has been returned")
                                    .build()
                    )
                    .documentList(documents)
                    .build();
        }
        else{
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("No Documents exists")
                                    .build()
                    )

                    .build();
        }
    }

    @GetMapping("/{id}")
    public DocumentResponse getDocumentById(@PathVariable Integer id){
        List<Document> documents = documentService.getDocumentById(id);
        if (documents.size()>0) {
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Document List has been returned")
                                    .build()
                    )
                    .documentList(documents)
                    .build();
        }
        else{
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("No Documents exists")
                                    .build()
                    )

                    .build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public DocumentResponse getDocumentByEmployeeId(@PathVariable int employeeId){
        List<Document> documents = documentService.getDocumentByEmployeeId(employeeId);
        if (documents.size()>0) {
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Document List has been returned")
                                    .build()
                    )
                    .documentList(documents)
                    .build();
        }
        else{
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("No Documents exists")
                                    .build()
                    )

                    .build();
        }
    }

    @GetMapping("/timesheet/{timesheetId}")
    public DocumentResponse getDocumentByTimeSheetId(@PathVariable int timesheetId){
        List<Document> documents = documentService.getDocumentByTimeSheetId(timesheetId);
        if (documents.size()>0) {
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Document List has been returned")
                                    .build()
                    )
                    .documentList(documents)
                    .build();
        }
        else{
            return DocumentResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("No Documents by the timesheet id exists")
                                    .build()
                    )

                    .build();
        }
    }



    @GetMapping("/object")
    public List<S3ObjectSummary> getObjects(){
        return documentService.listObjects();
    }

    @GetMapping("/buckets")
    public List<String> getAllBuckets(){
        List<Bucket> buckets = documentService.getBucketList();
        List<String> names = buckets.stream().map(Bucket::getName).collect(Collectors.toList());
        return names;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("employeeId") int employeeId, @RequestParam("timeSheetId") int timesheetId,
                                             @RequestParam("type") String type, @RequestParam("file") MultipartFile file) throws Exception{
        try{
            String url  = documentService.uploadFile(file);
            Document document = Document.builder()
                    .EmployeeID(employeeId)
                    .TimeSheetId(timesheetId)
                    .Type(type)
                    .Link(url)
                    .build();
            documentService.addDocument(document);
            return document.getLink();
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFile(@PathVariable("id") int id){
        List<Document> documents = documentService.getDocumentById(id);
        Document document = documents.get(0);
        String url = document.getLink();
        String[] parts = url.split(".com/");
        String fileName = parts[parts.length - 1];
        documentService.deleteFileFromS3(fileName);
        documentService.deleteByDocumentId(id);

    }
}
