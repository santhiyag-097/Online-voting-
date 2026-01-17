import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static HashMap<String, Integer> votes = new HashMap<>();

    public static void main(String[] args) {

        votes.put("Alice", 0);
        votes.put("Bob", 0);
        votes.put("Charlie", 0);

        while (true) {
            System.out.println("\n===== ONLINE VOTING SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login & Vote");
            System.out.println("3. View Results");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    loginAndVote();
                    break;
                case 3:
                    displayResults();
                    break;
                case 4:
                    System.out.println("Thank you for using the Voting System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void register() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("User already exists!");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }

    static void loginAndVote() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (User u : users) {
            if (u.getUsername().equals(username) &&
                u.getPassword().equals(password)) {

                if (u.hasVoted()) {
                    System.out.println("You have already voted!");
                    return;
                }

                System.out.println("\n--- Candidates ---");
                int i = 1;
                for (String c : votes.keySet()) {
                    System.out.println(i + ". " + c);
                    i++;
                }

                System.out.print("Enter your vote (number): ");
                int voteChoice = sc.nextInt();
                sc.nextLine();

                if (voteChoice < 1 || voteChoice > votes.size()) {
                    System.out.println("Invalid vote!");
                    return;
                }

                String selectedCandidate =
                        new ArrayList<>(votes.keySet()).get(voteChoice - 1);

                votes.put(selectedCandidate,
                          votes.get(selectedCandidate) + 1);
                u.vote();

                System.out.println("Vote cast successfully!");
                return;
            }
        }

        System.out.println("Invalid login credentials!");
    }

    static void displayResults() {
        System.out.println("\n--- Voting Results ---");
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
