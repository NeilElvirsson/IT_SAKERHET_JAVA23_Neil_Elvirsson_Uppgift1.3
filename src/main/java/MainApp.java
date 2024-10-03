import com.google.cloud.firestore.Firestore;

import java.util.Map;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        try {
            // Initierar Firestore
            FirebaseInitializer initializer = new FirebaseInitializer();
            initializer.initialize();
            Firestore db = initializer.getFirestore();
            FirebaseClient firebaseClient = new FirebaseClient(db);

            Scanner scan = new Scanner(System.in);
            boolean running = true;

            while(running) {

                System.out.println("Välj ett alternativ");
                System.out.println("1. Skapa användare");
                System.out.println("2. Visa användare");
                System.out.println("3. Radera användare");
                System.out.println("4. Avsluta");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Ange användar-ID: ");
                        String userID = scan.nextLine();

                        System.out.println("Ange name: ");
                        String name = scan.nextLine();

                        System.out.println("Ange lösenord: ");
                        String password = scan.nextLine();

                        System.out.println("Ange email: ");
                        String email = scan.nextLine();

                        System.out.println("Ange ålder");
                        int age = scan.nextInt();
                        scan.nextLine();

                        firebaseClient.createUser(userID, name, password, email, age);
                        break;

                    case 2 :
                        System.out.println("Ange användar-ID för att visa användare: ");
                        userID = scan.nextLine();
                        Map<String, Object> userData = firebaseClient.getUser(userID);
                        if(userData != null) {
                            System.out.println("AnvändarData: " + userData);
                        } else {
                            System.out.println("Användaren finns inte. ");
                        }
                        break;

                    case 3:
                        System.out.println("Ange anvädar-ID för att radera användare: ");
                        userID = scan.nextLine();
                        firebaseClient.deleteUser(userID);
                        break;

                    case 4:
                        running = false;
                        System.out.println("Programmet avslutas!");
                        break;

                    default:
                        System.out.println("Ogiltligt val");
                        break;


                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Operationen lyckades ej!");
        }
    }
}
