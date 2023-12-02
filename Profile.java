
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private String status;
    private List<Profile> friendsList;

    public Profile(String name) {
        this.name = name;
        this.friendsList = new ArrayList<>();
    }

    public Profile(String name, String status) {
        this.name = name;
        this.status = status;
        this.friendsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Profile>  getFriendsList() {
        return friendsList;
    }

    public void addFriends(Profile friend) {
        friendsList.add(friend);
    }

    public void printProfile() {
        System.out.println("Current Profile");
        System.out.println("Name: " + name);
        System.out.println("    Status: " + status);

        System.out.println("My Friends:");

        if (friendsList.isEmpty()) {
            System.out.println("    No friends yet.");
        } else {
            for (Profile friend : friendsList) {
                System.out.println("    Name: " + friend.getName() + " Status: " + friend.getStatus());
            }
        }
    }
    public String toString() {
        String status = "None";
        if (getStatus() != null) {
            status = getStatus();
        }
        return "    " + getName() + "    Status: " + status;
    }
}
