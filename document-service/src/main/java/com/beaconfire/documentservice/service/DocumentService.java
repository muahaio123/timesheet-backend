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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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

    @Transactional
    public void deleteByDocumentId(Integer id){
        documentDAO.deleteByDocumentId(id);
    }

    @Transactional
    public List<Document> getDocumentById(Integer id){
        return documentDAO.getDocumentById(id);
    }

    @Transactional
    public List<Document> getDocumentByEmployeeId(Integer employeeId){
        return documentDAO.getDocumentByEmployeeId(employeeId);
    }

    public List<Bucket> getBucketList(){
        return amazonS3.listBuckets();
    }



    public List<S3ObjectSummary> listObjects(){
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    private  File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private  String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private  void uploadFileTos3bucket(String fileName, File file) {
        amazonS3.putObject(bucketName, fileName, file);
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
//            fileUrl = "http://s3." + awsConfig.getEndPointUrl() + ".amazonaws.com/" + awsConfig.getBucketName() + "/" + fileName;
            fileUrl = "http://" + bucketName + ".s3.amazonaws.com/" + fileName;

            uploadFileTos3bucket(fileName, file);
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }


    public void deleteFileFromS3(String link){
        amazonS3.deleteObject(bucketName, link);
    }



}
