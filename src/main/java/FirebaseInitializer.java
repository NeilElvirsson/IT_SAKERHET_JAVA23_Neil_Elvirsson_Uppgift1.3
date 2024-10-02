import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private Firestore db;

    public void initialize() throws IOException {
        // Skapa en ström och behåll referensen
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/apikey.json");

        try {
            // Skapa FirebaseOptions
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("it-security-e1539")
                    .build();

            // Initiera FirebaseApp om den inte redan har initierats
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Initiera Firestore
            db = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("src/main/resources/apikey.json")))
                    .setProjectId("it-security-e1539")
                    .build()
                    .getService();

            System.out.println("Firestore initialized successfully!");
        } finally {
            // Stäng strömmen efter användning
            serviceAccount.close();
        }
    }

    public Firestore getFirestore() {
        return db;
    }
}
