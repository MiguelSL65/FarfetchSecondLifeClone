package com.luxclusifmiguel.challenge.backend.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Util class that provides the methods to store info on a google sheet
 * or images in a driver folder
 */
public class SheetsUtil {

    public static final String APPLICATION_NAME = "challenge";
    public static final String SPREADSHEET_ID = "1CO2FfKojdhEvvI8xML2Mpdw9gqs0TT0ZG003eYGhhuA";

    /**
     * Method to get the credentials from google
     *
     * @return the credentials
     * @throws IOException
     * @throws GeneralSecurityException
     */
    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = SheetsUtil.class.getResourceAsStream("/credentials.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS, DriveScopes.DRIVE_FILE);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");
    }

    /**
     * Gets the sheet service
     *
     * @return the sheet
     */
    private static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Writes info to google sheets
     *
     * @param customer customer info to be written
     * @param product product ingo to be written
     */
    public static void addToSheet(Customer customer, Product product)
            throws IOException, GeneralSecurityException {

        Sheets sheetsService = SheetsUtil.getSheetsService();

        ValueRange appendBody = new ValueRange()
                .setValues(Collections.singletonList(
                        Arrays.asList(customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                                customer.getCountry(), customer.getPhone(), product.getBrand(),
                                product.getCondition(), product.getSize())
                ));

        sheetsService.spreadsheets().values()
                .append(SheetsUtil.SPREADSHEET_ID, "challenge", appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();
    }



}
