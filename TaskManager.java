import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManager {
    private Task root;
    private int nextId;
    private final ActivityLog activityLog;
    private final UndoRedoManager undoRedoManager;

    public TaskManager() {
        this.activityLog = new ActivityLog();
        this.undoRedoManager = new UndoRedoManager();
        this.nextId = 1;
    }

    public void initializeRoot() {
        this.root = new Task(nextId++, "Semua Tugas", "Root dari semua tugas dan kategori", 0, null);
        undoRedoManager.saveState(root);
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

    public void addTask(Scanner scanner, User currentUser) {
        System.out.print("Masukkan ID dari Parent Task (masukkan " + root.getId() + " untuk root): ");
        int parentId = Integer.parseInt(scanner.nextLine());
        Task parentTask = findTaskById(parentId);

        if (parentTask == null) {
            System.out.println("Parent task dengan ID " + parentId + " tidak ditemukan.");
            return;
        }

        System.out.print("Nama Tugas: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String desc = scanner.nextLine();
        System.out.print("Prioritas (1-5, 1=tinggi): ");
        int priority = Integer.parseInt(scanner.nextLine());

        LocalDate deadline = null;
        while (deadline == null) {
            System.out.print("Deadline (YYYY-MM-DD): ");
            String deadlineInput = scanner.nextLine();
            try {
                LocalDate parsedDate = LocalDate.parse(deadlineInput);
                if (parsedDate.isBefore(LocalDate.now())) {
                    System.out.println("Tanggal tidak valid. Deadline tidak boleh kurang dari tanggal hari ini (" + LocalDate.now() + ").");
                } else {
                    deadline = parsedDate;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah. Harap masukkan tanggal sesuai format kalender (YYYY-MM-DD).");
            }
        }

        Task newTask = new Task(nextId++, name, desc, priority, deadline);
        parentTask.addSubTask(newTask);

        logActivity(currentUser.getRole() + " menambahkan tugas '" + name + "' di bawah '" + parentTask.getName() + "'.");
        undoRedoManager.saveState(root);
        System.out.println("Tugas '" + name + "' berhasil ditambahkan.");
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

    public void searchTask(Scanner scanner) {
        System.out.print("Masukkan ID tugas yang dicari: ");
        int id = Integer.parseInt(scanner.nextLine());
        Task foundTask = findTaskById(id);

        if (foundTask != null && foundTask != root) {
            System.out.println("\n--- Hasil Pencarian ---");
            System.out.println(foundTask);
            System.out.println("Deskripsi: " + foundTask.getDescription());
            System.out.println("------------------------\n");
        } else {
            System.out.println("Tugas dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void sortEntireTreeByPriority() {
        sortSubTasksRecursively(root);
        logActivity("Seluruh tree tugas telah diurutkan berdasarkan prioritas dan tanggal due date.");
        visualizeTaskTree();
    }

    private void sortSubTasksRecursively(Task parentTask) {
        if (parentTask == null) return;

        List<Task> subTasks = parentTask.getSubTasks();
        int n = subTasks.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Task task1 = subTasks.get(j);
                Task task2 = subTasks.get(j + 1);

                if (task1.getPriority() > task2.getPriority()) {
                    Collections.swap(subTasks, j, j + 1);
                } else if (task1.getPriority() == task2.getPriority()) {
                    if (task1.getDeadline() != null && task2.getDeadline() != null) {
                        if (task1.getDeadline().isAfter(task2.getDeadline())) {
                            Collections.swap(subTasks, j, j + 1);
                        }
                    } else if (task1.getDeadline() == null && task2.getDeadline() != null) {
                        Collections.swap(subTasks, j, j + 1);
                    }
                }
            }
        }

        for (Task subTask : subTasks) {
            sortSubTasksRecursively(subTask);
        }
    }

    public void undoAction() {
        Task previousState = undoRedoManager.undo();
        if (previousState != null) {
            root.getSubTasks().clear();
            root.getSubTasks().addAll(previousState.getSubTasks());
            logActivity("UNDO dilakukan.");
            visualizeTaskTree();
        }
    }

    public void redoAction() {
        Task nextState = undoRedoManager.redo();
        if (nextState != null) {
            root.getSubTasks().clear();
            root.getSubTasks().addAll(nextState.getSubTasks());
            logActivity("REDO dilakukan.");
            visualizeTaskTree();
        }
    }

    public void logActivity(String message) {
        activityLog.addLog(message);
    }

    public void printActivityLog() {
        activityLog.printLogs();
    }
}