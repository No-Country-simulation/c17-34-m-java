package com.c174.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitializer {
    @PostConstruct
    private void FirestoreInitializer() throws IOException {
        InputStream serviceAccount =
               getClass().getClassLoader().getResourceAsStream("firebase-adminsdk.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://c-17-31-m-java.firebaseio.com/")
                .build();
        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }
    }
    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }

}
