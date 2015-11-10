package com.exsoft.amy.dataatk.domain;

/**
 * Created by amy on 09/11/15.
 */
public class ATK {

    private String id;
    private String namabarang;
    private String merek;
    private String satuan;
    private String jumlah;
    private String harga;

    public String getId() {
        return id;
    }

    public static void setId(String id) {
        this.id = id;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public static void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getMerek() {
        return merek;
    }

    public static void setMerek(String merek) {
        this.merek = merek;
    }

    public String getSatuan() {
        return satuan;
    }

    public static void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public static void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public static void setHarga(String harga) {
        this.harga = harga;
    }
}
