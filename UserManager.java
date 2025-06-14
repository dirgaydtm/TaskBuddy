import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UserManager {
    private List<User> allUsers;
    private Queue<User> userQueue;

    public UserManager() {
        this.allUsers = new ArrayList<>();
        this.userQueue = new LinkedList<>(); // LinkedList mengimplementasikan interface Queue
    }

    public void addUser(User user) {
        allUsers.add(user);
        userQueue.add(user); // Masukkan ke antrian
        System.out.println("User '" + user.getUsername() + "' berhasil ditambahkan.");
    }

    public User findUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return userQueue.peek(); // Lihat siapa yang ada di depan antrian
    }

    public void nextTurn() {
        if (!userQueue.isEmpty()) {
            User currentUser = userQueue.poll(); // Ambil dari depan
            userQueue.add(currentUser); 
            System.out.println("\n🔄 Giliran sekarang: " + getCurrentUser().getUsername());
        }
    }
    
    public void listAllUsers() {
        System.out.println("\n--- 👥 Daftar Semua Pengguna ---");
        for (User user : allUsers) {
            System.out.println("- " + user.getUsername() + " (" + user.getRole() + ")");
        }
        System.out.println("-".repeat(28));
    }
}