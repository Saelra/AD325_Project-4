
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


    public List<Profile> getFriendsList() {
        return friendsList;
    }

    public void addFriends(Profile friend) {
        friendsList.add(friend);
    }

    public void printProfile() {
        System.out.println("Name: " + name);
        System.out.println("Status: " + status);

        System.out.println("Friends:");

        if (friendsList.isEmpty()) {
            System.out.println("No friends yet.");
        } else {
            for (Profile friend : friendsList) {
                System.out.println("Name: " + friend.getName());
                System.out.println("Status: " + getStatus());
                System.out.println();
            }
        }
    }
}
