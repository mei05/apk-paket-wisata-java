/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.paket.wisata;

import com.sun.xml.internal.ws.handler.HandlerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class Pemesanan implements Button {

    private int id;
    private Wisata namaPaket;
    private Customer namaCus;
    private PaketWisata harga;
    private String tgl, status;
    private float diskon;
    private int jumlah;

    public Pemesanan() {
    }

    public Pemesanan(Wisata namaPaket, Customer namaCus, PaketWisata harga,
            String tgl, String status, float diskon, int jumlah, int id) {
        this.namaPaket = namaPaket;
        this.namaCus = namaCus;
        this.harga = harga;
        this.tgl = tgl;
        this.status = status;
        this.diskon = diskon;
        this.jumlah = jumlah;
        this.id = id;
    }

    public Wisata getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(Wisata namaPaket) {
        this.namaPaket = namaPaket;
    }

    public Customer getNamaCus() {
        return namaCus;
    }

    public void setNamaCus(Customer namaCus) {
        this.namaCus = namaCus;
    }

    public PaketWisata getHarga() {
        return harga;
    }

    public void setHarga(PaketWisata harga) {
        this.harga = harga;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getDiskon() {
        if (this.jumlah > 7) {
            diskon = (float) 0.05;
        } else if (this.jumlah > 15) {
            diskon = (float) 0.1;
        } else if (this.jumlah > 20) {
            diskon = (float) 0.15;
        } else {
            diskon = 0;
        }
        return diskon;
    }

    public void setDiskon(float diskon) {
        this.diskon = diskon;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int totalBayar(int harga, int jumlah) {
        harga = this.harga.getHarga();
        return (int) ((harga * jumlah) - (harga * jumlah * diskon));
    }

    @Override
    public void tambah() {
        try {
            String sql = "INSERT INTO pemesanan VALUES('" + getId() + "', "
                    + "'" + getNamaPaket() + "', "
                    + "'" + getNamaCus() + "', "
                    + "'" + getTgl() + "', "
                    + "'" + getJumlah() + "', "
                    + "'" + totalBayar(harga.getHarga(), this.jumlah) + "', "
                    + "'" + getStatus() + "');";
            java.sql.Connection conn = KoneksiDB.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (HandlerException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void lihat() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM pemesanan;";
            Statement st = (Statement) KoneksiDB.ConfigDB().createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                Object[] ob = new Object[7];
                ob[0] = res.getString("id_pesan");
                ob[1] = res.getString("id_paket");
                ob[2] = res.getString("id_cus");
                ob[3] = res.getString("jadwal");
                ob[4] = res.getString("jumlah");
                ob[5] = res.getString("total");
                ob[6] = res.getString("status");
                m.addRow(ob);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        try {
            String sql = "UPDATE pemesanan SET id_paket='" + getNamaPaket() + "', "
                    + "id_cus='" + getNamaCus() + "', "
                    + "jadwal='" + getTgl() + "', "
                    + "jumlah='" + getJumlah() + "', "
                    + "total='" + totalBayar(harga.getHarga(), this.jumlah) + "', "
                    + "status='" + getStatus() + "'"
                    + "WHERE id='" + getId() + "';";
            Connection conn = KoneksiDB.ConfigDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Update data berhasil");
        } catch (HandlerException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void hapus() {
        try {
            String sql = "DELETE FROM pemesanan WHERE id='" + getId() + "'";
            java.sql.Connection conn = KoneksiDB.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
        } catch (HandlerException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
