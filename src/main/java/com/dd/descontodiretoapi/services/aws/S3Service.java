package com.dd.descontodiretoapi.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;

    private String bucketName = "desconto-direto";


    public String uploadFile(String fileName, InputStream fileStream, long fileSize, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileSize);
            metadata.setContentType(contentType);

            s3Client.putObject(bucketName, "comercios/" + fileName, fileStream, metadata);

            return s3Client.getUrl(bucketName, "comercios/" + fileName).toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload para o S3: " + e.getMessage(), e);
        }
    }

    public String uploadFotoProduto(String fileName, InputStream fileStream, long fileSize, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileSize);
            metadata.setContentType(contentType);

            String objectKey = "produtos/" + fileName; 

            s3Client.putObject(bucketName, objectKey, fileStream, metadata);

            return s3Client.getUrl(bucketName, objectKey).toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload para o S3: " + e.getMessage(), e);
        }
    }

    public String uploadFotoPanfleto(String fileName, InputStream fileStream, long fileSize, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileSize);
            metadata.setContentType(contentType);

            String objectKey = "panfletos/" + fileName;

            s3Client.putObject(bucketName, objectKey, fileStream, metadata);

            return s3Client.getUrl(bucketName, objectKey).toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload para o S3: " + e.getMessage(), e);
        }
    }
}
