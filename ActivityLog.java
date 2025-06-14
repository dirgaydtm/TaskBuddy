import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityLog {
    private Node<String> head;
    private Node<String> tail;

    public ActivityLog() {
        this.head = null;
        this.tail = null;
    }

    public void addLog(String activity) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = "[" + timestamp + "] " + activity;
        Node<String> newNode = new Node<>(logEntry);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void printLogs() {
        System.out.println("\n--- 📜 Log Aktivitas ---");
        if (head == null) {
            System.out.println("Tidak ada aktivitas yang tercatat.");
            return;
        }
        Node<String> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("------------------------\n");
    }
}