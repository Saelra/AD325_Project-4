import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProfileManager profileManager = new ProfileManager();
    private static Profile currentUser;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter First Name: " );
        String name = userInput.next();
        System.out.print("Enter Last Name: " );
        name += " " + userInput.next();
        System.out.print("Enter Status: ");
        String status = userInput.next();
        profileManager.addProfile(currentUser = new Profile(name, status));

        boolean login = true;

        while (true) {
            System.out.println();
            currentUser.printProfile();
            System.out.println();
            menu();
            int input = userInput.nextInt();
            if (input == 1) {
                System.out.print("Enter Name: ");
                name = userInput.next();
                System.out.print("Enter Last Name: " );
                name += " " + userInput.next();
                currentUser.setName(name);
                System.out.print("Enter Status: ");
                status = userInput.next();
                currentUser.setStatus(status);
            } else if (input == 2) {
                profileManager.displayAllProfiles();
            } else if (input == 3) {
                System.out.print("Enter Friend's First Name: ");
                name = userInput.next();
                System.out.print("Enter Friend's Last Name: ");
                name += " " + userInput.next();
                Profile friend = profileManager.getProfile(name);
                if (friend == null) {
                    System.out.println("Profile not found");
                } else {
                    profileManager.connectProfiles(currentUser, friend);
                }
            } else if (input == 4) {
                System.out.println("Friend List:");
                profileManager.displayFriend(currentUser, currentUser);
            } else if (input == 5) {
//                System.out.print("Enter Friend First Name: ");
//                name = userInput.next();
//                System.out.print("Enter Friend Last Name: ");
//                name += " " + userInput.next();
//                Profile friend = profileManager.getProfile(name);
                List<Profile> friends = currentUser.getFriendsList();
                System.out.println("Friend's Friend List:");
                for (Profile friend : friends) {
                    System.out.println(friend.getName());
                    profileManager.displayFriendOfFriend(friend, friend);
                }
            } else if (input == 6) {
                profileManager.removeProfile(currentUser.getName());
            } else if (input == 7) {
                System.out.print("Enter First Name: ");
                name = userInput.next();
                System.out.print("Enter Last Name: ");
                name += " " + userInput.next();
                Profile newProfile = new Profile(name);
                profileManager.addProfile(newProfile);
            } else if (input == 8) {
                System.out.print("Enter First Name: ");
                name = userInput.next();
                System.out.print("Enter Last Name: ");
                name += " " + userInput.next();
                Profile change = profileManager.getProfile(name);
                if (change != null) {
                    currentUser = change;
                }
            } else if (input == 9) {
                login = false;
                return;
            } else {
                System.out.print("Invalid input, please enter number 1 - 9:");
            }

        }

    }

    public static void menu() {
        System.out.println("Menu:");
        System.out.println("1. Modify profile");
        System.out.println("2. View all profiles");
        System.out.println("3. Add a friend");
        System.out.println("4. View your friend list");
        System.out.println("5. View your friend's friend list");
        System.out.println("6. Delete a profile");
        System.out.println("7. Add another profile");
        System.out.println("8. Switch user");
        System.out.println("9. Logout ");
        System.out.println("Please enter number 1 - 9: ");
    }

}
