package com.fcm_ms.token_api.config;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {

  @Bean
  public FirebaseApp firebaseApp() throws IOException {
    ClassPathResource resource = new ClassPathResource("firebase-service-account.json");

    FirebaseOptions options = FirebaseOptions.builder()
      .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
      .build();

    if (FirebaseApp.getApps().isEmpty())
      return FirebaseApp.initializeApp(options);
    else
      return FirebaseApp.getInstance();
  }
}
