import ADTPackage.*;
import GraphPackage.*;

import java.util.ArrayList;
import java.util.HashMap;


public class ProfileManager {
    private UndirectedGraph<Profile> graph;
    private ArrayList<Profile> profilesList;

    public ProfileManager() {
        graph = new UndirectedGraph<>();
        profilesList = new ArrayList<>();
    }
    public void addProfile(Profile profile) {
        graph.addVertex(profile);
        profilesList.add(profile);
    }
    public void connectProfiles(Profile current, Profile friend) {
        graph.addEdge(current, friend);
        current.addFriends(friend);
    }

    public void displayAllProfiles() {
        Profile[] profiles = profilesList.toArray(new Profile[0]);
        for (Profile profile : profiles) {
            System.out.println(profile);
        }
    }

    public void displayFriend(Profile current) {
        QueueInterface<Profile> friend = graph.getBreadthFirstTraversal(current);
        while (!friend.isEmpty()) {
            System.out.println(friend.dequeue().toString());
        }
    }
}
