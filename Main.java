import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProfileManager profileManager = new ProfileManager();
    private static Profile newProfile;
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
        profileManager.addProfile(newProfile = new Profile(name, status));
        currentUser = newProfile;

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

            //menu-action switch
            switch (option) {
                case 1: //modify profile
                    System.out.println("----------------Modify Profile----------------\n");
                    System.out.print("Enter Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    currentUser.setName(name);
                    System.out.print("Enter Status: ");
                    status = userInput.next();
                    currentUser.setStatus(status);
                    System.out.println("\n----------Modify profile successful-----------");
                    break;

                case 2: //view all
                    System.out.println("---------All profiles in the network----------\n");
                    profileManager.displayAllProfiles();
                    break;

                case 3: //add friend
                    System.out.println("-----------------Add a friend-----------------\n");
                    System.out.print("Enter Friend's First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Friend's Last Name: ");
                    name += " " + userInput.next();
                    Profile friend = profileManager.getProfile(name);
                    if (friend == null) {
                        System.out.println("\n-------------Profile not found---------------");
                    } else {
                        profileManager.connectProfiles(currentUser, friend);
                        System.out.println("\n-------Friend successfully added---------");
                    }
                    break;

                case 4: //friend list
                    System.out.println("----------------Friend List------------------\n");
                    profileManager.displayFriend(currentUser, currentUser);
                    break;

                case 5:
                    System.out.println("------------Friend's Friend List--------------\n");
                    List<Profile> friends = currentUser.getFriendsList();
                    for (Profile current : friends) {
                        System.out.println(current.getName());
                        profileManager.displayFriendOfFriend(currentUser, current);
                    }
                    break;

                case 6:
                    System.out.println("--------------Delete A Profile----------------\n");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    // check if the profile is in the database
                    Profile deleting = profileManager.getProfile(name);
                    if (deleting != null) {
                        currentUser.removeFriends(deleting);
                        profileManager.removeProfile(name);
                        System.out.println("\n-------Profile successfully deleted---------");
                    } else {
                        System.out.println("\n----------Profile does not exist------------");
                    }
                    break;

                case 7: //add profile
                    System.out.println("-------------Add Another Profile--------------\n");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    System.out.print("Enter Status: ");
                    status = userInput.next();
                    Profile newProfile = new Profile(name, status);
                    profileManager.addProfile(newProfile);
                    System.out.println("\n---------Profile successfully added-----------");
                    break;

                case 8: //TODO can't switch back to original user profile
                    System.out.println("-----------------Switch User------------------\n");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    Profile change = profileManager.getProfile(name);
                    if (change != null) {
                        currentUser = change;
                    }else{
                        System.out.println("\n-------------Profile not found---------------");
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
