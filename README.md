#  Aplikasi Catatan AI (KMP Notes App)

Aplikasi pencatatan cerdas berbasis **Kotlin Multiplatform (KMP)** yang dilengkapi dengan integrasi **Google Gemini AI**. Aplikasi ini tidak hanya berfungsi sebagai buku catatan digital biasa, tetapi juga dapat merangkum dan menerjemahkan teks secara otomatis menggunakan kecerdasan buatan.

---

## Fitur Utama

* **Manajemen Catatan:** Tambah, edit, simpan, dan hapus catatan dengan antarmuka yang bersih.
* **🤖 Rangkum AI:** Merangkum teks catatan yang panjang menjadi poin-poin singkat menggunakan model bahasa Google Gemini.
* **🌐 Translate AI:** Menerjemahkan isi catatan secara otomatis menggunakan kecerdasan buatan.
* **Penyimpanan Lokal Offline:** Data catatan disimpan secara lokal dan aman di dalam perangkat menggunakan SQLDelight.
* **UI Modern:** Dibangun menggunakan Compose Multiplatform untuk tampilan yang responsif.

---

##  Teknologi yang Digunakan

| Komponen | Teknologi |
|---|---|
| Bahasa Pemrograman | Kotlin |
| Framework | Kotlin Multiplatform (KMP) |
| UI Toolkit | Compose Multiplatform (Jetpack Compose) |
| Networking | Ktor Client |
| Database | SQLDelight |
| Artificial Intelligence | Google Gemini API (Gemini 1.5 Flash) |

---

## Cara Instalasi & Menjalankan Aplikasi

### 1. Clone Repositori

```bash
git clone [URL_REPOSITORI_KAMU]
```

### 2. Buka Proyek

Buka proyek di **Android Studio**.

### 3. Konfigurasi API Key Google Gemini

Aplikasi ini membutuhkan API Key dari Google AI Studio.

* Dapatkan API Key di [Google AI Studio](https://aistudio.google.com/).
* Buka file konfigurasi di path berikut:
  ```
  composeApp/src/androidMain/kotlin/com/example/project/platform/ApiConfig.android.kt
  ```
* Masukkan API Key kamu ke dalam variabel:

```kotlin
package com.example.project.platform

actual object ApiConfig {
    actual val geminiApiKey: String = "MASUKKAN_API_KEY_KAMU_DISINI"
}
```

### 4. Bersihkan dan Bangun Proyek *(Wajib agar API Key terbaca sistem)*

* Di Android Studio, klik menu **Build > Clean Project**.
* Setelah selesai, klik menu **Build > Rebuild Project**.

### 5. Jalankan Aplikasi

* Pilih emulator Android atau sambungkan perangkat fisikmu.
* Klik tombol **Run** ▶️ di Android Studio.

---

##  Pengembang

| | |
|---|---|
| **Nama** | Pradana Figo Ariansya |
| **NIM** | 123140063 |
| **Program Studi** | Teknik Informatika |
| **Instansi** | Institut Teknologi Sumatera (ITERA) |