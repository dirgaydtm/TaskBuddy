import java.util.LinkedList;

public class UndoRedoManager {
    private LinkedList<Task> history;
    private int currentStateIndex;

    public UndoRedoManager() {
        this.history = new LinkedList<>();
        this.currentStateIndex = -1;
    }

    public void saveState(Task rootTask) {
        while (history.size() > currentStateIndex + 1) {
            history.removeLast();
        }
        history.add(cloneTaskTree(rootTask));
        currentStateIndex++;
    }

    public Task undo() {
        if (canUndo()) {
            currentStateIndex--;
            System.out.println("Aksi dibatalkan (Undo).");
            return cloneTaskTree(history.get(currentStateIndex));
        }
        System.out.println("Tidak ada aksi untuk di-undo.");
        return null;
    }

    public Task redo() {
        if (canRedo()) {
            currentStateIndex++;
            System.out.println("Aksi diulangi (Redo).");
            return cloneTaskTree(history.get(currentStateIndex));
        }
        System.out.println("Tidak ada aksi untuk di-redo.");
        return null;
    }

    public boolean canUndo() {
        return currentStateIndex > 0;
    }

    public boolean canRedo() {
        return currentStateIndex < history.size() - 1;
    }

    private Task cloneTaskTree(Task original) {
        if (original == null) return null;
        Task clone = new Task(
            original.getId(),
            original.getName(),
            original.getDescription(),
            original.getPriority(),
            original.getDeadline()
        );
        for (Task subTask : original.getSubTasks()) {
            clone.addSubTask(cloneTaskTree(subTask));
        }
        return clone;
    }
}