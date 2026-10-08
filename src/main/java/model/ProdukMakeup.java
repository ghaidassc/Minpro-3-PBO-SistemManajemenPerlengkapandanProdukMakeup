package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

/**
 *
 * @author gedascc
 */
public class ProdukMakeup extends Makeup {
    private String shade;

    public ProdukMakeup(String id, String nama, String merk, double harga, int stok, String shade) {
        super(id, nama, merk, harga, stok);
        this.shade = shade;
    }

    @Override
    public String getKategori() {
        return "Produk Makeup";
    }

    public String getShade() {
        return shade;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("Shade / Varian: " + shade);
    }
}