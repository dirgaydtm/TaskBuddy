import java.util.LinkedList;
import java.util.Queue;

public class UserManager {
    private Queue<User> userQueue;

    public UserManager() {
        this.userQueue = new LinkedList<>();
        userQueue.add(new User("Admin"));
        userQueue.add(new User("Member"));
    }

    public User getCurrentUser() {
        return userQueue.peek(); // Lihat siapa yang ada di depan antrian
    }

    public void nextTurn() {
        if (!userQueue.isEmpty()) {
            User currentUser = userQueue.poll(); // Ambil dari depan
            userQueue.add(currentUser);
            System.out.println("\nGiliran sekarang: " + getCurrentUser().getRole());
        }
    }

    public void listAllUsers() {
        System.out.println("\n--- Daftar Semua Pengguna ---");
        for (User user : userQueue) {
            System.out.println("- " + user.getRole());
        }
        System.out.println("-".repeat(28));
    }
}