# Daily Note App — Compose Multiplatform (KMP)

> Aplikasi pencatatan harian modern berbasis **Kotlin Multiplatform** dengan penyimpanan lokal persisten, manajemen preferensi pengguna, dan UI/UX yang intuitif.
 
---

## Informasi Mahasiswa

| Field | Detail |
|---|---|
| **Nama** | Pradana Figo Ariansya |
| **NIM** | 123140063 |
| **Program Studi** | Teknik Informatika |
| **Instansi** | Institut Teknologi Sumatera (ITERA) |
| **Mata Kuliah** | Praktikum — Pertemuan 7 |
 
---

## Deskripsi Proyek

**Daily Note App** adalah aplikasi pencatatan harian (*daily journal*) yang dibangun menggunakan **Compose Multiplatform (KMP)**. Proyek ini difokuskan pada implementasi:

- Penyimpanan data lokal yang persisten menggunakan **SQLDelight**
- Manajemen pengaturan pengguna berbasis **multiplatform-settings**
- Antarmuka pengguna (UI/UX) modern dengan tema *Teal & Peach*
- Arsitektur **MVVM** yang bersih dan terstruktur
---

## Fitur Utama

### 1. Database Lokal (SQLDelight)
Menggunakan **SQLDelight** untuk penyimpanan data yang bersifat *offline-first*. Semua catatan tersimpan secara lokal di perangkat tanpa membutuhkan koneksi internet.

### 2. Operasi CRUD Lengkap
Pengguna dapat melakukan operasi penuh terhadap catatan:
- **Create** — Menambah catatan baru
- **Read** — Membaca daftar dan detail catatan
- **Update** — Mengedit judul, isi, atau status favorit
- **Delete** — Menghapus catatan berdasarkan ID
### 3. Pencarian (Search Functionality)
Filter catatan berdasarkan judul secara instan melalui **Search Bar** di layar utama tanpa membutuhkan query tambahan ke database.

### 4. Manajemen Pengaturan (DataStore)
Menyimpan preferensi pengguna secara persisten menggunakan `multiplatform-settings`, meliputi:
- **Nama Profil** pengguna
- **Urutan Catatan** (Terbaru / Terlama)
### 5. Modern UI/UX
- Desain **Timeline** harian yang elegan
- Tema warna **Teal & Peach**
- **Beveled corners** (sudut membulat) pada kartu catatan
- **Horizontal Calendar** interaktif untuk navigasi tanggal
- Sapaan dinamis berdasarkan waktu hari
### 6. State Handling
Implementasi **Empty State** yang informatif ditampilkan saat:
- Daftar catatan masih kosong
- Hasil pencarian tidak ditemukan
---

## Skema Database

Aplikasi menggunakan tabel `Note` dengan skema berikut:

```sql
CREATE TABLE Note (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    title      TEXT    NOT NULL,
    content    TEXT    NOT NULL,
    isFavorite INTEGER DEFAULT 0,
    created_at INTEGER NOT NULL
);
```

### Query Utama

| Query | Deskripsi |
|---|---|
| `selectAll` | Mengambil semua catatan berurutan berdasarkan waktu |
| `insert` | Menambahkan catatan baru ke database |
| `update` | Memperbarui isi atau status favorit catatan |
| `delete` | Menghapus catatan berdasarkan ID |
 
---

## Teknologi yang Digunakan

| Kategori | Teknologi |
|---|---|
| **Language** | Kotlin |
| **UI Framework** | Compose Multiplatform |
| **Navigation** | Jetpack Navigation Compose |
| **Local Database** | SQLDelight |
| **Preferences** | Multiplatform Settings (DataStore-like) |
| **Date & Time** | kotlinx-datetime |
| **Architecture** | MVVM (Model-View-ViewModel) |
 
---

## Dokumentasi Antarmuka (Screenshots)

| Layar Utama (Home) | Pencarian & Empty State | Pengaturan (Settings) |
|:---:|:---:|:---:|
| ![Home](screenshots/home.png) | ![Search](screenshots/search_empty.png) | ![Settings](screenshots/settings.png) |
| Sapaan dinamis & Kalender | State saat data kosong | Simpan nama & Sort order |

| Tambah/Edit Catatan |
|:---:|
| ![Add/Edit](screenshots/add_edit.png) |
| Form input modern |
 
---

## 🎥 Video Demonstrasi

Tonton video demo aplikasi melalui tautan di bawah ini:

### 👉 [Link Video Demo Aplikasi — Klik di Sini](#)

**Isi Video (Durasi ±45 Detik):**