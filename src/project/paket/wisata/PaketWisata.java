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
public class PaketWisata extends Wisata implements Button {

    private int harga;
    private int min;
    private int max;
    private String deskripsi;
    private int id;
    DefaultTableModel m = new DefaultTableModel();
    
    public PaketWisata() {
    }
    
    public PaketWisata(int id){
        this.id=id;
    }
    
    public PaketWisata(int harga, int min, int max, String deskripsi, int id) {
        this.harga = harga;
        this.min = min;
        this.max = max;
        this.deskripsi = deskripsi;
        this.id = id;
    }

    public PaketWisata(String namaWisata, String kota) {
        super(namaWisata, kota);
    }

    public PaketWisata(String namaWisata, String kota, int harga, int min, int max, String deskripsi, int id) {
        super(namaWisata, kota);
        this.harga = harga;
        this.min = min;
        this.max = max;
        this.deskripsi = deskripsi;
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public void tambah() {
        try {
            String sql = "INSERT INTO paketwisata VALUES('" + getId() + "', "
                    + "'" + getNama() + "', "
                    + "'" + getKota() + "', "
                    + "'" + getHarga() + "', "
                    + "'" + getMin() + "', "
                    + "'" + getMax() + "', "
                    + "'" + getDeskripsi() + "');";
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
        
        try {
            String sql = "SELECT * FROM paketwisata";
            Statement st = (Statement) KoneksiDB.ConfigDB().createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                Object[] ob = new Object[7];
                ob[0] = res.getString("id");
                ob[1] = res.getString("namaPaket");
                ob[2] = res.getString("kota");
                ob[3] = res.getString("harga");
                ob[4] = res.getString("min");
                ob[5] = res.getString("max");
                ob[6] = res.getString("des");
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
            String sql = "UPDATE paketwisata SET namaPaket='" + getNama() + "', "
                    + "kota='" + getKota() + "', "
                    + "harga='" + getHarga() + "', "
                    + "min='" + getMin() + "', "
                    + "max='" + getMax() + "', "
                    + "des='" + getDeskripsi() + "'"
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
            String sql = "DELETE FROM paketwisata WHERE id='" + getId() + "'";
            java.sql.Connection conn = KoneksiDB.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
        } catch (HandlerException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
