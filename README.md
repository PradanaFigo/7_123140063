# Tugas Pertemuan 10: Dependency Injection & Testing (KMP)

Repositori ini berisi pengerjaan **Tugas Pertemuan 10** untuk mata kuliah **Pemrograman Perangkat Bergerak (KMP)**. Aplikasi telah di-*refactor* menggunakan Dependency Injection dan diuji dengan serangkaian Unit Test serta UI Test.
 
---

## Identitas Mahasiswa

| Field | Detail |
|---|---|
| **Nama** | Pradana Figo Ariansya |
| **NIM** | 123140063 |
| **Program Studi** | Teknik Informatika |
| **Institut** | Institut Teknologi Sumatera (ITERA) |
 
---

## Tujuan Pembelajaran

1. Mengimplementasikan **Dependency Injection (DI)** menggunakan **Koin** pada platform Kotlin Multiplatform (KMP).
2. Memisahkan modul-modul aplikasi *(Data, Network, ViewModel)* ke dalam berkas `AppModule.kt`.
3. Menulis dan menjalankan **Unit Test** untuk Repository dan ViewModel menggunakan **MockK** dan **Turbine**.
4. Menulis dan menjalankan **UI Test** *(Instrumented Test)* menggunakan Compose UI Test Rule di Emulator Android.
5. Memenuhi standar *Minimum Code Coverage* sebesar **60%**.
---

##  Teknologi & Library

| Kategori | Library / Tool |
|---|---|
| **UI Framework** | Compose Multiplatform |
| **Dependency Injection** | Koin (`koin-core`, `koin-compose`, `koin-android`) |
| **Unit Testing** | `kotlin.test`, MockK, Turbine, Coroutines Test (`UnconfinedTestDispatcher`) |
| **UI Testing** | `androidx.compose.ui:ui-test-junit4` |
 
---

## Skenario Pengujian

### 1. Unit Test — ViewModel & Repository

Pengujian logika bisnis tanpa melibatkan UI Thread Android.

| Test Class | Skenario yang Diuji |
|---|---|
| `NoteRepositoryTest` | Operasi pengambilan, penambahan, penghapusan, dan pembaruan catatan menggunakan *relaxed mock* (mencegah bentrok dengan SQLDelight) |
| `NotesViewModelTest` | State awal/loading menggunakan *Turbine*; pemanggilan `addNote`, `deleteNote`, dan `toggleFavorite` agar memicu interaksi yang tepat ke Repository |

### 2. UI Test — HomeScreen

Skenario yang disimulasikan menggunakan Emulator Android:

| Test Case | Deskripsi |
|---|---|
| `emptyState_isDisplayed_whenNoNotes` | Memastikan komponen pesan kosong muncul saat hasil pencarian tidak ditemukan |
| `searchBar_allowsInput` | Mensimulasikan pengetikan teks pada kolom pencarian dan memverifikasi teks tertampil |
| `settingsButton_isClickable` | Menguji interaksi klik pada tombol navigasi pengaturan |
 
---

## Bukti Code Coverage

> Minimum coverage yang disyaratkan: **60%**

![Code Coverage Screenshot](GANTI_DENGAN_LINK_ATAU_PATH_GAMBAR_SCREENSHOT_COVERAGE)

*Screenshot di atas menunjukkan bahwa pengujian telah melampaui batas minimum 60% pada modul yang diuji.*
 
---

## Video Demo (±45 Detik)

Video demonstrasi menampilkan proses *Running UI Test* di emulator dan hasil eksekusi Unit Test.

>  **[Tonton Video Demo Tugas 10](GANTI_DENGAN_LINK_YOUTUBE_ATAU_GDRIVE)**
 
---
