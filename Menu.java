import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final TaskManager taskManager;
    private final UserManager userManager;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.taskManager = new TaskManager();
        this.userManager = new UserManager();
        initializeData();
    }

    private void initializeData() {
        taskManager.initializeRoot();
        taskManager.logActivity("Aplikasi dimulai dengan pengguna default: Admin dan Member.");
    }

    public void display() {
        while (true) {
            User currentUser = userManager.getCurrentUser();
            System.out.println("\n==========================================");
            System.out.println("Pengguna Aktif: " + currentUser.getRole());
            System.out.println("         TaskBuddy - Menu Utama");
            System.out.println("==========================================");
            System.out.println("1. Tampilkan Struktur Tugas");
            System.out.println("2. Tambah Tugas/Kategori Baru");
            System.out.println("3. Urutkan Seluruh Tree Tugas");
            System.out.println("4. Cari Tugas (berdasarkan Nama)");
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
        User currentUser = userManager.getCurrentUser();
        switch (choice) {
            case 1:
                taskManager.visualizeTaskTree();
                break;
            case 2:
                taskManager.addTask(scanner, currentUser);
                break;
            case 3:
                taskManager.sortEntireTreeByPriority();
                break;
            case 4:
                taskManager.searchTaskByName(scanner); // Update untuk pencarian berdasarkan nama
                break;
            case 5:
                taskManager.undoAction();
                break;
            case 6:
                taskManager.redoAction();
                break;
            case 7:
                if ("Member".equals(currentUser.getRole())) {
                    System.out.println("Akses ditolak! Pengguna dengan role 'Member' tidak dapat melihat log aktivitas.");
                } else {
                    taskManager.printActivityLog();
                }
                break;
            case 8:
                userManager.nextTurn();
                break;
            case 9:
                userManager.listAllUsers();
                break;
            case 0:
                System.out.println("Terima kasih telah menggunakan TaskBuddy!");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
}