import com.google.cloud.firestore.Firestore;

import java.util.Map;

public class MainApp {

    public static void main(String[] args) {

        try {
            // Initiera Firebase och Firestore
            FirebaseInitializer initializer = new FirebaseInitializer();
            initializer.initialize();
            Firestore db = initializer.getFirestore();

            // Skapa en instans av FirebaseClient
            FirebaseClient firebaseClient = new FirebaseClient(db);

            // Skapa en ny användare
            firebaseClient.createUser("user123", "John Doe", "johndoe@example.com", 30);

            // Hämta och visa användardata
            Map<String, Object> userData = firebaseClient.getUser("user123");
            System.out.println("User data: " + userData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
