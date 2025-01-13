package com.login.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class FirebaseController {

    @Value("${FIREBASE_API_KEY}")
    private String apiKey;

    @Value("${FIREBASE_AUTH_DOMAIN}")
    private String authDomain;

    @Value("${FIREBASE_PROJECT_ID}")
    private String projectId;

    @Value("${FIREBASE_STORAGE_BUCKET}")
    private String storageBucket;

    @Value("${FIREBASE_MESSAGING_SENDER_ID}")
    private String messagingSenderId;

    @Value("${FIREBASE_APP_ID}")
    private String appId;

    @Value("${FIREBASE_MEASUREMENT_ID}")
    private String measurementId;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("authDomain", authDomain);
        model.addAttribute("projectId", projectId);
        model.addAttribute("storageBucket", storageBucket);
        model.addAttribute("messagingSenderId", messagingSenderId);
        model.addAttribute("appId", appId);
        model.addAttribute("measurementId", measurementId);
        return "index";
    }

    @GetMapping("/private")
    @ResponseBody
    public String ping(@RequestHeader("Authorization") String token) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(token);
            return "Pong";
        } catch (FirebaseAuthException e) {
            return "Unauthorized";
        }
    }
}
