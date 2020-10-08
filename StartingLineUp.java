import java.util.LinkedList;

public class StartingLineUp {

    public static void main(String[] args) {

        LinkedList<String> starters = new LinkedList<>();

        starters.add("Ben Roethlisberger");
        starters.add("JuJu Smith-Schuster");
        starters.add("James Conner");
        starters.add("Diontae Johnson");
        starters.add("Chase Claypool");
        starters.add("Eric Ebron");

        System.out.println(starters);

        starters.remove("Diontae Johnson");
        starters.remove("Chase Claypool");
        starters.remove("Eric Ebron");

        System.out.println("\nThe starting lineup is: " + starters);
    }
}