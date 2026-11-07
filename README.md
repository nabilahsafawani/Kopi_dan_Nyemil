# Kopi dan Nyemil ☕🍩

Aplikasi Android mobile untuk pemesanan kopi dan camilan ("Kopi dan Nyemil") yang dikembangkan menggunakan **Kotlin** dan **Jetpack Compose (Material 3)** berdasarkan spesifikasi wireframe dan alur aplikasi.

---

## 📌 Deskripsi Aplikasi

**Kopi dan Nyemil** adalah aplikasi pemesanan makanan dan minuman berbasis Android yang memungkinkan pengguna memilih aneka varian kopi, matcha, donat, dan roti, mengelola profil pengguna, mengisi alamat pengiriman, serta melakukan konfirmasi pemesanan dengan antarmuka modern yang responsif dan *user-friendly*.

---

## ✨ Fitur Utama (8 Halaman Sesuai Wireframe)

Aplikasi ini mencakup seluruh 8 tampilan yang berada pada wireframe tugas:

1. **Splash / Welcome Screen (`1. Splash / Welcome`)**
   - Menampilkan logo branding, judul aplikasi "Kopi dan Nyemil", tagline *"Get a taste of your wish"*, dan tombol **"Start now"** untuk masuk ke halaman opsi.
2. **Sign Up Options Screen (`2. Sign up`)**
   - Halaman navigasi awal anggota untuk memilih opsi pendaftaran baru (**Register**) atau masuk (**Login**).
3. **Login User Screen (`3. Login User`)**
   - Form autentikasi login pengguna dengan input *username* dan *password* tersembunyi (*secure visual transformation*).
4. **Register Screen (`4. Register`)**
   - Form pendaftaran akun anggota baru mencakup input *nama lengkap*, *username*, dan *password*.
5. **Home Screen (`5. Home`)**
   - Header salam personalisasi *"Halo [Nama],"*.
   - Filter kategori produk (**Semua**, **Makanan**, **Minuman**).
   - Daftar menu makanan/minuman dengan minimal 7 pilihan item (melebihi batas minimal 5 item):
     1. **Kopi Susu Gula Aren** — Espresso, susu, gula aren (Rp 22.000)
     2. **Donat Cokelat** — Donat empuk, glaze cokelat (Rp 12.000)
     3. **Sourdough Loaf** — Roti fermentasi alami (Rp 35.000)
     4. **Cappuccino** — Espresso, foam susu lembut (Rp 25.000)
     5. **Matcha Latte** — Matcha jepang, susu segar (Rp 27.000)
     6. **Croissant Butter** — Pastry renyah dengan mentega gurih (Rp 20.000)
     7. **Americano Cold Brew** — Kopi hitam dingin menyegarkan (Rp 18.000)
   - *Bottom Navigation Bar* (*Home*, *Order*, *Profile*) dan *Floating Order Bar* saat item dipilih.
6. **Profile Screen (`6. Profile`)**
   - Menampilkan header *"Halo [Nama],"*, seksi *"Profile Saya"*, dan *"Profile Setting"*.
   - Input bidang `nama` pengguna dengan tombol **Save** yang secara otomatis memperbarui nama profil di seluruh aplikasi secara *real-time* disertai notifikasi *Snackbar*.
7. **Alamat Pengiriman (Order) Screen (`7. Alamat pengiriman (Order)`)**
   - Ringkasan item pesanan beserta total pembayaran otomatis.
   - Form pengiriman mencakup bidang input `Nama Lengkap`, `Alamat` (multiline), dan `Patokan atau detail lengkap`.
   - Tombol **"Order dan Kirim"** untuk memvalidasi dan mengirimkan pesanan.
8. **Konfirmasi Pesanan Screen (`8. Konfirmasi pesanan`)**
   - Tampilan pesan sukses pemesanan dengan ikon badge hijau bulat **"OK"**.
   - Pesan konfirmasi: *"Terima kasih sudah memesan! Tunggu aja sebentar, kurir kami segera mengantarkan pesanamu!"*.
   - Kartu rincian lengkap pesanan (Penerima, Alamat, Detail item, Total bayar).
   - Tombol **"Oke"** untuk menyelesaikan pesanan dan kembali ke halaman Home.

---

## 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman**: Kotlin 2.0+
- **UI Framework**: Declarative UI with Jetpack Compose (Material Design 3)
- **Navigasi**: Jetpack Navigation Compose
- **State Management**: ViewModel & StateFlow (`androidx.lifecycle.ViewModel`)
- **Desain Theme**: Custom Warm Coffee Color Palette (Material3 `lightColorScheme`)
- **Min SDK**: API Level 27 (Android 8.1 Oreo)
- **Target SDK**: API Level 37 (Android 15+)

---

## 📁 Struktur Direktori Proyek

```text
lat.pam.kopidannyemil/
├── model/
│   ├── FoodItem.kt          # Model data produk makanan/minuman & data sampel static
│   └── Order.kt             # Model data item keranjang & detail pesanan
├── ui/
│   ├── components/
│   │   └── BottomNavBar.kt  # Komponen Bottom Navigation Bar (Home, Order, Profile)
│   ├── navigation/
│   │   └── Screen.kt        # Rute navigasi halaman
│   ├── screens/
│   │   ├── SplashScreen.kt
│   │   ├── SignUpOptionsScreen.kt
│   │   ├── LoginScreen.kt
│   │   ├── RegisterScreen.kt
│   │   ├── HomeScreen.kt
│   │   ├── ProfileScreen.kt
│   │   ├── AddressOrderScreen.kt
│   │   └── OrderConfirmationScreen.kt
│   └── theme/
│       ├── Color.kt         # Warna palet tema Kopi dan Nyemil
│       └── Theme.kt         # Material 3 Theme Wrapper
├── viewmodel/
│   └── AppViewModel.kt      # Manajemen state terpusat (Profil, Cart, Order)
└── MainActivity.kt          # Host Activity & NavHost Setup
```

---

## 🚀 Cara Menjalankan Aplikasi

1. **Prasyarat**:
   - Install **Android Studio** (Koala / Ladybug / version 2024.1+ direkomendasikan).
   - Android SDK API 34+ / JDK 17 atau versi yang mendukung Kotlin 2.0+.

2. **Langkah-langkah**:
   - Clone repository ini ke komputer Anda:
     ```bash
     git clone <URL_REPOSITORY_GITHUB_ANDA>
     ```
   - Buka project di Android Studio melalui menu **File > Open** lalu pilih folder `KopidanNyemil`.
   - Tunggu proses **Gradle Sync** selesai.
   - Sambungkan emulator Android atau perangkat fisik melalui USB Debugging.
   - Klik tombol **Run** (`Shift + F10`) di Android Studio.

---

## 📱 Tangkapan Layar Tampilan (Screenshots)

| 1. Splash / Welcome | 2. Sign up | 3. Login User | 4. Register |
|:---:|:---:|:---:|:---:|
| *(Welcome Logo & Start Now)* | *(Register & Login Options)* | *(Form Login)* | *(Form Register)* |

| 5. Home | 6. Profile | 7. Alamat (Order) | 8. Konfirmasi Pesanan |
|:---:|:---:|:---:|:---:|
| *(Daftar Menu & Filter)* | *(Setting Nama)* | *(Form Alamat)* | *(Badge OK & Confirmation)* |

---

## 📝 Catatan Tugas

- Seluruh data aplikasi disimpan dalam memori (*in-memory state management*) menggunakan ViewModel & StateFlow tanpa bergantung pada database eksternal.
- Desain disesuaikan presisi dengan wireframe yang disediakan dengan peningkatan estetika modern menggunakan warna dan bentuk Material 3.
