package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

/**
 *
 * @author gedascc
 */
public class PerlengkapanMakeup extends Makeup {
    private String material;

    public PerlengkapanMakeup(String id, String nama, String merk, double harga, int stok, String material) {
        super(id, nama, merk, harga, stok);
        this.material = material;
    }

    @Override
    public String getKategori() {
        return "Perlengkapan Makeup";
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("Material / Bahan: " + material);
    }
}