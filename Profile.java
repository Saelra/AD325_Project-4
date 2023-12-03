import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private String status;
    private List<Profile> friendsList;

    public Profile(String name) {
        this.name = name;
        this.friendsList = new ArrayList<>();
    } //end default constructor

    public Profile(String name, String status) {
        this.name = name;
        this.status = status;
        this.friendsList = new ArrayList<>();
    } //end constructor

    /**
     * Retrieves the name from the user's profile.
     * @return name associated with this profile.
     */
    public String getName() {
        return name;
    }

    /**
     * Creates or alters the name on the user's profile.
     * @param name name associated with this profile.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the status from the user's profile.
     * @return status - e.g. online, busy, or offline.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Creates or alters the status of the user.
     * @param status e.g. online, busy, or offline.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves this user's list of friends stored in an ArrayList.
     * @return friendsList - a list of this user's friends.
     */
    public List<Profile>  getFriendsList() {
        return friendsList;
    }

    /**
     * Adds a friend to this user's list of friends, stored in an ArrayList.
     * @param friend a new profile associated with this user.
     */
    public void addFriends(Profile friend) {
        friendsList.add(friend);
    }

    /**
     * Removes a friend from this user's list of friends, stored in an ArrayList.
     * @param friend profile to be deleted from current user's list.
     */
    public void removeFriends(Profile friend) {
        friendsList.remove(friend);
    }

    /**
     * Outputs the contents of the profile to console as such:
     * Current Profile
     * Name: username
     *     Status: user status
     * My Friends:
     *     Name: friend username Status: friend user status
     *     -alternately-
     *     No friends yet
     */
    public void printProfile() { //O(n)

        System.out.println("---------------Current Profile----------------");
        System.out.println("Name: " + name);
        System.out.println("    Status: " + status);

        System.out.println("\nMy Friends:");

        if (friendsList.isEmpty()) {
            System.out.println("    No friends yet.");
        } else {
            for (Profile friend : friendsList) {
                System.out.println("    Name: " + friend.getName() + " Status: " + friend.getStatus());
            }
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * A string representation of a profile instance from this user's
     * friends list.
     * @return A line of text in the following format:
     * "friend username    Status: user status"
     */
    @Override
    public String toString() {
        String status = "None";
        if (getStatus() != null) {
            status = getStatus();
        }
        return "    " + getName() + "    Status: " + status;
    }
}
