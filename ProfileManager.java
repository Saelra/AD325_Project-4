import ADTPackage.StackInterface;
import GraphPackage.*;

import java.util.Stack;

public class ProfileManager {
    private UndirectedGraph<Profile> graph;

    public ProfileManager() {
        graph = new UndirectedGraph<>();
    }
    public void addProfile(Profile profile) {
        graph.addVertex(profile);
    }
    public void connectProfiles(Profile profile1, Profile profile2) {
        graph.addEdge(profile1, profile2);
    }
//    public void displayAllProfiles(Stack<Profile> profiles) {
//        System.out.println("All Profiles:");
//        while (!profiles.isEmpty()) {
//            System.out.println(profiles.pop());
//        }
//    }
    public void displayAllProfiles() {
        StackInterface<Profile> profiles = graph.getTopologicalOrder();
        System.out.println("All Profiles:");
        while (!profiles.isEmpty()) {
            System.out.println(profiles.pop());
        }
    }

        public void displayFriend(Profile current) {
        graph.getBreadthFirstTraversal(current);
    }
}
