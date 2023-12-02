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
        if (profilesList.containsKey(profile)) {
            return profilesList.get(profile);
        } else {
            return null;
        }
    }

    public void displayAllProfiles() {
        for (Map.Entry<String, Profile> profile : profilesList.entrySet()) {
            System.out.println(profile.getValue().toString());
        }

    }
    public void displayFriend(Profile current, Profile friends) {
        QueueInterface<Profile> friend = graph.getBreadthFirstTraversal(friends);
        while (!friend.isEmpty()) {
            String f = friend.dequeue().toString();
            if (!f.equals(current.toString())) {
                System.out.println(f);
            }
        }
    }
}
