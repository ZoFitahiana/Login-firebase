package com.login.firebase.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration {

    @PostConstruct
    public void initializeFirebase() throws IOException {
        String firebaseConfigJson = System.getenv("FIREBASE_CONFIG_JSON");

        if (firebaseConfigJson == null || firebaseConfigJson.isBlank()) {
            throw new IllegalStateException("The environment variable ‘FIREBASE_CONFIG_JSON’ is missing or empty");
        }

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(firebaseConfigJson.getBytes())))
                    .build();
            FirebaseApp.initializeApp(options);
        }
    }
}
