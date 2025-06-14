import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private static int nextId = 1;
    private int id;
    private String name;
    private String description;
    private int priority; // 1 (Paling tinggi) - 5 (Paling rendah)
    private LocalDate deadline;
    private List<Task> subTasks;

    public Task(String name, String description, int priority, LocalDate deadline) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.subTasks = new ArrayList<>();
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public LocalDate getDeadline() { return deadline; }
    public List<Task> getSubTasks() { return subTasks; }

    // Metode untuk mengelola Sub-Task
    public void addSubTask(Task subTask) {
        this.subTasks.add(subTask);
    }

    public void removeSubTask(Task subTask) {
        this.subTasks.remove(subTask);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + name + " (Prioritas: " + priority + ", Deadline: " + deadline + ")";
    }
}