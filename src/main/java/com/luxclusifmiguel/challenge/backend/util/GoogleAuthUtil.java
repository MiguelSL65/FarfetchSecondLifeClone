package com.luxclusifmiguel.challenge.backend.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.util.Arrays;
import java.util.List;

public class GoogleAuthUtil {

    public static Credential authorize() {

        // build Google Client Secrets from JSON file
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        //build Credentials object
        return null;
    }
}
