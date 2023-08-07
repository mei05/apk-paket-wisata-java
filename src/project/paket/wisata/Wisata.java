/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.paket.wisata;


public class Wisata {
    private String nama;
    private String kota;

    public Wisata() {
    }

    public Wisata(String namaPaket, String kota) {
        this.nama = namaPaket;
        this.kota = kota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
