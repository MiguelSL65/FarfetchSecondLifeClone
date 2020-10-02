package com.luxclusifmiguel.challenge.backend.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleDriveUtil {

    /**
     * Method to get the credentials from google
     *
     * @return the credentials
     */
    private static Credential authorizeDrive() throws IOException, GeneralSecurityException {

        InputStream in = SheetsUtil.class.getResourceAsStream("/credentials.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> scopes = Arrays.asList(DriveScopes.DRIVE_FILE);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("admin");
    }

    /**
     * Get google drive service
     *
     * @return the Drive
     */
    private static Drive getDriveServices() throws IOException, GeneralSecurityException {

        Credential credential = authorizeDrive();

        // instantiating a client
        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Challenge")
                .build();
    }

    /**
     * Writes a file to a google driver folder
     *
     * @param multipartFile the file to write
     */
    public static void addToDrive(MultipartFile multipartFile) throws IOException, GeneralSecurityException {

        String folderId = "1Ipa9FiSFzx1JXhe9pOCHRllelXglbHWv";

        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getName());

        fileMetadata.setParents(Collections.singletonList(folderId));
        //fileMetadata.setMimeType("application/vnd.google-apps.photo");

        FileContent mediaContent = new FileContent("image/jpeg", new java.io.File(multipartFile.getName()));

        getDriveServices().files().create(fileMetadata, mediaContent)
                .setSupportsTeamDrives(true)
                .execute();
    }
}
