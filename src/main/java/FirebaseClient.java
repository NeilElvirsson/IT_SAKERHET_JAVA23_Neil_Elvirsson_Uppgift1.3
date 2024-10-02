import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirebaseClient {

    private final Firestore db;

    public FirebaseClient(Firestore db) {
        this.db = db;
    }

    // Skapa en användare i Firestore
    public void createUser(String userId, String name, String email, int age) throws Exception {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("age", age);

        DocumentReference docRef = db.collection("users").document(userId);
        WriteResult result = docRef.set(user).get();
        System.out.println("User created at: " + result.getUpdateTime());
    }

    // Hämta en användare från Firestore
    public Map<String, Object> getUser(String userId) throws Exception {
        DocumentReference docRef = db.collection("users").document(userId);
        return docRef.get().get().getData();
    }
}
