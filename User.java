public class User {
    private static int nextId = 1;
    private int id;
    private String username;
    private String role; // Contoh: "Admin" atau "Member"

    public User(String username, String role) {
        this.id = nextId++;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User #" + id + " - " + username + " (" + role + ")";
    }
}