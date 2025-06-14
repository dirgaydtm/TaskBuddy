import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final TaskManager taskManager;
    private final UserManager userManager;
    private final ActivityLog activityLog;
    private final UndoRedoManager undoRedoManager;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.taskManager = new TaskManager();
        this.userManager = new UserManager();
        this.activityLog = new ActivityLog();
        this.undoRedoManager = new UndoRedoManager();
        initializeData();
    }
    
    private void initializeData() {
        userManager.addUser(new User("Andi", "Admin"));
        userManager.addUser(new User("Budi", "Member"));
        undoRedoManager.saveState(taskManager.getRoot());
        activityLog.addLog("Aplikasi dimulai dan data awal dimuat.");
    }

    public void display() {
        while (true) {
            User currentUser = userManager.getCurrentUser();
            System.out.println("\n==========================================");
            System.out.println("Pengguna Aktif: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
            System.out.println("         TaskBuddy - Menu Utama");
            System.out.println("==========================================");
            System.out.println("1. Tampilkan Struktur Tugas");
            System.out.println("2. Tambah Tugas/Kategori Baru");
            System.out.println("3. Urutkan Sub-Tugas (berdasarkan Prioritas)");
            System.out.println("4. Cari Tugas (berdasarkan ID)");
            System.out.println("5. Undo Aksi Terakhir");
            System.out.println("6. Redo Aksi");
            System.out.println("7. Lihat Log Aktivitas");
            System.out.println("8. Ganti Giliran Pengguna");
            System.out.println("9. Tampilkan Semua Pengguna");
            System.out.println("0. Keluar");
            System.out.println("------------------------------------------");
            System.out.print("Pilih Opsi: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                handleMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
            }
        }
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                taskManager.visualizeTaskTree();
                break;
            case 2:
                addTask();
                break;
            case 3:
                sortTasks();
                break;
            case 4:
                searchTask();
                break;
            case 5:
                undoAction();
                break;
            case 6:
                redoAction();
                break;
            case 7:
                activityLog.printLogs();
                break;
            case 8:
                userManager.nextTurn();
                break;
            case 9:
                userManager.listAllUsers();
                break;
            case 0:
                System.out.println(" Terima kasih telah menggunakan TaskBuddy!");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private void addTask() {
        System.out.print("Masukkan ID dari Parent Task (masukkan " + taskManager.getRoot().getId() + " untuk root): ");
        int parentId = Integer.parseInt(scanner.nextLine());
        Task parentTask = taskManager.findTaskById(parentId);

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
        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = null;
        try {
            deadline = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal salah, deadline diatur ke null.");
        }

        Task newTask = new Task(name, desc, priority, deadline);
        parentTask.addSubTask(newTask);

        String logMessage = userManager.getCurrentUser().getUsername() + " menambahkan tugas '" + name + "' di bawah '" + parentTask.getName() + "'.";
        activityLog.addLog(logMessage);
        undoRedoManager.saveState(taskManager.getRoot());
        
        System.out.println("Tugas '" + name + "' berhasil ditambahkan.");
    }
    
    private void sortTasks() {
        System.out.print("Masukkan ID dari task yang sub-tugasnya ingin diurutkan: ");
        int parentId = Integer.parseInt(scanner.nextLine());
        Task parentTask = taskManager.findTaskById(parentId);

        if (parentTask == null) {
            System.out.println("Task dengan ID " + parentId + " tidak ditemukan.");
            return;
        }

        taskManager.sortSubTasksByPriority(parentTask);
        
        String logMessage = userManager.getCurrentUser().getUsername() + " mengurutkan sub-tugas di bawah '" + parentTask.getName() + "'.";
        activityLog.addLog(logMessage);
        undoRedoManager.saveState(taskManager.getRoot());
        
        taskManager.visualizeTaskTree();
    }
    
    private void searchTask() {
        System.out.print("Masukkan ID tugas yang dicari: ");
        int id = Integer.parseInt(scanner.nextLine());
        Task foundTask = taskManager.findTaskById(id);

        if (foundTask != null && foundTask != taskManager.getRoot()) {
            System.out.println("\n--- Hasil Pencarian ---");
            System.out.println(foundTask);
            System.out.println("Deskripsi: " + foundTask.getDescription());
            System.out.println("------------------------\n");
        } else {
            System.out.println("Tugas dengan ID " + id + " tidak ditemukan.");
        }
    }

    private void undoAction() {
        Task previousState = undoRedoManager.undo();
        if (previousState != null) {
            taskManager.getRoot().getSubTasks().clear();
            taskManager.getRoot().getSubTasks().addAll(previousState.getSubTasks());
            activityLog.addLog(userManager.getCurrentUser().getUsername() + " melakukan UNDO.");
            taskManager.visualizeTaskTree();
        }
    }

    private void redoAction() {
        Task nextState = undoRedoManager.redo();
        if (nextState != null) {
            taskManager.getRoot().getSubTasks().clear();
            taskManager.getRoot().getSubTasks().addAll(nextState.getSubTasks());
            activityLog.addLog(userManager.getCurrentUser().getUsername() + " melakukan REDO.");
            taskManager.visualizeTaskTree();
        }
    }
}