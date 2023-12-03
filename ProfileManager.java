import ADTPackage.LinkedQueue;
import ADTPackage.QueueInterface;
import GraphPackage.UndirectedGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfileManager {
    private UndirectedGraph<Profile> graph;
//    private ArrayList<Profile> profilesList;
    private final Map<String, Profile> profilesList;

    public ProfileManager() {
        graph = new UndirectedGraph<>();
        profilesList = new HashMap<>();
    } //end default constructor

    /**
     * Adds a profile to the network (undirected graph), represented by a
     * new, unconnected vertex.
     * @param profile a vertex.
     */
    public void addProfile(Profile profile) {
        graph.addVertex(profile);
        profilesList.put(profile.getName(), profile);
    }

    /**
     * Connects a friend to the current profile, represented by adding
     * an edge between vertices.
     * @param current the active vertex.
     * @param friend the vertex to be connected.
     */
    public void connectProfiles(Profile current, Profile friend) {
        graph.addEdge(current, friend);
        graph.addEdge(friend, current);
        current.addFriends(friend);
        friend.addFriends(current);
    }

    /**
     * Retrieves a profile from the list of profiles represented by a hash map
     * where the key is the username, and the value is the profile associated
     * with it.
     * @param profile the search key.
     * @return the value (Profile) associated with the username; null if
     * no value.
     */
    public Profile getProfile(String profile) {
        return profilesList.getOrDefault(profile, null);
    }

    /**
     * Deletes a profile from the list of profiles represented by a hash map
     * where the key is the username, and the value is the profile associated
     * with it.
     * @param profile the search key.
     */
    public void removeProfile(String profile) {
        Profile profileToRemove = profilesList.remove(profile);

        // Remove the profile from all friend lists
        for (Profile current : profilesList.values()) {
            current.removeFriends(profileToRemove);
        }
    }

    /**
     *Outputs the list of all profiles in the network to the console.
     */
    public void displayAllProfiles() {
        for (Map.Entry<String, Profile> profile : profilesList.entrySet()) {
            System.out.println(profile.getValue().toString());
        }
    }

    public void displayFriend(Profile current, Profile friends) {
        QueueInterface<Profile> friend = graph.getBreadthFirstTraversal(friends);
        while (!friend.isEmpty()) {
            String profile = friend.dequeue().toString();
            if (!profile.equals(current.toString())) {
                System.out.println(profile);
            }
        }
    }
    public void displayFriendOfFriend(Profile current, Profile friends) {
        QueueInterface<Profile> friend = new LinkedQueue<>();
        List<Profile> friendsList = friends.getFriendsList();
        for (Profile currentFriend : friendsList) {
            friend.enqueue(currentFriend);
        }
        while (!friend.isEmpty()) {
            String profile = friend.dequeue().toString();
            if (!profile.equals(current.toString())) {
                if (!profile.equals(friends.toString())) {
                    System.out.println(profile);
                }
            }
        }
    }
}
