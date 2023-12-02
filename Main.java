import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProfileManager profileManager = new ProfileManager();
    private static Profile currentUser;

    public static void main(String[] args) {
        //add the active user profile
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter First Name: " );
        String name = userInput.next();
        System.out.print("Enter Last Name: " );
        name += " " + userInput.next();
        System.out.print("Enter Status: ");
        String status = userInput.next();
        profileManager.addProfile(currentUser = new Profile(name, status));


        //create a switch for menu actions
        int option = -1;
        while (option != 9) {

            //prints profile with each return to menu
            System.out.println();
            currentUser.printProfile();
            System.out.println();

            //prints menu after action has been completed
            System.out.println("Menu:");
            System.out.println("1. Modify profile");
            System.out.println("2. View all profiles");
            System.out.println("3. Add a friend");
            System.out.println("4. View your friend list");
            System.out.println("5. View your friend's friend list");
            System.out.println("6. Delete a profile");
            System.out.println("7. Add another profile");
            System.out.println("8. Switch user");
            System.out.println("9. Logout\n");

            System.out.println("Enter menu option: ");
            option = userInput.nextInt();

            //menu action switch
            switch (option) {
                case 1:
                    System.out.println("---------------Modify Profile----------------");
                    System.out.print("Enter Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    currentUser.setName(name);
                    System.out.print("Enter Status: ");
                    status = userInput.next();
                    currentUser.setStatus(status);
                    System.out.println("Modify profile successful");
                    break;
                case 2:
                    System.out.println("--------All profiles in the network----------");
                    profileManager.displayAllProfiles();
                    break;
                case 3:
                    System.out.println("----------------Add a friend-----------------");
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
                    break;
                case 4:
                    System.out.println("----------------Friend List------------------");
                    System.out.println("Friend List:");
                    profileManager.displayFriend(currentUser, currentUser);
                    break;
                case 5:
                    System.out.println("-------------Friends of Friends---------------");
                    List<Profile> friends = currentUser.getFriendsList();
                    System.out.println("Friend's Friend List:");
                    /*for (Profile friend : friends) {
                        System.out.println(friend.getName());
                        profileManager.displayFriendOfFriend(friend, friend);
                    }*/
                    break;
                case 6:
                    System.out.println("-------------Unfriend A Profile---------------");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    currentUser.removeFriend(profileManager.getProfile(name));
                    break;
                case 7:
                    System.out.println("------------Add Another Profile--------------");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    //TODO ask to set status
                    Profile newProfile = new Profile(name);
                    profileManager.addProfile(newProfile);
                    break;
                case 8:
                    System.out.println("----------------Switch User------------------");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    Profile change = profileManager.getProfile(name);
                    if (change != null) {
                        currentUser = change;
                    }
                    break;
                default:
                    if (option == 9) continue;
                    System.out.println("Please enter number 1 - 9: ");
                    break;
            }
        }
        userInput.close();
        System.out.println("You have successfully logged out");
    }
}
