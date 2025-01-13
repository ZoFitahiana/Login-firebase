package com.login.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirebaseController {

    @GetMapping("/login")
    public String login(@RequestHeader("Authorization") String token) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            return "User ID: " + decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            return "Invalid token";
        }
    }

    @GetMapping("/hello")
    public String hello(@RequestHeader("Authorization") String token) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(token);
            return "Hello World";
        } catch (FirebaseAuthException e) {
            return "Unauthorized";
        }
    }
}
