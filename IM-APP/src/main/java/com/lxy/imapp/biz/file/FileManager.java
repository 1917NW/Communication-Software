package com.lxy.imapp.biz.file;

import io.minio.*;

import java.io.File;
import java.io.InputStream;

public class FileManager {
    public static void main(String[] args) {
        upLoadFile("im-test", "1505382/123.png", "C:\\Users\\26327\\Desktop\\毕业设计\\通讯软件\\UI\\123.png");
    }

    public static void upLoadFile(String bucketName, String objectName, String localFilePath){
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(MinioProperties.url)
                            .credentials(MinioProperties.accessKey, MinioProperties.secretKey)
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                System.out.println("Bucket "+ bucketName +" already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(localFilePath)
                            .build());
            System.out.println(String.format("object {} has sent to bucket {}", objectName, bucketName));
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public static void downLoadFile(String bucketName, String objectName, String localFilePath){
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(MinioProperties.url)
                            .credentials(MinioProperties.accessKey, MinioProperties.secretKey)
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                System.out.println(String.format("Bucket {} already exists.", bucketName));
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(localFilePath)
                            .build());
            System.out.println(
                    String.format("object:{} has downloaded to local:{}", objectName, localFilePath));
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
