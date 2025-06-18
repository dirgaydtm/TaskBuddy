import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

public class TaskManager {
    private Task root;
    private int nextId;

    public TaskManager() {
        this.root = new Task(1, "Semua Tugas", "Root dari semua tugas dan kategori", 0, null);
        this.nextId = 2;
    }

    public Task getRoot() {
        return root;
    }

    public Task createTask(String name, String description, int priority, LocalDate deadline) {
        Task newTask = new Task(nextId++, name, description, priority, deadline);
        return newTask;
    }

    public void visualizeTaskTree() {
        System.out.println("\n--- Struktur Tugas ---");
        visualize(root, "", true);
        System.out.println("-".repeat(27) + "\n");
    }

    private void visualize(Task task, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + task.getName() + " (ID: " + task.getId() + ")");
        List<Task> subTasks = task.getSubTasks();
        for (int i = 0; i < subTasks.size() - 1; i++) {
            visualize(subTasks.get(i), prefix + (isTail ? "    " : "│   "), false);
        }
        if (subTasks.size() > 0) {
            visualize(subTasks.get(subTasks.size() - 1), prefix + (isTail ? "    " : "│   "), true);
        }
    }

    public Task findTaskById(int id) {
        return findTaskByIdRecursive(root, id);
    }

    private Task findTaskByIdRecursive(Task current, int id) {
        if (current.getId() == id) {
            return current;
        }
        for (Task subTask : current.getSubTasks()) {
            Task found = findTaskByIdRecursive(subTask, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public void sortSubTasksByPriority(Task parentTask) {
        if (parentTask == null) return;
        List<Task> subTasks = parentTask.getSubTasks();
        int n = subTasks.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (subTasks.get(j).getPriority() > subTasks.get(j + 1).getPriority()) {
                    Collections.swap(subTasks, j, j + 1);
                }
            }
        }
        System.out.println("✅ Sub-tugas di bawah '" + parentTask.getName() + "' telah diurutkan berdasarkan prioritas.");
    }

    public void sortEntireTreeByPriority() {
        sortSubTasksRecursively(root);
        System.out.println("Seluruh tree tugas telah diurutkan berdasarkan prioritas dan tanggal due date.");
    }

    private void sortSubTasksRecursively(Task parentTask) {
        if (parentTask == null) return;

        // Urutkan sub-tugas di level ini
        List<Task> subTasks = parentTask.getSubTasks();
        int n = subTasks.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Task task1 = subTasks.get(j);
                Task task2 = subTasks.get(j + 1);

                // Urutkan berdasarkan prioritas terlebih dahulu
                if (task1.getPriority() > task2.getPriority()) {
                    Collections.swap(subTasks, j, j + 1);
                } 
                // Jika prioritas sama, urutkan berdasarkan tanggal due date
                else if (task1.getPriority() == task2.getPriority()) {
                    if (task1.getDeadline() != null && task2.getDeadline() != null) {
                        if (task1.getDeadline().isAfter(task2.getDeadline())) {
                            Collections.swap(subTasks, j, j + 1);
                        }
                    } 
                    // Jika salah satu deadline null, tempatkan yang memiliki deadline lebih dekat di atas
                    else if (task1.getDeadline() == null && task2.getDeadline() != null) {
                        Collections.swap(subTasks, j, j + 1);
                    }
                }
            }
        }

        // Rekursif untuk setiap sub-tugas
        for (Task subTask : subTasks) {
            sortSubTasksRecursively(subTask);
        }
    }
}