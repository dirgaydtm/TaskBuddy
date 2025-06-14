# TaskBuddy

TaskBuddy adalah aplikasi manajemen tugas berbasis Java yang memudahkan pengguna dalam mengelola, mengorganisasi, dan memantau tugas-tugas secara terstruktur. Aplikasi ini mendukung sistem multi-user, pencatatan aktivitas, serta fitur undo/redo untuk kenyamanan pengguna.

## Fitur Lengkap & Penjelasan

### 1. Manajemen Tugas Berbasis Tree

- Struktur Pohon (Tree): Setiap tugas dapat memiliki sub-tugas tanpa batasan kedalaman, sehingga pengguna dapat membuat kategori, sub-kategori, dan tugas secara hierarkis.
- Tambah Tugas/Kategori: Pengguna dapat menambahkan tugas atau kategori baru di bawah tugas/kategori manapun.
- Visualisasi Struktur Tugas: Menampilkan seluruh struktur tugas dan sub-tugas dalam bentuk pohon yang mudah dibaca.
- Hapus & Edit Tugas: (opsional, dapat dikembangkan) Menghapus atau mengedit tugas tertentu.

### 2. Pengelolaan Sub-Tugas

- Sorting Sub-Tugas: Sub-tugas di bawah suatu tugas dapat diurutkan berdasarkan prioritas (1=tinggi, 5=terendah) menggunakan algoritma bubble sort.
- Pencarian Tugas: Mencari tugas berdasarkan ID secara rekursif di seluruh tree.

### 3. Manajemen Pengguna

- Multi-User: Mendukung banyak pengguna dengan sistem antrian giliran (queue).
- Ganti Giliran: Pengguna dapat bergantian mengelola tugas sesuai urutan antrian.
- Daftar Pengguna: Menampilkan seluruh pengguna beserta peran (Admin/Member).

### 4. Undo & Redo

- Undo: Membatalkan aksi terakhir pada struktur tugas (misal: penambahan, pengurutan).
- Redo: Mengulangi aksi yang telah di-undo.
- Penyimpanan State: Setiap perubahan pada tree tugas akan disimpan sebagai state untuk mendukung fitur undo/redo.

### 5. Activity Log

- Pencatatan Aktivitas: Setiap aksi penting (tambah tugas, urutkan, undo, redo, ganti user) dicatat lengkap dengan timestamp.
- Riwayat Aktivitas: Pengguna dapat melihat seluruh log aktivitas yang telah terjadi.

### 6. Antarmuka Menu Interaktif

- Menu CLI: Menu berbasis teks yang interaktif dan mudah digunakan.
- Validasi Input: Menangani input yang tidak valid agar aplikasi tetap stabil.

## Cara Menjalankan

1. Compile seluruh file Java.
2. Jalankan `Main.java`.
3. Ikuti instruksi pada menu yang muncul.

## Struktur File

- `Main.java` : Entry point aplikasi.
- `Menu.java` : Antarmuka menu utama.
- `Task.java` : Kelas tugas (mendukung tree).
- `TaskManager.java` : Pengelolaan dan visualisasi tree tugas.
- `UndoRedoManager.java` : Manajemen undo/redo.
- `ActivityLog.java` : Pencatatan aktivitas.
- `User.java` : Kelas pengguna.
- `UserManager.java` : Pengelolaan dan antrian pengguna.
- `Node.java` : Struktur data node untuk log aktivitas.

## Teknologi

- Java (OOP)
- Struktur Data: Tree, Linked List, Queue, Stack
