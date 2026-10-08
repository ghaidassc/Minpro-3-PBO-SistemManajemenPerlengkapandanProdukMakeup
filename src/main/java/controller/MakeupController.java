package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

import java.util.ArrayList;
import model.Makeup;
import model.ProdukMakeup;
import model.PerlengkapanMakeup;
import view.MakeupView;

/**
 *
 * @author gedascc
 */
public class MakeupController {
    private ArrayList<Makeup> daftarBarang;
    private MakeupView view;

    public MakeupController(MakeupView view) {
        this.view = view;
        this.daftarBarang = new ArrayList<>();
        isiDummyData();
    }

    private void isiDummyData() {
        daftarBarang.add(new ProdukMakeup("MK01", "Superstay Matte Ink", "Maybelline", 125000, 2, "115 Founder"));
        daftarBarang.add(new PerlengkapanMakeup("AL01", "Miracle Complexion Sponge", "Real Techniques", 89000, 1, "Hydrophilic Foam"));
    }

    public void mulaiProgram() {
        boolean berjalan = true;
        while (berjalan) {
            view.tampilkanMenuUtama();
            String pilihan = view.inputString("");

            switch (pilihan) {
                case "1" -> tambahBarang();
                case "2" -> tampilkanSemua();
                case "3" -> ubahBarang();
                case "4" -> hapusBarang();
                case "5" -> {
                    berjalan = false;
                    view.tampilkanPesan("\nTerima kasih telah menggunakan program ini:)");
                }
                default -> view.tampilkanPesan("Pilihan tidak valid, masukkan angka 1 sampai 5.");
            }
        }
    }

    public void tambahBarang() {
        view.tampilkanPesan("\n===== Tambah Barang Baru =====");

        String tipe = "";
        for (int i = 1; i <= 3; i++) {
            tipe = view.inputString("Pilih Kategori (1. Produk Makeup / 2. Perlengkapan Makeup): ");
            if (tipe.equals("1") || tipe.equals("2")) {
                break;
            }
            view.tampilkanPesan("Pilihan tidak valid! (Percobaan " + i + "/3)");
            if (i == 3) {
                view.tampilkanPesan("Gagal memilih kategori 3 kali. Kembali ke menu utama.");
                return;
            }
        }

        String id = null;
        for (int i = 1; i <= 3; i++) {
            String input = view.inputString("Masukkan ID Barang: ");
            if (!input.isEmpty()) {
                if (cariIndexById(input) != -1) {
                    view.tampilkanPesan("ID sudah terdaftar, masukkan ID lain!!");
                    return;
                }
                id = input;
                break;
            }
            view.tampilkanPesan("ID tidak boleh kosong!! (Percobaan " + i + "/3)");
        }
        if (id == null) {
            view.tampilkanPesan("Gagal mengisi ID 3 kali. Kembali ke menu utama.");
            return;
        }

        String nama = inputTeksWajib("Nama Barang");
        if (nama == null) return;

        String merk = inputTeksWajib("Merk Barang");
        if (merk == null) return;

        double harga = -1;
        for (int i = 1; i <= 3; i++) {
            String input = view.inputString("Masukkan Harga (Rp): ");
            if (input.isEmpty()) {
                view.tampilkanPesan("Harga tidak boleh kosong!! (Percobaan " + i + "/3)");
                continue;
            }
            try {
                double tempHarga = Double.parseDouble(input);
                if (tempHarga <= 0) {
                    view.tampilkanPesan("Harga harus lebih dari 0!! (Percobaan " + i + "/3)");
                    continue;
                }
                harga = tempHarga;
                break;
            } catch (NumberFormatException e) {
                view.tampilkanPesan("Format harga harus angka!! (Percobaan " + i + "/3)");
            }
        }
        if (harga == -1) {
            view.tampilkanPesan("Gagal mengisi Harga 3 kali. Kembali ke menu utama.");
            return;
        }

        int stok = -1;
        for (int i = 1; i <= 3; i++) {
            String input = view.inputString("Masukkan Stok (pcs): ");
            if (input.isEmpty()) {
                view.tampilkanPesan("Stok tidak boleh kosong! (Percobaan " + i + "/3)");
                continue;
            }
            try {
                int tempStok = Integer.parseInt(input);
                if (tempStok <= 0) {
                    view.tampilkanPesan("Stok minimal 1 (tidak boleh 0 atau negatif)!! (Percobaan " + i + "/3)");
                    continue;
                }
                stok = tempStok;
                break;
            } catch (NumberFormatException e) {
                view.tampilkanPesan("Format stok harus angka bulat!! (Percobaan " + i + "/3)");
            }
        }
        if (stok == -1) {
            view.tampilkanPesan("Gagal mengisi Stok 3 kali. Kembali ke menu utama.");
            return;
        }

        if (tipe.equals("1")) {
            String shade = inputTeksWajib("Shade / Varian");
            if (shade == null) return;
            daftarBarang.add(new ProdukMakeup(id, nama, merk, harga, stok, shade));
        } else {
            String material = inputTeksWajib("Material / Bahan");
            if (material == null) return;
            daftarBarang.add(new PerlengkapanMakeup(id, nama, merk, harga, stok, material));
        }

        view.tampilkanPesan("Barang berhasil ditambahkan!!");
    }

    public void tampilkanSemua() {
        view.tampilkanDaftarBarang(daftarBarang);
    }

    public void ubahBarang() {
        view.tampilkanPesan("\n===== Ubah Data Barang =====");
        if (daftarBarang.isEmpty()) {
            view.tampilkanPesan("Data kosong, tidak ada barang yang bisa diubah!!");
            return;
        }

        String id = view.inputString("Masukkan ID Barang yang ingin diubah: ");
        int index = cariIndexById(id);

        if (index == -1) {
            view.tampilkanPesan("Barang dengan ID '" + id + "' tidak ditemukan!!");
            return;
        }

        Makeup item = daftarBarang.get(index);
        item.tampilkanData("\nData saat ini:");
        view.tampilkanPesan("--------------------------------------");

        String namaBaru = view.inputString("Nama baru (kosongkan jika tidak diubah): ");
        if (!namaBaru.isEmpty()) {
            item.setNama(namaBaru);
        }

        String merkBaru = view.inputString("Merk baru (kosongkan jika tidak diubah): ");
        if (!merkBaru.isEmpty()) {
            item.setMerk(merkBaru);
        }

        String ubahHarga = view.inputString("Ingin mengubah harga? (y/n): ");
        if (ubahHarga.equalsIgnoreCase("y")) {
            try {
                double hargaBaru = Double.parseDouble(view.inputString("Masukkan harga baru (Rp): "));
                if (hargaBaru > 0) {
                    item.setHarga(hargaBaru);
                } else {
                    view.tampilkanPesan("Harga harus lebih dari 0. Perubahan harga dilewati.");
                }
            } catch (NumberFormatException e) {
                view.tampilkanPesan("Harga tidak valid, perubahan harga dilewati.");
            }
        }

        String ubahStok = view.inputString("Ingin mengubah stok? (y/n): ");
        if (ubahStok.equalsIgnoreCase("y")) {
            try {
                int stokBaru = Integer.parseInt(view.inputString("Masukkan stok baru (pcs): "));
                if (stokBaru > 0) {
                    item.setStok(stokBaru);
                } else {
                    view.tampilkanPesan("Stok minimal 1. Perubahan stok dilewati.");
                }
            } catch (NumberFormatException e) {
                view.tampilkanPesan("Stok tidak valid, perubahan stok dilewati.");
            }
        }

        if (item instanceof ProdukMakeup) {
            String shadeBaru = view.inputString("Shade baru (kosongkan jika tidak diubah): ");
            if (!shadeBaru.isEmpty()) {
                ((ProdukMakeup) item).setShade(shadeBaru);
            }
        } else if (item instanceof PerlengkapanMakeup) {
            String materialBaru = view.inputString("Material baru (kosongkan jika tidak diubah): ");
            if (!materialBaru.isEmpty()) {
                ((PerlengkapanMakeup) item).setMaterial(materialBaru);
            }
        }

        view.tampilkanPesan("Data barang berhasil diperbarui!!");
    }

    public void hapusBarang() {
        view.tampilkanPesan("\n===== Hapus Data Barang =====");
        if (daftarBarang.isEmpty()) {
            view.tampilkanPesan("Data kosong, tidak ada barang yang bisa dihapus!!");
            return;
        }

        String id = view.inputString("Masukkan ID Barang yang ingin dihapus: ");
        int index = cariIndexById(id);

        if (index == -1) {
            view.tampilkanPesan("Barang dengan ID '" + id + "' tidak ditemukan!!");
            return;
        }

        String konfirmasi = view.inputString("Yakin hapus '" + daftarBarang.get(index).getNama() + "'? (y/n): ");
        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarBarang.remove(index);
            view.tampilkanPesan("Barang berhasil dihapus!!");
        } else {
            view.tampilkanPesan("Penghapusan dibatalkan.");
        }
    }

    private String inputTeksWajib(String namaField) {
        for (int i = 1; i <= 3; i++) {
            String input = view.inputString("Masukkan " + namaField + ": ");
            if (!input.isEmpty()) {
                return input;
            }
            view.tampilkanPesan(namaField + " tidak boleh kosong!! (Percobaan " + i + "/3)");
        }
        view.tampilkanPesan("Gagal mengisi " + namaField + " 3 kali. Kembali ke menu utama.");
        return null;
    }

    private int cariIndexById(String id) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            if (daftarBarang.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}