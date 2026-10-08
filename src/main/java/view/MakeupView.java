package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

import java.util.ArrayList;
import java.util.Scanner;
import model.Makeup;

/**
 *
 * @author gedascc
 */
public class MakeupView {
    private Scanner scanner = new Scanner(System.in);

    public void tampilkanMenuUtama() {
        System.out.println("\n=============================================");
        System.out.println(" SISTEM MANAJEMEN PERLENGKAPAN & PRODUK MAKEUP");
        System.out.println("=============================================");
        System.out.println("1. Tambah Data Barang");
        System.out.println("2. Tampilkan Semua Barang");
        System.out.println("3. Ubah Data Barang");
        System.out.println("4. Hapus Data Barang");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu (1-5): ");
    }

    public String inputString(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    public void tampilkanDaftarBarang(ArrayList<Makeup> listBarang) {
        System.out.println("\n====== Daftar Barang Makeup ======");
        if (listBarang.isEmpty()) {
            System.out.println("Belum ada data barang tersimpan!!");
            return;
        }

        for (int i = 0; i < listBarang.size(); i++) {
            System.out.println("Data ke-" + (i + 1));
            listBarang.get(i).tampilkanData();
            System.out.println("--------------------------------");
        }
    }
}