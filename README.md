<div align="center">

<h1> 📚 Manajemen Buku Perpustakaan</h1>

<img src="./assets/Library - OOP.png" />

<p>🚀 Sebuah aplikasi berbasis Java dengan paradigma OOP untuk mengelola data buku dengan database MySQL. </p>

<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java Badge">
<img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL Badge">
<img src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntellijIDEA Badge">

<p align="justify">Aplikasi ini dikembangkan menggunakan <b>Object-Oriented Programming (OOP)</b> dengan struktur modular yang rapi. Fitur utama mencakup <b>CRUD buku, pencarian, validasi data</b>, serta <b>Graphical User Interface </b> (GUI) untuk kemudahan penggunaan. </p>

</div>

---

## 📌 Table of Contents

1. ✨ [Fitur](#fitur)
2. 📈 [Pengembangan Dari Sebelumnya](#pengembangan-dari-sebelumnya)
3. 📂 [Struktur Proyek](#struktur-proyek)
4. 🛠 [Sebelum Menjalankan Program](#sebelum-menjalankan-program)
5. 🚀 [Cara Menjalankan Aplikasi](#cara-menjalankan-aplikasi)
6. 🔧 [Teknologi yang Digunakan](#teknologi-yang-digunakan)
7. 🤝 [Kontribusi](#kontribusi)
8. 📜 [Lisensi](#lisensi)

---

## <a name="fitur"> ✨ Fitur </a>

✅ **Tambah Buku**: Tambahkan buku baru dengan detail seperti **judul, penulis, penerbit, dan tahun terbit**.  
✅ **Hapus Buku**: Hapus buku dari sistem berdasarkan **ID unik**.  
✅ **Daftar Buku**: Tampilkan semua daftar buku yang tersimpan di database.  
✅ **Validasi Data**: Pastikan data buku sesuai aturan sebelum disimpan.  
✅ **Cari Buku**: Temukan buku berdasarkan **keyword pencarian** yang dimasukkan pengguna.  
✅ **GUI Modern**: Tidak lagi berbasis **console**, sekarang dengan tampilan lebih user-friendly.  
✅ **Terintegrasi dengan MySQL**: Data buku disimpan ke **database MySQL**, bukan file `.txt` lagi.

---

## <a name="pengembangan-dari-sebelumnya">📈 Pengembangan Dari Sebelumnya</a>

[Proyek 2](./Proyek%202/) merupakan pengembangan lanjutan dari [Proyek 1](./Proyek%201/).

| Proyek 1                          | Proyek 2                              |
| --------------------------------- | ------------------------------------- |
| ❌ Berbasis **console**           | ✅ **Graphical User Interface (GUI)** |
| ❌ Data disimpan di **file .txt** | ✅ Data disimpan di **MySQL**         |

---

## <a name="struktur-proyek">📂 Struktur Proyek</a>

Proyek ini menggunakan pendekatan **Separation of Concerns (SoC)** untuk memisahkan tanggung jawab tiap komponen:

📌 **`entity/`**: Menyimpan class model `Buku.java` yang merepresentasikan data buku.  
📌 **`repository/`**: Berisi `BukuRepository` untuk interaksi dengan **database**.  
📌 **`service/`**: Menghubungkan antara **controller** dan **repository**.  
📌 **`controller/`**: Mengelola alur data dari dan ke **antarmuka pengguna**.  
📌 **`validation/`**: Menangani **validasi input** sebelum disimpan.  
📌 **`view/`**: Menyediakan **Graphical User Interface (GUI)** bagi pengguna.  
📌 **`utils/`**: Berisi fungsi tambahan seperti **pengelolaan input/output**.

---

## <a name="sebelum-menjalankan-program">🛠 Sebelum Menjalankan Program</a>

1. **Pastikan MySQL sudah terinstall**, bisa menggunakan **XAMPP** atau **Laragon**.
2. **Buat database** `Perpustakaan` di MySQL.
3. **Jalankan SQL berikut** untuk membuat tabel **buku**:

```sql
CREATE DATABASE Perpustakaan;

USE Perpustakaan;

CREATE TABLE buku (
    id INT(3) AUTO_INCREMENT PRIMARY KEY,
    judul VARCHAR(255) NOT NULL,
    penulis VARCHAR(255) NOT NULL,
    penerbit VARCHAR(255) NOT NULL,
    tahun_terbit INT(4) NOT NULL
);

INSERT INTO buku (judul, penulis, penerbit, tahun_terbit) VALUES
('Bumi Manusia', 'Pramoedya Ananta Toer', 'Gramedia', 1980),
('Orang-Orang Biasa', 'Andrea Hirata', 'Bentang Pustaka', 2022),
('Laskar Pelangi', 'Andrea Hirata', 'Gramedia', 2005);
```

---

## <a name="cara-menjalankan-aplikasi">🚀 Cara Menjalankan Aplikasi</a>

1. **Pastikan JDK 8+ terinstall**.
2. **Jalankan MySQL Server** melalui PHPMyAdmin atau CLI.
3. **Jalankan file utama** `Responsi2_PPBO_L0123068_SC.java`.
4. **Mulai mengelola buku** melalui GUI aplikasi!

---

## <a name="teknologi-yang-digunakan">🔧 Teknologi yang Digunakan</a>

- ☕ **Java** → Bahasa pemrograman utama.
- 🗄 **MySQL** → Database untuk menyimpan data buku.
- 🎨 **Java Swing** → GUI yang modern dan interaktif.
- 🏗 **MVC Architecture** → Pemisahan logika aplikasi agar lebih terstruktur.

---

## <a name="kontribusi">🤝 Kontribusi </a>

Kontribusi sangat diterima! Ikuti langkah berikut untuk berkontribusi:

1. **Fork** repositori ini.
2. **Buat branch baru** (`git checkout -b feature-xyz`).
3. **Lakukan perubahan** dan **commit** (`git commit -m "Menambahkan fitur XYZ"`).
4. **Push branch ke GitHub** (`git push origin feature-xyz`).
5. **Buka pull request**, dan tunggu review! 🚀

---

## <a name="lisensi">📜 Lisensi </a>

Proyek ini **gratis** untuk digunakan dan dimodifikasi untuk keperluan belajar dan pengembangan.

💡 **Keep Coding, Stay Awesome!** 💻✨
