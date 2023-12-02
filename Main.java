import java.util.Scanner;

public class Main {
    private static ProfileManager profileManager = new ProfileManager();
    private static Profile currentUser;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = userInput.nextLine();
        profileManager.addProfile(currentUser = new Profile(name));
        boolean login = true;

        while (true) {
            menu();
            int input = userInput.nextInt();
            if (input == 1) {
                System.out.println("Enter Name: ");
                currentUser.setName(userInput.nextLine());
                System.out.println("Enter Status: ");
                currentUser.setStatus(userInput.nextLine());
                break;
            } else if (input == 2) {
                profileManager.displayAllProfiles();
            } else if (input == 9) {
                login = false;
                break;
            }


        }

    }

    public static void menu() {
        System.out.println("\nMenu:");
        System.out.println("1. Modify profile");
        System.out.println("2. View all profiles");
        System.out.println("3. Add a friend");
        System.out.println("4. View your friend list");
        System.out.println("5. View your friend's friend list");
        System.out.println("6. Delete a profile");
        System.out.println("7. Add another profile");
        System.out.println("8. Switch user");
        System.out.println("9. Logout ");

    }


}
