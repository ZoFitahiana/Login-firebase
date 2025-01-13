package com.login.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Value("${firebase.auth.ui.url}")
    private String firebaseAuthUiUrl;

    @CrossOrigin(origins = "${firebase.auth.origin}")
    @GetMapping("/")
    public ResponseEntity<Void> redirectToAuthUi() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, firebaseAuthUiUrl)
                .build();
    }

    @CrossOrigin(origins = "${firebase.auth.origin}")
    @GetMapping("/private")
    public ResponseEntity<String> authenticateUser(@RequestParam("token") String token) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            return ResponseEntity.ok("🎉 Welcome back! Your UID is: " + uid);
        } catch (FirebaseAuthException e) {
            System.err.println("error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("😞 Oops! Invalid or expired token. Please try again.");
        }
    }
}
