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
        while (option != 10) {

            //prints profile with each return to menu
            System.out.println();
            currentUser.printProfile();
            System.out.println();

            //prints menu after action has been completed
            System.out.println("Menu:");
            System.out.println("1. Modify profile");
            System.out.println("2. View all profiles");
            System.out.println("3. Add a friend");
            System.out.println("4. Unfriend a profile");
            System.out.println("5. View your friend list");
            System.out.println("6. View your friend's friend list");
            System.out.println("7. Delete a profile");
            System.out.println("8. Add another profile");
            System.out.println("9. Switch user");
            System.out.println("10. Logout\n");

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

                case 3: //TODO add friend adds to the active user only; switching to a friend's profile shows no friends
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

                case 4: //TODO unfriend currently just disconnects entire friends list
                    System.out.println("-------------Unfriend A Profile---------------\n");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    currentUser.removeFriend(profileManager.getProfile(name));
                    System.out.println("\n------Profile successfully unfriended---------");
                    break;

                case 5: //friend list
                    System.out.println("----------------Friend List------------------\n");
                    profileManager.displayFriend(currentUser, currentUser);
                    break;

                case 6: //TODO view friends of specified friend
                    System.out.println("------------Friend's Friend List--------------\n");
                    List<Profile> friends = currentUser.getFriendsList();
                    /*for (Profile friend : friends) {
                        System.out.println(friend.getName());
                        profileManager.displayFriendOfFriend(friend, friend);
                    }*/
                    break;

                case 7: //TODO deleting a profile does not delete from friend list
                    System.out.println("--------------Delete A Profile----------------\n");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    profileManager.removeProfile(name);
                    System.out.println("\n-------Profile successfully deleted---------");
                    break;

                case 8: //TODO add profile does not ask for status
                    System.out.println("-------------Add Another Profile--------------\n");
                    System.out.print("Enter First Name: ");
                    name = userInput.next();
                    System.out.print("Enter Last Name: ");
                    name += " " + userInput.next();
                    Profile newProfile = new Profile(name);
                    profileManager.addProfile(newProfile);
                    System.out.println("\n---------Profile successfully added-----------");
                    break;

                case 9: //TODO can't switch back to original user profile; doesn't report not found
                    System.out.println("-----------------Switch User------------------\n");
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
                    if (option == 10) continue;
                    System.out.println("Please enter number 1 - 9: ");
                    break;
            }
        }
        userInput.close();
        System.out.println("You have successfully logged out");
    }
}
