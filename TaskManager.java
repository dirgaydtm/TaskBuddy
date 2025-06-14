import java.util.Collections;
import java.util.List;

public class TaskManager {
    private Task root; // Root dari semua tugas (kategori utama)

    public TaskManager() {
        this.root = new Task("Semua Tugas", "Root dari semua tugas dan kategori", 0, null);
    }

    public Task getRoot() {
        return root;
    } 

    // Visualisasi Tree
    public void visualizeTaskTree() {
        System.out.println("\n--- Struktur Tugas ---");
        visualize(root, "", true);
        System.out.println("-".repeat(27)+"\n");
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

    // Pencarian (Linear Search)
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

    // Sorting (Bubble Sort)
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
}