# Sistem Pengelolaan Buku

Proyek ini adalah aplikasi berbasis Java sederhana untuk mengelola data buku. Aplikasi dirancang menggunakan prinsip Object-Oriented Programming (OOP) dengan struktur modular yang terorganisasi. Fitur utama mencakup penambahan, penghapusan, dan penampilan daftar buku, serta validasi data untuk memastikan integritas informasi.

---

## **Fitur**
- **Tambah Buku**: Pengguna dapat menambahkan buku baru dengan memasukkan informasi seperti judul, penulis, penerbit, dan tahun terbit.
- **Hapus Buku**: Menghapus data buku tertentu dari sistem berdasarkan ID uniknya.
- **Daftar Buku**: Menampilkan semua buku yang tersimpan dalam sistem.
- **Validasi Data**: Memastikan data yang dimasukkan sesuai dengan aturan yang telah ditentukan.

---

## **Struktur Proyek**
Proyek ini terdiri dari beberapa paket yang mengelompokkan tanggung jawab masing-masing komponen:

1. **`entity`**:
    - **Buku**: Merepresentasikan entitas buku dengan atribut seperti `id`, `judul`, `penulis`, `penerbit`, dan `tahunTerbit`.

2. **`repository`**:
    - **BukuRepository** dan **BukuRepositoryImpl**: Bertugas mengelola operasi penyimpanan, pengambilan, dan penghapusan data buku.

3. **`service`**:
    - **BukuService** dan **BukuServiceImpl**: Menyediakan logika bisnis utama untuk menghubungkan antara controller dan repository.

4. **`controller`**:
    - **BukuController**: Mengelola alur data dari dan ke antarmuka pengguna.

5. **`validation`**:
    - **BukuValidation**: Menangani validasi data buku sebelum diproses lebih lanjut.

6. **`view`**:
    - **BukuView**: Menyediakan antarmuka berbasis teks untuk interaksi dengan pengguna.

7. **`utils`**:
    - Berisi fungsi utilitas yang mendukung operasi seperti pengelolaan input dan output.

---

## **Cara Menjalankan Aplikasi**
1. Pastikan Anda memiliki **Java Development Kit (JDK)** versi 8 atau lebih tinggi.
2. Jalankan file utama: `Responsi1_PPBO_L0123068_SC.java`.
3. Ikuti instruksi di terminal untuk menambah, menghapus, atau menampilkan buku.

---

## **Pengembangan Lebih Lanjut**
Aplikasi ini dapat dikembangkan lebih jauh dengan fitur tambahan, seperti:
- Pencarian buku berdasarkan atribut tertentu (judul, penulis, dll.).
- Integrasi dengan basis data untuk penyimpanan permanen.
- Implementasi antarmuka berbasis GUI (Graphical User Interface).
- Penerapan pola desain **MVC (Model-View-Controller)** secara penuh.

---

## **Teknologi yang Digunakan**
- **Bahasa Pemrograman**: Java
- **Paradigma**: Object-Oriented Programming (OOP)
- **Pendekatan Desain**: Modular dengan pemisahan tanggung jawab (Separation of Concerns).

---

## **Kontribusi**
Jika Anda ingin berkontribusi pada proyek ini:
1. Fork repositori ini.
2. Lakukan perubahan pada branch baru.
3. Kirim pull request dengan penjelasan lengkap terkait perubahan yang Anda lakukan.

---

## **Lisensi**
Proyek ini bebas digunakan dan dimodifikasi untuk tujuan belajar dan pengembangan.

