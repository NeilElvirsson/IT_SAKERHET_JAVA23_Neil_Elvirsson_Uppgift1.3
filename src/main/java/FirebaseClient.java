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
    public void createUser(String userId, String name, String password, String email, int age) throws Exception {

        String hashedPassword = PasswordHasher.hashPassword(password);

        Map<String, Object> user = new HashMap<>();
        //user.put("userID", userId);
        user.put("name", name);
        user.put("password", hashedPassword);
        System.out.println("Hashed password: " + hashedPassword);
        user.put("email", email);
        user.put("age", age);

        DocumentReference docRef = db.collection("users").document(userId);
        WriteResult result = docRef.set(user).get();
        System.out.println("User created at: " + result.getUpdateTime());
    }

    // Hämta en användare från Firestore
    public Map<String, Object> getUser(String userId) throws Exception {
        DocumentReference docRef = db.collection("users").document(userId);
        Map<String, Object> data = docRef.get().get().getData();
        if(data == null) {
            System.out.println("Ingen användare hittades");
        } else if (data != null) {
            data.remove("password");

        }
        return data;
    }

    public void deleteUser (String userId) throws Exception {
        DocumentReference docRef = db.collection("users").document(userId);
        docRef.delete().get();
        System.out.println("User with ID: " + userId + " deleted!");
    }
}
