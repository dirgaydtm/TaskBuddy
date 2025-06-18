# TaskBuddy - Kelompok GacorKang

TaskBuddy adalah aplikasi manajemen tugas berbasis Java yang dirancang untuk membantu pengguna mengelola, mengorganisasi, dan memantau tugas-tugas secara terstruktur. Aplikasi ini mendukung sistem multi-user, pencatatan aktivitas, serta fitur undo/redo untuk kenyamanan pengguna. Berikut adalah deskripsi fitur dan pembagian tugas dalam pengembangan aplikasi ini.

# Anggota :
- Dirga Yuditama (245150400111034)
- Frisnel Aditia Virgo Ginting (Menghilang, tidak kerja)
- Muhammad Nabiel Yandra (245150400111036)
- Prasetya Hafidz Ramadhan (245150407111053)
- Raditya Bintang D. R. (245150400111037)

---

## Fitur dan Pembagian Tugas

### 1. Struktur Tugas (Tree) - Muhammad Nabiel Yandra (245150400111036)
TaskBuddy menggunakan struktur pohon (tree) untuk mengelola tugas dan sub-tugas. Setiap tugas dapat memiliki sub-tugas tanpa batasan kedalaman, sehingga pengguna dapat membuat kategori, sub-kategori, dan tugas secara hierarkis. Fitur ini memungkinkan pengguna untuk memvisualisasikan tugas dalam bentuk pohon yang mudah dibaca.

#### Fitur:
- Tambah tugas atau kategori baru di bawah tugas/kategori tertentu.
- Visualisasi struktur tugas dalam bentuk pohon.
- Pengelolaan sub-tugas secara hierarkis.

---

### 2. Log Perubahan (Double Linked List) - Dirga Yuditama (245150400111034)
TaskBuddy mencatat setiap aktivitas penting yang dilakukan pengguna, seperti penambahan tugas, pengurutan, undo, redo, dan pergantian giliran pengguna. Log ini disimpan menggunakan struktur data double linked list, sehingga memungkinkan pencatatan yang efisien dan fleksibel.

#### Fitur:
- Pencatatan aktivitas dengan timestamp.
- Riwayat aktivitas dapat dilihat oleh pengguna dengan role "Admin".
- Penyimpanan log menggunakan double linked list untuk kemudahan traversal.

---

### 3. Antrian Pengguna (Queue) - Bersama
TaskBuddy mendukung sistem multi-user dengan role "Admin" dan "Member". Setiap pengguna memiliki giliran untuk mengelola tugas, yang diatur menggunakan struktur data queue. Sistem ini memastikan bahwa setiap pengguna mendapatkan giliran secara bergantian.

#### Fitur:
- Sistem antrian pengguna untuk pergantian giliran.
- Role pengguna: "Admin" memiliki akses penuh, sedangkan "Member" memiliki akses terbatas.
- Menampilkan daftar pengguna beserta perannya.

---

### 4. Sorting dan Searching - Raditya Bintang D. R. (245150400111037)
TaskBuddy memungkinkan pengguna untuk mengelola sub-tugas dengan fitur sorting dan searching. Sorting dilakukan berdasarkan prioritas dan deadline, sedangkan searching memungkinkan pencarian tugas berdasarkan nama menggunakan linear search.

#### Fitur:
- **Sorting**:
  - Sorting sub-tugas berdasarkan prioritas (1=tinggi, 5=terendah).
  - Jika prioritas sama, sub-tugas dengan deadline lebih dekat akan berada di atas.
- **Searching**:
  - Pencarian tugas berdasarkan nama menggunakan linear search.
  - Pencarian dilakukan secara rekursif pada seluruh tree tugas.

---

### 5. Manajemen Pengguna - Prasetya Hafidz Ramadhan (245150407111053)
TaskBuddy menyediakan fitur untuk mengelola pengguna, termasuk penambahan pengguna default, pergantian giliran, dan pembatasan akses berdasarkan role. Fitur ini memastikan bahwa setiap pengguna memiliki peran yang jelas dalam aplikasi.

#### Fitur:
- Penambahan pengguna default ("Admin" dan "Member").
- Pergantian giliran pengguna secara otomatis.
- Pembatasan akses untuk pengguna dengan role "Member".

---

### 6. CLI dan Logika Utama - Bersama
TaskBuddy menggunakan antarmuka berbasis teks (CLI) yang interaktif dan mudah digunakan. Logika utama aplikasi, seperti pengelolaan menu, validasi input, dan integrasi fitur lainnya, dikembangkan bersama oleh tim.

#### Fitur:
- Menu CLI untuk memilih opsi dengan angka.
- Validasi input untuk menangani input yang tidak valid.
- Integrasi semua fitur ke dalam logika utama aplikasi.

---

## Cara Menjalankan

1. **Compile seluruh file Java**:
   ```bash
   javac *.java
   ```
2. **Jalankan `Main.java`**:
   ```bash
   java Main
   ```
3. **Ikuti instruksi pada menu yang muncul**.

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
