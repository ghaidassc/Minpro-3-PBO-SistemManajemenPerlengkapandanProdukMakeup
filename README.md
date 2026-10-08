Nama: Ghaida Suci Nahiza      
NIM: 2509116077     

# Sistem Manajemen Perlengkapan dan Produk Makeup💄💅🛍️

## 1. Deskripsi Singkat
Sistem Manajemen Perlengkapan dan Produk Makeup merupakan program berbasis konsol (CLI) yang digunakan untuk mencatat dan mengelola data inventaris barang rias secara terstruktur. Program ini dapat menangani dua jenis barang utama, yaitu kosmetik (Produk Makeup) dan alat atau aplikator (Perlengkapan Makeup). Setiap data barang memiliki atribut umum berupa ID Barang, Nama Barang, Merk, Harga, dan Stok. Program dilengkapi dengan sistem penanganan input (defensive programming) untuk mencegah program terhenti tiba-tiba (crash) akibat kesalahan format masukan pengguna, dengan batasan maksimal 3 kali percobaan salah sebelum aksi dibatalkan.

Program membedakan barang inventaris menjadi dua entitas spesifik: Produk Makeup yang menyimpan informasi khusus berupa varian atau shade warna riasan, serta Perlengkapan Makeup yang menyimpan informasi khusus berupa material atau bahan baku alat. Pengguna dapat menjalankan fitur pengelolaan data melalui menu interaktif, yaitu Tambah Data Barang, Tampilkan Semua Barang, Ubah Data Barang, Hapus Data Barang, dan Keluar. Data selama program berjalan disimpan secara dinamis menggunakan ArrayList<Makeup>.

Program ini dikembangkan sebagai pemenuhan tugas Mini Project 3 pada mata kuliah Pemrograman Berorientasi Objek dengan menerapkan arsitektur Model-View-Controller (MVC), konsep Abstraction (abstract class & abstract method), Polymorphism (method overriding & overloading), Inheritance, Encapsulation, serta penambahan Interface.

---

## 2. Tujuan Program  
Program ini dibuat sebagai pemenuhan tugas Mini Project 3 pada mata kuliah Pemrograman Berorientasi Objek melalui penerapan arsitektur perangkat lunak Model-View-Controller (MVC) serta konsep lanjutan PBO (Abstraction, Polymorphism, dan Interface).

Tujuan program adalah:  
* Mengelola data inventaris barang rias secara terorganisasi dan dinamis menggunakan struktur data `ArrayList`.
* Memisahkan hierarki entitas data barang inventaris menjadi kategori kosmetik (`ProdukMakeup`) dan perlengkapan alat (`PerlengkapanMakeup`) menggunakan konsep inheritance.
* Mengimplementasikan konsep abstraction melalui abstract class `Makeup` dan abstract method `getKategori()`.
* Menerapkan prinsip polymorphism melalui method overriding dan method overloading pada method `tampilkanData()`.
* Memanfaatkan interface `KelolaBarang` sebagai kontrak standarisasi perilaku pencetakan data barang.
* Menyediakan fitur CRUD lengkap (Create, Read, Update, Delete) yang dilengkapi sistem validasi masukan secara defensif (*defensive programming*).

---

## 3. Struktur Package
Program disusun menggunakan pola arsitektur Model-View-Controller (MVC) untuk memastikan pemisahan tanggung jawab fungsional (separation of concerns):


| Package | Class / Interface | Peran |
| --- | --- | --- |
| `main` | `Main` | Menjadi *entry point* program. Sesuai evaluasi, kelas ini dibuat sangat ringkas dan hanya memanggil 1 baris method untuk menjalankan aplikasi. |
| `controller` | `MakeupController` | Menghubungkan View dan Model, mengelola `ArrayList<Makeup>`, memproses logika CRUD, memuat dummy data, serta menangani alur validasi masukan pengguna. |
| `model` | `KelolaBarang` *(Interface)* | Menjadi kontrak interface yang mendefinisikan metode dasar penampilan data (`tampilkanData()`). |
| `model` | `Makeup` *(Abstract Class)* | Superclass abstrak yang mengimplementasikan interface `KelolaBarang`, menyimpan atribut inti, menyediakan method konkrit, method overloading, serta abstract method `getKategori()`. |
| `model` | `ProdukMakeup` | Subclass dari `Makeup` untuk entitas kosmetik dengan atribut spesifik varian atau shade riasan. |
| `model` | `PerlengkapanMakeup` | Subclass dari `Makeup` untuk entitas aplikator/alat rias dengan atribut spesifik material atau bahan baku. |
| `view` | `MakeupView` | Mengatur seluruh tampilan antarmuka konsol, mencetak format teks menu/pesan, serta menangani masukan teks pengguna lewat `Scanner`. |

### Diagram Struktur Direktori
```text
src/
└── main/
    └── java/
        ├── main/
        │   └── Main.java
        ├── controller/
        │   └── MakeupController.java
        ├── model/
        │   ├── KelolaBarang.java
        │   ├── Makeup.java
        │   ├── ProdukMakeup.java
        │   └── PerlengkapanMakeup.java
        └── view/
            └── MakeupView.java
```

---

## 4. Menu Program   
Menu utama yang tersedia pada sistem adalah:

```text
=============================================
 SISTEM MANAJEMEN PERLENGKAPAN & PRODUK MAKEUP
=============================================
1. Tambah Data Barang
2. Tampilkan Semua Barang
3. Ubah Data Barang
4. Hapus Data Barang
5. Keluar
Pilih menu (1-5):
```
### Rincian Fungsi Menu:

**1. Tambah Data Barang**  
   Digunakan untuk memasukkan data barang baru ke dalam inventaris. Pengguna memilih kategori terlebih dahulu (Produk Makeup atau Perlengkapan Makeup), kemudian mengisikan ID barang (unik), nama, merk, harga (harus > 0), stok (minimal 1), serta data spesifik kategori (shade/varian untuk produk kosmetik, atau material bahan untuk perlengkapan alat). Seluruh proses masukan dilengkapi penanganan kesalahan (*defensive programming*) dengan toleransi maksimal 3 kali kesalahan.

**2. Tampilkan Semua Barang**  
   Digunakan untuk mencetak seluruh data inventaris yang tersimpan di dalam `ArrayList<Makeup>`. Fitur ini langsung memuat dua data bawaan (*dummy data*) sejak awal program berjalan dan memanfaatkan mekanisme polimorfisme (*dynamic binding*) untuk memanggil method `tampilkanData()` sesuai tipe objek aslinya.

**3. Ubah Data Barang**  
   Digunakan untuk memperbarui informasi barang berdasarkan ID yang dicari. Fitur ini menerapkan method overloading `tampilkanData(header)` untuk menampilkan rincian data saat ini sebelum diubah. Pengguna dapat memperbarui nama dan merk (dapat dikosongkan jika tidak ingin diubah), serta menentukan konfirmasi `(y/n)` untuk perubahan harga, stok, maupun atribut spesifik kategori (shade atau material).

**4. Hapus Data Barang**  
   Digunakan untuk menghapus data barang dari inventaris `ArrayList<Makeup>` berdasarkan ID barang. Sistem menampilkan nama barang yang akan dihapus dan meminta konfirmasi persetujuan keamanan `(y/n)` agar barang tidak terhapus tanpa sengaja.

**5. Keluar**  
   Digunakan untuk menghentikan perulangan menu utama pada controller dan menutup program dengan menampilkan pesan penutup.

---

# 5. Alur Program

## 5.1 Alur Sistem

Secara umum, sistem dimulai dari kelas `Main` yang hanya memanggil satu baris perintah untuk menginisialisasi controller dan view. Controller langsung memuat dua data bawaan (*dummy data*) ke dalam `ArrayList<Makeup>`. Pengguna memilih fitur yang tersedia melalui menu utama. Setiap fitur bekerja melalui menu utama dan setelah proses selesai pengguna akan kembali ke menu. Program akan berhenti saat pengguna memasukkan angka `5`.

```text
                          ┌──────────────┐
                          │    MULAI     │
                          └──────┬───────┘
                                 │
                                 ▼
                     Inisialisasi Controller
                                 │
                                 ▼
                       Muat Dummy Data Awal
                                 │
            ┌────────────────────┴────────────────────┐
            │                                         │
            ▼                                         │
┌────────────────────┐                                │
│   Tampilkan Menu   │◄───────────────────────────┐   │
└─────────┬──────────┘                            │   │
          │                                       │   │
          ▼                                       │   │
┌────────────────────┐                            │   │
│   Input Pilihan    │                            │   │
│       Menu         │                            │   │
└─────────┬──────────┘                            │   │
          │                                       │   │
          ▼                                       │   │
  ┌───────────────┐                               │   │
  │ Pilihan Menu? │                               │   │
  └───────┬───────┘                               │   │
          │                                       │   │
  ┌───────┼─────────────┬─────────────┬───────────┤   │
  │       │             │             │           │   │
  ▼       ▼             ▼             ▼           ▼   │
 [1]     [2]           [3]           [4]         [5]  │
Tambah  Lihat         Ubah          Hapus       Keluar│
  │       │             │             │           │   │
  ▼       ▼             ▼             ▼           ▼   │
Proses  Proses        Proses        Proses     Selesai│
  │       │             │             │               │
  └───────┴──────┬──────┴─────────────┘               │
                 │                                    │
                 ▼                                    │
          Kembali ke Menu ────────────────────────────┘
```

## 5.2 Alur Tambah Barang
Fitur Tambah Data Barang digunakan untuk membuat entitas data barang baru, baik berupa produk kosmetik maupun perlengkapan alat. 
Urutan prosesnya adalah:
```text
Pilih Menu Tambah Data Barang
                       │
                       ▼
            Pilih Kategori Barang?
                 ┌─────┴─────┐
                 │           │
                 ▼           ▼
           Produk Makeup  Perlengkapan Makeup
                 │           │
                 └─────┬─────┘
                       │
                       ▼
                Input ID Barang
          (Cek Kosong & Cek Duplikat)
                       │
                       ▼
              Input Nama & Merk
                       │
                       ▼
           Input Harga (Harus > 0)
                       │
                       ▼
           Input Stok (Bulat & > 0)
                       │
                       ▼
            Kategori yang Dipilih?
                 ┌─────┴─────┐
                 │           │
                 ▼           ▼
           Produk Makeup  Perlengkapan Makeup
                 │           │
                 ▼           ▼
            Input Shade  Input Material
                 │           │
                 ▼           ▼
            Buat Object  Buat Object
           ProdukMakeup  PerlengkapanMakeup
                 │           │
                 └─────┬─────┘
                       │
                       ▼
              Simpan ke ArrayList
                       │
                       ▼
            Tampilkan Pesan Berhasil
                       │
                       ▼
                Kembali ke Menu
```
Setiap input teks dan angka memiliki validasi ketat dengan batas maksimal 3 kali percobaan salah:        
* ID tidak boleh kosong dan tidak boleh sama dengan ID yang sudah ada di list.       
* Harga harus berupa angka riil lebih dari 0.      
* Stok harus berupa bilangan bulat minimal 1 (divalidasi oleh setter agar tidak boleh 0 atau negatif).            

Pilihan kategori menentukan pembuatan objek subclass:     
* Produk Makeup menghasilkan objek ProdukMakeup dan meminta masukan data varian/shade.         
* Perlengkapan Makeup menghasilkan objek PerlengkapanMakeup dan meminta masukan data material bahan.

**Bukti output proses tambah produk makeup**                           
<img width="284" height="116" alt="image" src="https://github.com/user-attachments/assets/99ea8155-01cc-46f3-bac2-0132bba5ea74" />                       
Proses penambahan produk rias kosmetik (ProdukMakeup) hingga data berhasil disimpan.

**Bukti output proses tambah perlengkapan makeup**                             
<img width="313" height="131" alt="image" src="https://github.com/user-attachments/assets/0116c91d-e23b-4dcc-8b12-15735a472a22" />                                   
Proses penambahan perlengkapan alat rias (PerlengkapanMakeup) hingga data berhasil disimpan.      

## 5.3 Tampilkan Semua Barang          
Fitur Tampilkan Semua Barang digunakan untuk mencetak seluruh barang inventaris yang tersimpan di dalam `ArrayList<Makeup>`.                            
<img width="196" height="371" alt="image" src="https://github.com/user-attachments/assets/92dcd2c6-e662-4876-987e-71e547371122" />                                     
Tampilan inventaris pada fitur Tampilkan Semua Barang, termasuk data dummy yang tersedia sejak awal.         
Data dummy dimuat saat controller pertama kali diinisialisasi sehingga saat fitur ini dipilih pertama kali, data langsung tersaji rapi.       
```text
Pilih Menu Tampilkan Semua Barang
              ↓
Controller mengakses ArrayList
              ↓
View menerima koleksi ArrayList<Makeup>
              ↓
Perulangan membaca tiap objek
              ↓
Method polimorfik tampilkanData()
              ↓
Rincian barang tampil pada terminal
```       
Kategori barang ditampilkan melalui pemanggilan abstract method `getKategori()` yang dioverride oleh tiap subclass, sedangkan spesifikasi unik (shade/material) dicetak melalui method overriding `tampilkanData()`.          

## 5.4 Alur Ubah Data Barang         
Fitur Ubah Data Barang digunakan untuk memperbarui rincian barang berdasarkan ID yang dimasukkan pengguna.                        
<img width="247" height="272" alt="image" src="https://github.com/user-attachments/assets/9f05a73c-9c18-4a9f-afbe-bf848aa3598f" />                                     
Proses pembaruan data barang berdasarkan ID dengan pemanggilan method overloading saat menampilkan data lama.            
```text
Pilih Ubah Data Barang
                         │
                         ▼
        ┌► Input ID Barang yang Dicari ◄────────┐
        │                │                      │
        │                ▼                      │
        │       Cari Barang via ID              │
        │                │                      │
        │                ▼                      │
        │        Data ditemukan?                │
        │         ┌─────┴─────┐                 │
        │       Tidak        Ya                 │
        │         │           │                 │
        │         ▼           ▼                 │
        └── Data Tidak Ada  Tampilkan Detail    │
                           Data Saat Ini via    │
                          Method Overloading    │
                                  │             │
                                  ▼             │
                           Input Data Baru      │
                        (Kosongkan jika skip)   │
                                  │             │
                                  ▼             │
                            Update Nilai        │
                         via Method Setter      │
                                  │             │
                                  ▼             │
                       Pembaruan Data Berhasil  │
                                  │             │
                                  ▼             │
                         Kembali ke Menu ───────┘
```

Sebelum mengubah data, sistem menampilkan detail barang saat ini menggunakan pemanggilan method overloading `item.tampilkanData("\nData saat ini:");`. Pengguna dapat memilih atribut mana saja yang ingin diubah (nama, merk, harga, stok, atau atribut subclass). Masukan dapat dikosongkan jika pengguna tidak ingin mengubah nilai atribut tersebut.        

## 5.5 Alur Hapus Data Barang         
Fitur Hapus Data Barang digunakan untuk menghapus data inventaris dari `ArrayList<Makeup>` berdasarkan ID.                          
<img width="247" height="78" alt="image" src="https://github.com/user-attachments/assets/ca4e8162-4117-4706-a49f-9f1eb67187a4" />                                          
Proses penghapusan data barang berdasarkan ID dengan konfirmasi pengguna.         
Sebelum data dihapus, sistem menampilkan nama barang dan meminta konfirmasi persetujuan `(y/n)` agar barang tidak terhapus tanpa sengaja.         
```text
Pilih Hapus Data Barang
                         │
                         ▼
        ┌► Input ID Barang yang Dicari
        │                │
        │                ▼
        │       Cari Barang via ID
        │                │
        │                ▼
        │        Data ditemukan?
        │         ┌─────┴─────┐
        │       Tidak        Ya
        │         │           │
        │         ▼           ▼
        └── Data Tidak Ada  Tampilkan Konfirmasi
                           Penghapusan Barang
                                 │
                                 ▼
                          Konfirmasi (y/n)?
                            ┌─────┴─────┐
                            │           │
                            ▼           ▼
                           'y'         'n'
                            │           │
                            ▼           ▼
                        Hapus dari    Batal
                        ArrayList       │
                            │           │
                            └─────┬─────┘
                                  │
                                  ▼
                           Kembali ke Menu
```      

## 5.6 Alur Keluar Program         
Ketika pengguna memasukkan opsi 5 pada menu utama, sistem mengubah status perulangan menjadi false, menampilkan pesan penutup program, dan mengakhiri sesi terminal.            
<img width="266" height="44" alt="image" src="https://github.com/user-attachments/assets/34d0fea4-685e-429a-b71e-aca3eb75e478" />                                       
Tampilan saat program selesai dan keluar dari sistem.               

---

# 6. Penerapan Ketentuan OOP  
## 6.1 Encapsulation

Encapsulation diwujudkan dengan menyembunyikan variabel menggunakan modifier `private` dan membukanya secara aman lewat perantara method getter serta setter.

### 1. Deklarasi Atribut Private
Pada superclass `Makeup`, seluruh atribut inti dideklarasikan menggunakan access modifier `private`:
```java
private final String id;
private String nama;
private String merk;
private double harga;
private int stok;
```     

Hal yang sama diterapkan pada subclass:      
* Atribut shade pada ProdukMakeup dideklarasikan private String shade;.             
* Atribut material pada PerlengkapanMakeup dideklarasikan private String material;.

Deklarasi private menjamin data tidak dapat dibaca atau diubah secara langsung dari luar class. Akses data hanya dapat dilakukan melalui method resmi yang disediakan.      

### 2. Method Getter dan Immutability ID         
Setiap atribut dilengkapi dengan method getter untuk mengambil nilainya secara aman. Khusus untuk atribut `id`:        
```java
public String getId() {
    return id;
}
```

Atribut `id` dideklarasikan menggunakan kata kunci final dan hanya disediakan method `getId()`. Method `setId()` sengaja ditiadakan karena ID berperan sebagai tanda pengenal permanen yang tidak boleh dimodifikasi setelah objek dibuat, sekaligus mencegah adanya kode mati (dead code).        

### 3. Method Setter dan Validasi Data          
Atribut `nama`, `merk`, `harga`, `stok`, `shade`, dan `material` memiliki method setter yang digunakan oleh controller untuk memanipulasi nilai. Khusus pada method `setStok()`, diterapkan sistem validasi defensif:         
```java
public void setStok(int stok) {
    if (stok <= 0) {
        System.out.println("[Peringatan] Stok minimal 1 (tidak boleh 0 atau negatif)! Nilai diatur ke 1.");
        this.stok = 1;
    } else {
        this.stok = stok;
    }
}
```                           
Validasi ini memastikan data stok yang tersimpan ke dalam objek tidak bernilai 0 atau negatif.

## 6.2 Inheritance             
Inheritance diterapkan dengan menjadikan class `Makeup` sebagai superclass abstrak yang mewariskan atribut serta perilaku umum kepada dua subclass yang lebih spesifik.                 

### 1. Deklarasi Subclass Menggunakan Keyword `extends`                              
Subclass `ProdukMakeup` mewarisi superclass `Makeup`:                           
```java
public class ProdukMakeup extends Makeup {
    private String shade;
    // ...
}
```
Subclass `PerlengkapanMakeup` juga mewarisi superclass `Makeup`:                     
```java
public class PerlengkapanMakeup extends Makeup {
    private String material;
    // ...
}
```

### 2. Penggunaan `super()` pada Constructor Subclass                        
Constructor pada masing-masing subclass memanggil constructor superclass menggunakan kata kunci `super(...)` untuk menginisialisasi atribut dasar (`id`, `nama`, `merk`, `harga`, `stok`):                      
* Pada `ProdukMakeup`:
```java
public ProdukMakeup(String id, String nama, String merk, double harga, int stok, String shade) {
    super(id, nama, merk, harga, stok);
    this.shade = shade;
}
```
* Pada `PerlengkapanMakeup`:
```java
public PerlengkapanMakeup(String id, String nama, String merk, double harga, int stok, String material) {
    super(id, nama, merk, harga, stok);
    this.material = material;
}
```
Dengan mekanisme inheritance ini, atribut dan method umum tidak perlu ditulis ulang pada masing-masing subclass, sehingga kode program lebih modular, terstruktur, dan efisien (code reusability).        

---

# 7. Penerapan Abstraction dan Polymorphism                    
## 7.1 Abstraction              
Abstraction diterapkan untuk menyembunyikan detail teknis yang kompleks dan hanya mengekspos fungsi-fungsi esensial melalui kontrak antarmuka yang terstandarisasi.

### 1. Abstract Class `Makeup`
Superclass `Makeup` dideklarasikan menggunakan kata kunci `abstract`:
```java
public abstract class Makeup implements KelolaBarang {
    // ...
}
```

Penerapan abstract class ini memiliki tujuan:                   
* Mencegah instansiasi objek umum secara langsung (`new Makeup(...)` tidak dapat dilakukan).                
* Menjadikan kelas `Makeup` sebagai kerangka dasar (blueprint) yang mewariskan atribut umum ke kelas turunannya (`ProdukMakeup` dan `PerlengkapanMakeup`).

### 2. Abstract Method `getKategori()`                 
Di dalam class `Makeup`, terdapat method abstrak tanpa bodi:                  
```java
public abstract String getKategori();
```
Method ini mewajibkan setiap kelas turunan untuk mengimplementasikan kategorinya sendiri:                               
* Subclass `ProdukMakeup` mengimplementasikan method tersebut untuk mengembalikan label kategori produk:
```java
@Override
public String getKategori() {
    return "Produk Makeup";
}
```
* Subclass `PerlengkapanMakeup` mengimplementasikan method tersebut untuk mengembalikan label kategori perlengkapan:
```java
@Override
public String getKategori() {
    return "Perlengkapan Makeup";
}
```

## 7.2 Polymorphism                
Polymorphism diterapkan melalui dua bentuk utama, yaitu Method Overriding (Dynamic Polymorphism) dan Method Overloading (Static Polymorphism), serta pemanfaatan Koleksi Polimorfik.                             
### 1. Method Overriding (Dynamic Polymorphism)                   
Method `tampilkanData()` yang didefinisikan pada superclass `Makeup` di-override oleh masing-masing subclass untuk mencetak atribut spesifik:                    

* Pada Subclass `ProdukMakeup`:
Method memanggil `super.tampilkanData()` terlebih dahulu untuk mencetak atribut dasar, lalu menambahkan informasi khusus berupa Shade / Varian:
```java
@Override
public void tampilkanData() {
    super.tampilkanData();
    System.out.println("Shade / Varian: " + shade);
}
```
* Pada Subclass `PerlengkapanMakeup`:
Method memanggil `super.tampilkanData()`, lalu menambahkan informasi khusus berupa Material / Bahan:
```java
@Override
public void tampilkanData() {
    super.tampilkanData();
    System.out.println("Material / Bahan: " + material);
}
```

### 2. Method Overloading (Static Polymorphism)                     
Method overloading diterapkan pada superclass `Makeup` dengan menyediakan dua method bernama sama `(tampilkanData)` tetapi memiliki parameter yang berbeda (signature):                            
* Versi Tanpa Parameter:
```java
@Override
public void tampilkanData() {
    System.out.println("ID Barang: " + id);
    System.out.println("Nama: " + nama);
    System.out.println("Merk: " + merk);
    System.out.println("Kategori: " + getKategori());
    System.out.printf("Harga: Rp%,.2f\n", harga);
    System.out.println("Stok: " + stok + " pcs");
}
```
* Versi Dengan Parameter `String header`:
```java
public void tampilkanData(String header) {
    System.out.println(header);
    tampilkanData();
}
```
Penerapan Nyata: Method overloading kedua dipanggil pada `MakeupController` saat fitur Ubah Data Barang dijalankan untuk menampilkan judul keterangan sebelum rincian data lama dicetak ke terminal:                     
```java
item.tampilkanData("\nData saat ini:");
```

### 3. Koleksi Polimorfik                     
Polymorphism juga tampak pada pengelolaan struktur data di `MakeupController`. Seluruh data barang disimpan dalam satu wadah koleksi seragam:                           
```java
private ArrayList<Makeup> daftarBarang;
```

Meskipun tipe wadahnya adalah tipe referensi superclass `Makeup`, koleksi ini dapat menampung objek turunan `ProdukMakeup` maupun `PerlengkapanMakeup`. Saat perulangan cetak data dijalankan:                                 
```java
for (int i = 0; i < listBarang.size(); i++) {
    System.out.println("Data ke-" + (i + 1));
    listBarang.get(i).tampilkanData();
    System.out.println("--------------------------------");
}
```
Java secara dinamis (runtime polymorphism) mengenali tipe objek asli dari setiap indeks dan memanggil implementasi method `tampilkanData()` yang tepat (apakah mencetak shade atau mencetak material).                                   

---

# 8. Penerapan Nilai Tambah

Nilai tambah yang diterapkan pada program ini adalah penggunaan Interface untuk melengkapi penerapan konsep Object-Oriented Programming (OOP) tingkat lanjut.

## Penerapan Interface `KelolaBarang`

Pada package `model`, dibuat sebuah interface bernama `KelolaBarang`:

```java
package model;

public interface KelolaBarang {
    void tampilkanData();
}
```

Letak dan Mekanisme Penerapan:                           
### 1. Implementasi pada Superclass                   
Interface `KelolaBarang` diimplementasikan secara langsung oleh superclass abstrak `Makeup`:
```java
public abstract class Makeup implements KelolaBarang {
    // ...
}
```

### 2. Sebagai Kontrak Baku (Contractual Behavior)
Interface ini bertindak sebagai kontrak yang mewajibkan class `Makeup` beserta seluruh kelas turunannya (`ProdukMakeup` dan `PerlengkapanMakeup`) untuk memiliki method pencetakan data (`tampilkanData()`).
Dengan adanya interface `KelolaBarang`, struktur kode menjadi lebih modular, terstandarisasi, dan memenuhi prinsip abstraksi murni dalam PBO.                                                 
