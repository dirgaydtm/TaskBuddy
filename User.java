public class User {
    private static int nextId = 1;
    private int id;
    private String role; // Contoh: "Admin" atau "Member"

    public User(String role) {
        this.id = nextId++;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User #" + id + " (" + role + ")";
    }
}