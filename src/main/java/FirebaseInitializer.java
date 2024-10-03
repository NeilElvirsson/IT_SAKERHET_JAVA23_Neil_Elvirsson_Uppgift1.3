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

        String pathToCredentials = "src/main/resources/apikey.json";

        FileInputStream serviceAccount = new FileInputStream(pathToCredentials);

        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("it-security-e1539")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            db = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(pathToCredentials)))
                    .setProjectId("it-security-e1539")
                    .build()
                    .getService();

            System.out.println("Firestore initialized successfully!");
        } finally {
            serviceAccount.close();
        }
    }

    public Firestore getFirestore() {
        return db;
    }
}
