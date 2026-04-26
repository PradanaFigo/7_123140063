#  Daily Note App — Kotlin Multiplatform

> **Tugas Praktikum Pengembangan Aplikasi Mobile — Pertemuan 8**
>
> Aplikasi pencatatan harian modern yang dibangun menggunakan **Compose Multiplatform (KMP)**, dirombak secara arsitektural pada rilis minggu ke-8 menggunakan **Koin Dependency Injection** dan integrasi fitur native perangkat melalui pola **expect/actual**.
 
---

## Informasi Pengembang

| Field | Detail |
|---|---|
| **Nama** | Pradana Figo Ariasya |
| **NIM** | 123140063 |
| **Program Studi** | Teknik Informatika |
| **Instansi** | Institut Teknologi Sumatera (ITERA) |
 
---

## Fitur Utama & Pembaruan (Minggu 8)

### 1. Dependency Injection (Koin)
Seluruh *dependencies* seperti Database, Repository, ViewModels, dan Platform Services kini disuntikkan secara dinamis menggunakan Koin KMP (`KoinContext` & `KoinComponent`).

### 2. Device Info (`expect/actual`)
Mengambil dan menampilkan nama model perangkat, versi OS, dan versi aplikasi secara *real-time* di layar Pengaturan.

### 3. Network Monitor (`expect/actual`)
Memantau status koneksi internet pengguna. Jika koneksi terputus, aplikasi memunculkan *banner* peringatan merah **"Mode Offline"** yang dilengkapi *timer* otomatis.

### 4.[BONUS] Battery Info (`expect/actual`)
Membaca persentase kapasitas baterai saat ini dan mendeteksi status pengisian daya (*charging*), ditampilkan dengan *Progress Bar* dinamis.

### 5. Offline-first & CRUD
Pembuatan, pembacaan, pembaruan, dan penghapusan catatan yang sepenuhnya tersimpan di memori lokal (*database*).

### 6. Dynamic Search & DataStore
Fitur pencarian catatan secara instan dan penyimpanan preferensi pengguna yang tetap tersimpan meski aplikasi ditutup.
 
---

## Architecture Diagram (DI & Platform APIs)

```
UI Layer (Compose Multiplatform)
       │ (koinInject)
       ▼
[ ViewModels ] ──► (StateFlow) ──► HomeScreen & SettingsScreen
       │
       ├─► NoteViewModel
       └─► SettingsViewModel
       │ (Constructor Injection)
       ▼
Data Layer & Platform Layer (Koin Modules — AppModule.kt)
       │
       ├─► [ Repository ] ──► NoteRepository & SettingsManager
       ├─► [ Database ]   ──► SQLDelight (NotesDatabase)
       │
       └─► [ Platform APIs (expect/actual pattern) ]
               ├─► DeviceInfo    (Model, OS, App Version)
               ├─► NetworkMonitor (ConnectivityManager)
               └─► BatteryInfo   (BatteryManager)
```
 
---

## Dokumentasi Antarmuka (Screenshots)

| Layar Utama & Kalender | Network Offline Indicator | Device & Battery Info |
|:---:|:---:|:---:|
| <img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/df96896c-e493-45ea-aa06-8fbcc11c047e" />| <img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/c4c0ff49-50f8-42b7-b773-41169f746c44" />| <img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/8f1423ec-0287-4377-b03f-91a54f5d50a4" />|
 
---

## Video Demonstrasi

Tonton video demo aplikasi (durasi ±45 detik) untuk melihat semua fitur berjalan secara langsung:

https://github.com/user-attachments/assets/8aaa6e9f-fb0e-451d-bcae-47b555cc4ee2

---

## Teknologi yang Digunakan

| Kategori | Teknologi |
|---|---|
| **Bahasa** | Kotlin |
| **Framework UI** | Compose Multiplatform (Android & iOS ready) |
| **Dependency Injection** | Koin (`koin-core`, `koin-compose`) |
| **Database** | SQLDelight |
| **Preferences** | Multiplatform Settings |
| **Date & Time** | `kotlinx-datetime` |
| **Asynchronous** | Kotlin Coroutines & Flow |
 
---
