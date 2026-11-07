# EDUCATE

<img width="193" height="106" alt="logo" src="https://github.com/user-attachments/assets/f9858ae7-71a5-42ea-985b-286079a9d05c"/>

---

## Deskripsi Singkat

Aplikasi **“Educate”** merupakan platform teknologi pendidikan berbasis **Java** dan **MySQL** yang dirancang untuk mengelola data pengguna, modul pembelajaran, latihan soal berjenjang, kelas online interaktif, serta evaluasi riwayat belajar secara real-time. Platform ini juga dilengkapi dengan fitur analisis kelemahan siswa dan pemberian rekomendasi strategi belajar otomatis.

Proyek ini dirancang untuk mendukung pencapaian **Tujuan Pembangunan Berkelanjutan (SDGs)**, khususnya **SDG 4 (Quality Education / Pendidikan Berkualitas)** dan **SDG 10 (Reduced Inequalities / Berkurangnya Kesenjangan)**. Melalui sistem ini, proses pengelolaan materi, pelatihan soal berjenjang, kelas daring, hingga pemantauan perkembangan belajar siswa dapat dilakukan secara terstruktur, inklusif, dan dapat diakses secara merata untuk mengurangi kesenjangan akses pendidikan berkualitas.

Aplikasi ini menyediakan fitur utama berupa **manajemen pengguna, modul pembelajaran, paket latihan soal, pemeriksaan otomatis, kelas online berbayar, serta analisis riwayat belajar**, yang dapat diakses oleh peran *Pengajar* maupun *Pelajar*. Seluruh data disimpan dan dikelola secara terpusat melalui basis data, sehingga proses evaluasi dan pelacakan akademik dapat berjalan secara efisien.


---

## Fitur Utama Program

1. **Autentikasi & Manajemen Pengguna:** Registrasi akun pelajar baru, login multi-peran (*Pengajar* dan *Pelajar*), dan pengelolaan profil (perbarui data dan hapus akun).
2. **Manajemen Modul Pembelajaran:** Pengajar dapat mengunggah dan mengelola modul belajar (format teks/video) yang dikategorikan berdasarkan topik bahasan.
3. **Manajemen Paket & Latihan Soal:** Penyusunan bank soal pilihan ganda yang terikat pada modul materi tertentu dan dikelompokkan berdasarkan tingkat kesulitan (*Easy*, *Medium*, *Hard*).
4. **Pengerjaan Soal & Pemeriksaan Otomatis:** Sesi latihan interaktif untuk pelajar dengan penilaian otomatis oleh sistem, rekapitulasi jumlah jawaban benar/salah, serta perhitungan skor akhir.
5. **Kelas Online & Pendaftaran Berbayar:** Pengajar dapat membuka jadwal bimbingan daring interaktif; pelajar dapat mendaftar kelas dengan sistem status pembayaran (*Lunas* / *Pending*) dan batasan kuota peserta.
6. **Analisis Riwayat & Rekomendasi Belajar:** Pelacakan perkembangan belajar siswa per topik materi dan pemberian saran/rekomendasi belajar otomatis berdasarkan rentang nilai yang diperoleh.

---
## Tech Stack

| Kategori | Komponen |
|----------|-----------|
| **Bahasa Pemrograman** | Java |
| **Build Automation Tool** | Apache Maven |
| **Framework GUI** | Java Swing |
| **Database** | MySQL |
| **Library / Driver** | MySQL Connector, JDBC API |
| **Arsitektur Sistem** | MVC + DAO |
| **Tools Pengembangan** | Apache NetBeans IDE, Laragon, Draw.io, Git & GitHub |

---

## Perancangan Sistem

<details>
<summary>USE CASE DIAGRAM</summary>

Sistem memiliki dua aktor utama:
- **Pengajar:** Mengelola modul materi, latihan soal, rekomendasi belajar, jadwal kelas online, serta memantau seluruh hasil belajar siswa.
- **Pelajar:** Melakukan registrasi, mempelajari materi, mengerjakan latihan soal, mendaftar kelas online berbayar, dan melihat hasil evaluasi beserta rekomendasi belajarnya.
dengan 13 pemodelan use case yang menghubungkan aktor Pengaja dan Pelajar dengan prasyarat utama melakukan Login.

<img width="2094" height="1983" alt="usecase3-Halaman-1 drawio" src="https://github.com/user-attachments/assets/bb217c27-f8b4-4df4-a191-aabb1b8cc5cc" />

</details>

<details>
<summary>ERD DIAGRAM</summary>

Sistem ini menggunakan 12 entitas dan menerapkan konsep Supertype/Subtype pada tabel akun pengguna:
1. `pengguna` (Supertype)
2. `pengajar` (Subtype)
3. `pelajar` (Subtype)
4. `materi`
5. `paket_soal`
6. `latihan_soal`
7. `jawaban`
8. `hasil_belajar`
9. `detail_latihan`
10. `kelas_online`
11. `pendaftaran_kelas_online`
12. `rekomendasi`

**A. Logical ERD**

<img width="1441" height="655" alt="image" src="https://github.com/user-attachments/assets/fcf072ed-bf6e-402c-9505-22a17da6dbeb" />

**B. Relational ERD**

<img width="1506" height="658" alt="image" src="https://github.com/user-attachments/assets/1a1ffdcd-190c-4870-bb71-65ddb5135e10" />

Pada sistem basis data perusahaan Educate memiliki rancangan ERD yang terdiri dari 12 entitas utama, di mana sistem ini juga menerapkan konsep supertype dan subtype agar penyimpanan data akun pengguna lebih efisien. Konsep ini diterapkan pada entitas `pengguna` yang berperan sebagai supertype, sedangkan entitas `pengajar` dan `pelajar` berperan sebagai subtype. Pada pengelolaan media pembelajaran dan latihan soal menggunakan entitas materi yang terhubung ke entitas `pengajar`, sedangkan untuk pengelolaan latihan soal menggunakan sistem yang memisahkan paket latihan soal dan butir soal menggunakan entitas `paket_soal` dan entitas `latihan_soal`. Untuk mencatat histori atau tracking belajar yang dilakukan oleh pelajar menggunakan entitas `hasil_belajar` yang berfungsi mencatat hasil belajar oleh `pelajar` secara rinci. Berikut merupakan hasil rancangan ERD pada sistem basis data ini.

</details>

<details>
<summary>FLOWCHART DIAGRAM</summary>
 
Alur kerja program terbagi menjadi banyak segmen:
1. **Menu Utama**

<img width="682" height="1412" alt="Capstone Flowchart NEW-(FIKS) Menu Utama drawio" src="https://github.com/user-attachments/assets/064a1ae9-c9b5-4f9d-b89f-f6c2e1c427df" />

Menu utama berfungsi sebagai pusat navigasi yang menyediakan pilihan akses Admin, Pengajar, Pelajar, dan Keluar. Sistem akan mengarahkan pengguna sesuai pilihan atau menampilkan peringatan jika input tidak valid.

2. **Halaman Registrasi:**

<img width="2067" height="622" alt="Capstone Flowchart NEW-(FIKS) Register drawio" src="https://github.com/user-attachments/assets/ebe9dc27-a105-4837-b972-1ce5591aeeaf" />

Pelajar mengisi formulir pendaftaran berupa identitas dan informasi akun. Sistem memvalidasi kelengkapan data serta keunikan username sebelum menyimpan akun ke database dan mengarahkan pengguna ke halaman login.

3. **Halaman login:**

<img width="2896" height="510" alt="Capstone Flowchart NEW-(FIKS) Login drawio" src="https://github.com/user-attachments/assets/663c4d6e-51d6-4a1f-89ed-efa869705b77" />

Pengguna memasukkan username atau email beserta password untuk diverifikasi oleh sistem berdasarkan database. Setelah berhasil, pengguna diarahkan ke halaman sesuai perannya, yaitu Pengajar atau Pelajar. 

4. **Halaman Pengajar:**

<img width="776" height="2216" alt="Capstone Flowchart NEW-(FIKS) Halaman Pengajar drawio" src="https://github.com/user-attachments/assets/0885f6d3-569c-42a6-a797-311f820094d3" />

Halaman Pengajar menyediakan berbagai fitur pengelolaan pembelajaran, seperti membuat kelas, mengunggah materi, serta menyusun latihan soal dan kuis. Pengajar juga dapat memantau hasil belajar dan perkembangan Pelajar. 

5. **Halaman Pengajar - Kelola Materi:**

<img width="2962" height="1587" alt="Capstone Flowchart NEW-(FIKS) Pengajar - Kelola Materi drawio" src="https://github.com/user-attachments/assets/54ce24f6-ec5d-42eb-b897-636b658afee7" />

Pengajar dapat menambah, mengubah, dan menghapus materi pembelajaran yang tersedia. Setiap perubahan akan divalidasi oleh sistem sebelum disimpan ke database.

6. **Halaman Pengajar - Kelola Latihan Soal:**

<img width="3032" height="1578" alt="Capstone Flowchart NEW-(FIKS) Pengajar - Kelola Soal drawio" src="https://github.com/user-attachments/assets/748a0dd8-a11a-47e2-87b2-c289f802b3d3" />

Pengajar dapat menambah, mengubah, dan menghapus latihan soal dengan menentukan topik, tingkat kesulitan, pertanyaan, pilihan jawaban, serta kunci jawaban. Sistem akan memvalidasi setiap perubahan dan memperbarui database setelah data dinyatakan valid.

7. **Halaman Pengajar - Kelola Kelas Online:**

<img width="2992" height="1568" alt="Capstone Flowchart NEW-(FIKS) Pengajar - Kelola Kelas Online drawio" src="https://github.com/user-attachments/assets/e2a323d2-5dc0-4769-ac69-1972f795de53" />


Pengajar dapat melihat seluruh kelas online serta melakukan penambahan, perubahan, dan penghapusan data kelas. Sistem akan memvalidasi data sebelum menyimpan perubahan ke database.

8. **Halaman Pengajar – Lihat Hasil Belajar**

<img width="532" height="816" alt="Capstone Flowchart NEW-(FIKS) Pengajar - Lihat Hasil Belajar Keseluruhan  drawio" src="https://github.com/user-attachments/assets/1c18dd0c-db5c-4195-93ac-e5852857dc7a" />


Pengajar dapat melihat seluruh data hasil belajar Pelajar yang tersimpan di dalam sistem. Setelah selesai, Pengajar dapat kembali ke halaman utama Pengajar.

9. **Halaman Pengajar – Kelola Rekomendasi**

<img width="2963" height="1523" alt="Capstone Flowchart NEW-(FIKS) Pengajar - Kelola Rekomendasi  drawio" src="https://github.com/user-attachments/assets/afe4a74f-7536-48ec-b880-fe6d401432ab" />


Pengajar dapat menambah, mengubah, dan menghapus rekomendasi belajar berdasarkan rentang nilai minimum dan maksimum. Sistem akan memvalidasi setiap perubahan sebelum memperbarui database.

10. **Halaman Pengajar – Kelola Data Pelajar**

<img width="2732" height="1314" alt="Capstone Flowchart NEW-(FIKS) Pengajar - Data Pelajar drawio" src="https://github.com/user-attachments/assets/13c32ac5-a55e-4f99-92f5-8bdcd220a22c" />


Pengajar dapat melihat seluruh data Pelajar serta melakukan perubahan atau penghapusan akun. Setiap perubahan divalidasi terlebih dahulu, sedangkan penghapusan memerlukan konfirmasi pengguna.

11. **Halaman Pengajar – Edit Profil**

<img width="3692" height="812" alt="Capstone Flowchart NEW-Pengajar - Profil Pengajar drawio" src="https://github.com/user-attachments/assets/ecc5e13b-8b66-4a2c-9f67-d9cfd9e9a2de" />


Pengajar dapat melihat dan mengubah informasi profil akun sesuai kebutuhan. Sistem akan memvalidasi dan menyimpan perubahan data ke database.

12. **Halaman Pelajar**

<img width="772" height="1942" alt="Capstone Flowchart NEW-(FIKS) Halaman Pelajar drawio" src="https://github.com/user-attachments/assets/20d8f639-18fa-41d3-bc25-12681d75472a" />

Halaman Pelajar menyediakan akses ke berbagai fitur pembelajaran, mulai dari melihat materi, mengerjakan latihan soal, hingga mengikuti kelas online. Sistem juga mencatat progres dan nilai untuk menampilkan hasil serta rekomendasi belajar.

13. **Halaman Pelajar – Lihat Materi**

<img width="1628" height="588" alt="Capstone Flowchart NEW-(FIKS) Halaman Pelajar - Lihat Materi drawio" src="https://github.com/user-attachments/assets/3f7bdbd7-d856-4941-8656-94a2529c724a" />

Pelajar dapat melihat daftar materi yang tersedia, kemudian memilih dan mempelajari materi sesuai kebutuhan. Setelah selesai, pengguna dapat kembali ke daftar materi.

14. **Halaman Pelajar – Lihat Latihan Soal**

<img width="3715" height="618" alt="Capstone Flowchart NEW-(FIKS) Halaman Pelajar - Lihat Latihan Soal drawio" src="https://github.com/user-attachments/assets/52a786f7-6888-48e6-ae81-17d7c7240ed3" />

Pelajar dapat memilih paket latihan berdasarkan topik dan tingkat kesulitan, kemudian mengerjakan soal untuk mendapatkan nilai secara otomatis. Hasil pengerjaan disimpan ke database dan digunakan untuk mengidentifikasi topik terlemah serta menghasilkan rekomendasi belajar.

15. **Halaman Pelajar – Lihat Kelas Online**

<img width="2034" height="618" alt="Capstone Flowchart NEW-(FIKS) Halaman Pelajar - Lihat Kelas Online drawio" src="https://github.com/user-attachments/assets/dfaf4732-e89b-4f77-af01-8a47448e602d" />

Pelajar dapat melihat dan memilih kelas online yang tersedia, kemudian melakukan pembayaran sesuai harga yang ditentukan. Setelah pembayaran berhasil diverifikasi, sistem akan mendaftarkan Pelajar ke kelas yang dipilih.

16. **Halaman Pelajar – Lihat Hasil Belajar dan Rekomendasi Belajar**

<img width="502" height="608" alt="Capstone Flowchart NEW-(FIKS) Halaman Pelajar - Lihat Hasil Belajar drawio" src="https://github.com/user-attachments/assets/3b92407c-219f-44ff-a9c7-b49874d47a36" />

Pelajar dapat melihat rekapitulasi hasil belajar beserta rekomendasi pembelajaran berdasarkan performa latihan soal. Setelah selesai melihat informasi, Pelajar dapat kembali ke menu utama.

17. **Halaman Pelajar – Edit Profi**

<img width="2034" height="618" alt="Capstone Flowchart NEW-(FIKS) Halaman Pelajar - Lihat Kelas Online drawio (1)" src="https://github.com/user-attachments/assets/c8913d09-905b-40dd-99dd-8a4ebfd2c4de" />

Pelajar dapat mengubah informasi profil berupa nama, email, dan password, atau menghapus akun dengan konfirmasi password. Sistem akan memvalidasi perubahan sebelum menyimpan atau menghapus data dari database.

</details>

---

## Struktur Folder/Package

```
Educate/
├── src/
│   └── main/
│       ├── java/
│       │   ├── main/
│       │   │   └── Educatee1.java             # Main Class (Entry Point Aplikasi)
│       │   ├── model/                         # Package model (Entitas Objek)
│       │   │   ├── Pengguna.java              # Abstract Parent Class
│       │   │   ├── Pelajar.java               # Subclass Pengguna (Pelajar)
│       │   │   ├── Pengajar.java              # Subclass Pengguna (Pengajar)
│       │   │   ├── KelasOnline.java
│       │   │   ├── Materi.java
│       │   │   ├── LatihanSoal.java
│       │   │   ├── DetailLatihan.java
│       │   │   ├── PaketSoal.java
│       │   │   ├── Jawaban.java
│       │   │   ├── HasilBelajar.java
│       │   │   ├── PendaftaranKelasOnline.java
│       │   │   └── Rekomendasi.java
│       │   ├── dao/                           # Package Data Access Object (DAO)
│       │   │   ├── CrudDao.java
│       │   │   ├── CrudDemoDao.java
│       │   │   ├── PenggunaDao.java
│       │   │   ├── KelasOnlineDao.java
│       │   │   ├── MateriDao.java
│       │   │   ├── LatihanSoalDao.java
│       │   │   ├── PaketSoalDao.java
│       │   │   ├── JawabanDao.java
│       │   │   ├── HasilBelajarDao.java
│       │   │   ├── PendaftaranKelasDao.java
│       │   │   ├── RekomendasiDao.java
│       │   │   ├── StatistikDao.java
│       │   │   ├── RekapHasilDao.java
│       │   │   ├── PenghapusanBerantaiDao.java
│       │   │   └── TampilanDataDao.java
│       │   ├── controller/                    # Package controller
│       │   │   ├── SessionManagerController.java
│       │   │   ├── AuthController.java
│       │   │   ├── MateriController.java
│       │   │   ├── LatihanSoalController.java
│       │   │   ├── HasilBelajarController.java
│       │   │   ├── PendaftaranKelasController.java
│       │   │   └── TampilanDataController.java
│       │   ├── View/                          # Package view (Form, Dialog, Panel)
│       │   │   ├── MenuUtama.java
│       │   │   ├── MenuUtamaPanel.java
│       │   │   ├── LoginPanel.java
│       │   │   ├── SidebarPelajarPanel.java
│       │   │   ├── SidebarPengajarPanel.java
│       │   │   ├── HalamanPengajarPanel.java
│       │   │   ├── DashboardPengajarPanel.java
│       │   │   ├── KelolaMateriPanel.java
│       │   │   ├── KelolaLatihanSoalPanel.java
│       │   │   ├── KelolaKelasOnlinePanel.java
│       │   │   ├── KelolaDataPelajarPanel.java
│       │   │   ├── KelolaRekomendasiPanel.java
│       │   │   ├── HasilBelajarKeseluruhanPanel.java
│       │   │   ├── FormMateriDialog.java
│       │   │   ├── FormSoalDialog.java
│       │   │   ├── FormKelasDialog.java
│       │   │   ├── FormPelajarDialog.java
│       │   │   ├── FormRekomendasiDialog.java
│       │   │   ├── EditProfilPengajarPanel.java
│       │   │   ├── UbahProfilPengajarDialog.java
│       │   │   └── HapusAkunDialog.java
│       │   └── config/                        # Package konfigurasi database
│       │       └── EducateDB.java
│       └── resources/
│           └── Images/                        # Aset Gambar / Logo
│               └── LogoText.png
├── pom.xml                                    # Konfigurasi Maven
└── README.md                                  # Dokumentasi Project
```

---

## Penjelasan Penerapan 4 Pilar OOP di dalam program

Proyek **Educate** dikembangkan menggunakan bahasa pemrograman **Java** dengan menerapkan prinsip-prinsip **Object-Oriented Programming (OOP)** secara menyeluruh pada kelas-kelas model dan strukturnya. Berikut adalah rincian penerapannya:

### 1. Encapsulation (Pengkapsulan)
- **Penerapan:** Mengamankan variabel internal/atribut pada setiap kelas model (seperti `Pengguna`, `Pelajar`, `KelasOnline`) dengan menggunakan access modifier `private`.
- **Akses Data:** Pembacaan dan modifikasi data dilakukan melalui method *getter* dan *setter* (`getId()`, `setNama()`, `setEmail()`, dll.) guna menjaga integritas data dan mencegah perubahan langsung dari luar kelas.

### 2. Inheritance (Pewarisan)
- **Penerapan:** Membangun hierarki kelas dengan memanfaatkan *superclass* dan *subclass*.
- **Contoh:** 
  * Kelas `Pengguna` bertindak sebagai *superclass* yang menyimpan properti umum (`id`, `nama`, `email`).
  * Kelas `Pelajar` dan `Pengajar` merupakan *subclass* yang mewarisi atribut dari `Pengguna` dan menambahkan atribut spesifik (seperti NISN/Progres pada `Pelajar` atau NIP/Mata Pelajaran pada `Pengajar`).

### 3. Polymorphism (Polimorfisme)
- **Penerapan:** Memungkinkan objek dari kelas turunan untuk merespons pemanggilan method sesuai dengan perilakunya masing-masing.
- **Overriding:** Implementasi ulang method dari kelas induk pada kelas turunan (seperti method `tampilInfo()` atau `aksesSistem()`), di mana `Pelajar` dan `Pengajar` memiliki tampilan informasi atau hak akses yang berbeda.
- **Overloading:** Penyediaan beberapa konstruktor atau method dengan nama yang sama tetapi memiliki parameter yang berbeda pada kelas-kelas model (misalnya pendaftaran dengan data minimal vs data lengkap).

### 4. Abstraction (Abstraksi)
- **Penerapan:** Menyembunyikan detail kompleksitas proses bisnis dan hanya menyajikan antarmuka (*interface*) atau *abstract class* yang relevan.
- **Contoh:** Pengelolaan pengerjaan soal (`DetailLatihan` dan `HasilBelajar`) menyembunyikan proses kalkulasi nilai di latar belakang dan hanya menyajikan hasil akhir (skor) kepada pengguna.

---

## Cara Penggunaan Program (sertakan screenshot GUI)

### 1. Prasyarat Sistem
- **Java Development Kit (JDK)**
- **IDE:** Apache NetBeans, IntelliJ IDEA, Eclipse, atau VS Code.
- **Database Server:** MySQL / Laragon / XAMPP.

### 2. Langkah Menjalankan Aplikasi
1. **Clone Repository:**
   ```bash
   https://github.com/sabrinazhsa/Project-Educate.git
   ```
2. **Buka Proyek:** Buka direktori proyek di IDE pilihan Anda.
3. **Konfigurasi Database:** Sesuaikan konfigurasi koneksi database (URL JDBC, Username, Password) pada package `config`.
4. **Jalankan File Utama:** Jalankan berkas utama aplikasi yang terletak pada package `Main`:
   ```bash
   main/java/Main/Educate.java
   ```

### 3. Alur Interaksi Pengguna

<details>
<summary>Menu Utama</summary>

* **Deskripsi:** Menu utama berfungsi sebagai pusat navigasi awal aplikasi yang menyediakan pilihan akses untuk Login, Registrasi, atau Keluar dari sistem. Sistem akan mengarahkan pengguna sesuai pilihan atau menampilkan peringatan jika input tidak valid.
* **Alur:**
  1. Pengguna memasukkan username/email beserta password.
  2. Sistem memverifikasi kredensial ke basis data `pengguna` (mengecek peran: *Pengajar* atau *Pelajar*).
  3. Jika verifikasi gagal, muncul pemberitahuan kegagalan autentikasi.
  4. Jika berhasil, sistem menyimpan sesi login dan mengarahkan pengguna ke Halaman Pengajar atau Halaman Pelajar sesuai perannya.

 
<img width="1008" height="688" alt="image" src="https://github.com/user-attachments/assets/895516a1-92e0-4564-8879-d7c36490a875" />


</details>


<details>
<summary>Halaman Registrasi</summary>

* **Deskripsi:** Tempat bagi calon Pelajar baru untuk mengisikan identitas diri dan membuat akun.
* **Alur:**
  1. Pelajar mengisi formulir pendaftaran.
  2. Sistem memvalidasi kelengkapan data serta keunikan username di database.
  3. Jika data tidak valid atau username sudah digunakan, sistem menampilkan pesan peringatan.
  4. Jika data valid, sistem menyimpan akun baru ke database dan mengarahkan pengguna ke Halaman Login.
 
<img width="1009" height="630" alt="image" src="https://github.com/user-attachments/assets/589767ea-b0a8-4d1e-9226-b4f8c64ca308" />


</details>



<details>
<summary>Halaman Dashboard Pengajar</summary>

* **Deskripsi:** Pusat kendali bagi Pengajar untuk mengakses seluruh fungsi manajemen pembelajaran.
* **Alur:**
  1. Pengajar masuk ke menu dashboard utama.
  2. Sistem menampilkan opsi menu: **Kelola Materi**, **Kelola Latihan Soal**, **Kelola Kelas Online**, **Lihat Hasil Belajar**, **Kelola Rekomendasi**, **Kelola Data Pelajar**, dan **Edit Profil**.
  3. Pengajar memilih salah satu fitur untuk diproses lebih lanjut.
 
 <img width="1264" height="711" alt="image" src="https://github.com/user-attachments/assets/c7bfeff0-2838-4dd5-be3f-fda2a3536f94" />

</details>


<details>
<summary>Halaman Pengajar – Kelola Materi</summary>

* **Deskripsi:** Fitur bagi Pengajar untuk mengunggah dan memelihara bahan ajar.
* **Alur:**
  1. Sistem menampilkan daftar materi yang sudah ada.
  2. Pengajar memilih tindakan: **Tambah Materi**, **Ubah Materi**, atau **Hapus Materi**.
  3. Untuk Tambah/Ubah: Pengajar mengisi judul, topik, deskripsi, serta tautan/file materi. Sistem memvalidasi input dan menyimpan pembaruan ke database.
  4. Untuk Hapus: Pengajar mengonfirmasi penghapusan data, lalu sistem menghapus materi terkait dari database.

<img width="1261" height="706" alt="image" src="https://github.com/user-attachments/assets/51ad6d11-e7a7-4b9e-827c-464451a705ea" />

</details>

<details>
<summary>Halaman Pengajar – Kelola Latihan Soal</summary>

* **Deskripsi:** Fitur bagi Pengajar untuk menyusun bank soal dan kunci jawaban berdasarkan topik serta tingkat kesulitan.
* **Alur:**
  1. Sistem menampilkan daftar paket dan latihan soal.
  2. Pengajar dapat menambah, mengedit, atau menghapus butir soal.
  3. Pengajar mengisikan teks pertanyaan, opsi pilihan ganda (A/B/C/D), kunci jawaban, serta menentukan tingkat kesulitan (*Easy*, *Medium*, *Hard*).
  4. Sistem memvalidasi data dan memperbarui database `latihan_soal` serta `paket_soal`.

<img width="1261" height="711" alt="image" src="https://github.com/user-attachments/assets/58eeaac2-6d22-4af7-9fbe-1c8e4db86ac0" />


</details>

<details>
<summary>Halaman Pengajar – Kelola Kelas Online</summary>

* **Deskripsi:** Fitur untuk merencanakan dan membuka jadwal kelas daring interaktif.
* **Alur:**
  1. Pengajar melihat jadwal kelas online yang telah dibuat.
  2. Pengajar memilih opsi untuk membuat kelas baru, memperbarui informasi kelas (jadwal, kuota, biaya), atau membatalkan/menghapus kelas.
  3. Sistem memvalidasi data dan menyimpan perubahan ke tabel `kelas_online`.

<img width="1267" height="711" alt="image" src="https://github.com/user-attachments/assets/0d797a47-d3c8-4cfb-8fac-2dc10efb76c9" />


</details>

<details>
<summary>Halaman Pengajar – Lihat Hasil Belajar</summary>

* **Deskripsi:** Fitur untuk merekapitulasi seluruh perkembangan dan nilai siswa.
* **Alur:**
  1. Pengajar memilih menu **Lihat Hasil Belajar**.
  2. Sistem menarik dan menampilkan rekapitulasi data dari tabel `hasil_belajar` seluruh Pelajar.
  3. Pengajar dapat melihat detail nilai pengerjaan soal dan topik yang dikuasai/belum dikuasai siswa.

<img width="1261" height="706" alt="image" src="https://github.com/user-attachments/assets/83d0a519-e08f-47fe-84f3-9066c944b9ee" />


</details>

<details>
<summary>Halaman Pengajar – Kelola RekomendasiL</summary>

* **Deskripsi:** Fitur untuk mengatur batasan skor dan pesan saran belajar otomatis.
* **Alur:**
  1. Pengajar melihat daftar aturan rekomendasi belajar.
  2. Pengajar dapat menambah, mengedit, atau menghapus aturan rekomendasi berdasarkan rentang nilai (misal: skor 0–50 mendapat saran remedial).
  3. Sistem memvalidasi dan menyimpan perubahan pada tabel `rekomendasi`.

  <img width="1258" height="708" alt="image" src="https://github.com/user-attachments/assets/b01bc0f7-8b73-494e-94f3-80c9ddda02e7" />


</details>

<details>
<summary>Halaman Pengajar – Kelola Data PelajarL</summary>

* **Deskripsi:** Fitur bagi Pengajar untuk meninjau dan mengelola akun Pelajar yang terdaftar.
* **Alur:**
  1. Sistem menampilkan daftar seluruh akun Pelajar.
  2. Pengajar dapat memperbarui informasi data Pelajar atau melakukan penghapusan akun.
  3. Jika menghapus akun, sistem meminta konfirmasi terlebih dahulu sebelum menghapus data terkait di database.

<img width="1261" height="706" alt="image" src="https://github.com/user-attachments/assets/da9e7419-dbe3-461e-8f83-f0eaeee2af4d" />


</details>

<details>
<summary>Halaman Pengajar – Edit Profil</summary>

* **Deskripsi:** Pengelolaan informasi pribadi akun Pengajar.
* **Alur:**
  1. Pengajar membuka menu **Edit Profil**.
  2. Sistem menampilkan data profil saat ini.
  3. Pengajar mengubah data (seperti nama, email, atau password) dan menekan tombol simpan.
  4. Sistem memvalidasi input dan mengompensasi pembaruan ke database `pengguna` dan `pengajar`.

<img width="1257" height="705" alt="image" src="https://github.com/user-attachments/assets/e7686187-c353-450f-b8ac-39dc51b6e908" />


</details>

<details>
<summary>Halaman Dashboard Pelajar</summary>

* **Deskripsi:** Pusat navigasi utama bagi siswa untuk mengakses ruang belajar.
* **Alur:**
  1. Pelajar masuk ke menu utama Pelajar.
  2. Sistem menampilkan opsi menu: **Lihat Materi**, **Kerjakan Latihan Soal**, **Lihat Kelas Online**, **Lihat Hasil Belajar & Rekomendasi**, dan **Edit Profil**.
  3. Pelajar memilih fitur yang ingin diakses.

<img width="1266" height="714" alt="image" src="https://github.com/user-attachments/assets/e92028b8-c6ad-4deb-8600-d126c14d4adc" />

</details>

<details>
<summary>Halaman Pelajar – Lihat Materi</summary>

* **Deskripsi:** Akses membaca dan mempelajari bahan ajar.
* **Alur:**
  1. Pelajar memilih modul materi berdasarkan topik bahasan.
  2. Sistem menampilkan konten materi (teks atau link video pembelajaran).
  3. Pelajar dapat mempelajari materi lalu kembali ke menu daftar materi setelah selesai.

<img width="1267" height="706" alt="image" src="https://github.com/user-attachments/assets/abea39c3-bd1b-420b-85ff-21e9210b7461" />



</details>

<details>
<summary>Halaman Pelajar – Lihat & Kerjakan Latihan Soal</summary>

* **Deskripsi:** Modul pengerjaan latihan soal berjenjang dengan evaluasi otomatis.
* **Alur:**
  1. Pelajar memilih paket latihan soal berdasarkan topik dan tingkat kesulitan (*Easy*, *Medium*, *Hard*).
  2. Sistem menampilkan soal satu per satu beserta pilihan jawaban.
  3. Pelajar menjawab seluruh soal dan menekan tombol **Selesai/Kirim**.
  4. Sistem secara otomatis mencocokkan jawaban dengan kunci jawaban, mengkalkulasi skor akhir, serta mencatat topik terlemah.
  5. Hasil penilaian disimpan ke tabel `hasil_belajar` dan `detail_latihan`.

<img width="1263" height="706" alt="image" src="https://github.com/user-attachments/assets/a7327b5d-c339-4047-85ad-ecaac559281e" />


</details>

<details>
<summary>Halaman Pelajar – Lihat & Daftar Kelas Online</summary>

* **Deskripsi:** Modul pendaftaran sesi bimbingan daring.
* **Alur:**
  1. Pelajar melihat daftar kelas online yang sedang dibuka.
  2. Pelajar memilih kelas yang diinginkan dan melakukan pendaftaran.
  3. Sistem mengecek ketersediaan kuota peserta.
  4. Jika kuota tersedia, sistem mencatat pendaftaran ke tabel `pendaftaran_kelas_online` dengan status pembayaran (*Pending* / *Lunas*).

<img width="1258" height="706" alt="image" src="https://github.com/user-attachments/assets/b1278499-a2be-47e9-aee0-4fb8d22d58af" />


</details>

<details>
<summary>Halaman Pelajar – Lihat Hasil Belajar & Rekomendasi</summary>

* **Deskripsi:** Menampilkan rekap evaluasi belajar dan saran otomatis.
* **Alur:**
  1. Pelajar memilih menu **Hasil Belajar & Rekomendasi**.
  2. Sistem mengambil riwayat skor latihan dari database.
  3. Berdasarkan skor dan analisis topik terlemah, sistem mencocokkan aturan di tabel `rekomendasi` dan menampilkan saran strategi belajar otomatis kepada Pelajar.

<img width="1263" height="700" alt="image" src="https://github.com/user-attachments/assets/263e39d6-1d77-403a-98ae-e5af9dbdca51" />


</details>

<details>
<summary>Halaman Pelajar – Edit Profil</summary>

* **Deskripsi:** Pengelolaan informasi akun Pelajar.
* **Alur:**
  1. Pelajar membuka menu profil.
  2. Pelajar dapat mengedit nama, email, password, atau memilih opsi **Hapus Akun**.
  3. Untuk pengubahan data: Sistem memvalidasi dan menyimpan pembaruan ke database.
  4. Untuk hapus akun: Pelajar memasukkan konfirmasi password, lalu sistem menghapus data akun dari database.

<img width="1258" height="705" alt="image" src="https://github.com/user-attachments/assets/93fc9857-dd14-4d4e-92e7-d0420712fe46" />


</details>

## Kontributor

Proyek ini disusun oleh Kelompok **EDUCATE**

| No | Nama Lengkap | NIM |
|---|---|---|
| 1 | **Fariz Aufarizky** | 2509116004 |
| 2 | **Bunga Zulfa Aqila** | 2509116024 |
| 3 | **Sabrina Azhmalia Nisa** | 2509116051 | 
| 4 | **Aditya Dwinugraha Yusniandra** | 2509116114 | 
| 5 | **Muhammad Islami Pasya** | 2509116108 |

---
