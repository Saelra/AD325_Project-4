import ADTPackage.*;
import GraphPackage.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProfileManager {
    private UndirectedGraph<Profile> graph;
//    private ArrayList<Profile> profilesList;
    private final Map<String, Profile> profilesList;

    public ProfileManager() {
        graph = new UndirectedGraph<>();
        profilesList = new HashMap<>();
    }
    public void addProfile(Profile profile) {
        graph.addVertex(profile);
        profilesList.put(profile.getName(), profile);
    }
    public void connectProfiles(Profile current, Profile friend) {
        graph.addEdge(current, friend);
        current.addFriends(friend);
    }

    public Profile getProfile(String profile) {
        return profilesList.getOrDefault(profile, null);
    }
    public void removeProfile(String profile) {
        profilesList.remove(profile);
        graph.clear();
    }

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
        QueueInterface<Profile> friend = graph.getDepthFirstTraversal(friends);
        while (!friend.isEmpty()) {
            String profile = friend.dequeue().toString();
            if (!profile.equals(current.toString())) {
                System.out.println(profile);
            }
        }
    }

}
