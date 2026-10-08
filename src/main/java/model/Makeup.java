package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

/**
 *
 * @author gedascc
 */
public abstract class Makeup implements KelolaBarang {
    private final String id;
    private String nama;
    private String merk;
    private double harga;
    private int stok;

    public Makeup(String id, String nama, String merk, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.merk = merk;
        this.harga = harga;
        setStok(stok);
    }

    public abstract String getKategori();

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok <= 0) {
            System.out.println("[Peringatan] Stok minimal 1 (tidak boleh 0 atau negatif)! Nilai diatur ke 1.");
            this.stok = 1;
        } else {
            this.stok = stok;
        }
    }

    @Override
    public void tampilkanData() {
        System.out.println("ID Barang: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Merk: " + merk);
        System.out.println("Kategori: " + getKategori());
        System.out.printf("Harga: Rp%,.2f\n", harga);
        System.out.println("Stok: " + stok + " pcs");
    }

    public void tampilkanData(String header) {
        System.out.println(header);
        tampilkanData();
    }
}