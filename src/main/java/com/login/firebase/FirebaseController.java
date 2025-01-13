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
