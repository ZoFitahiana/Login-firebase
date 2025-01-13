package com.login.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.apiKey}")
    private String apiKey;

    @Value("${firebase.authDomain}")
    private String authDomain;

    @Value("${firebase.projectId}")
    private String projectId;

    @Value("${firebase.storageBucket}")
    private String storageBucket;

    @Value("${firebase.messagingSenderId}")
    private String messagingSenderId;

    @Value("${firebase.appId}")
    private String appId;

    @Value("${firebase.measurementId}")
    private String measurementId;

    @PostConstruct
    public void initializeFirebase() throws IOException {
        String json = String.format(
                "{\"apiKey\":\"%s\",\"authDomain\":\"%s\",\"projectId\":\"%s\",\"storageBucket\":\"%s\",\"messagingSenderId\":\"%s\",\"appId\":\"%s\",\"measurementId\":\"%s\"}",
                apiKey, authDomain, projectId, storageBucket, messagingSenderId, appId, measurementId
        );

        InputStream serviceAccount = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.create(null)) // Utilisez null pour les informations d'identification par défaut
                .setDatabaseUrl("https://" + projectId + ".firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
