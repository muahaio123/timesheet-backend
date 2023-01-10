package com.beaconfire.documentservice.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.beaconfire.documentservice.DAO.DocumentDAO;
import com.beaconfire.documentservice.domain.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class DocumentService {
    DocumentDAO documentDAO;

    AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    String bucketName;

    @Autowired
    public DocumentService(DocumentDAO documentDAO, AmazonS3 amazonS3){
        this.documentDAO = documentDAO;
        this.amazonS3 = amazonS3;
    }

    @Transactional
    public void addDocument(Document document){
        documentDAO.addDocument(document);
    }

    @Transactional
    public List<Document> getAllPersonalDocument() {
        return documentDAO.getAllDocuments();
    }

    public ResponseEntity<byte[]> uploadFile(String fileName, InputStream file, ObjectMetadata metaData) {
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file, metaData));
        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon S3, but was rejected with an error response for some reason.");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.info("Caught an AmazonClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with S3, " +
                    "such as not being able to access the network.");
            log.info("Error Message: " + ace.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<byte[]> downloadFile(String keyName) throws IOException {
        S3Object s3object = amazonS3.getObject(new GetObjectRequest(bucketName, keyName));
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType(keyName));
        headers.setContentLength(bytes.length);
        headers.setContentDispositionFormData("attachment", keyName);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
