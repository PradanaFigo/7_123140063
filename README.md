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
<img width="1600" height="850" alt="image" src="https://github.com/user-attachments/assets/69607056-f4ef-4ae8-8ec3-593e9fbb0585" />
<img width="1600" height="801" alt="image" src="https://github.com/user-attachments/assets/4a9c73c1-55c1-4b2c-bf90-856135563c4b" />
<img width="1600" height="804" alt="image" src="https://github.com/user-attachments/assets/a9dcc9f2-c354-45ff-a6a2-8ddd3dbb90eb" />

 
---

## Video Demo 

Video demonstrasi menampilkan proses *Running UI Test* di emulator dan hasil eksekusi Unit Test.

https://github.com/user-attachments/assets/2761761e-e50a-4d75-bef0-db0eadf12b66

---
