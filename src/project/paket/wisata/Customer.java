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
public class Customer implements Button {

    private int id;
    private String namaCus;
    private String telp;
    private String email;
    private String alamat;
    private String pw;

    DefaultTableModel m = new DefaultTableModel();

    public Customer() {
    }

    public Customer(int id, String namaCus, String telp, String email, String alamat, String pw) {
        this.id = id;
        this.namaCus = namaCus;
        this.telp = telp;
        this.email = email;
        this.alamat = alamat;
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaCus() {
        return namaCus;
    }

    public void setNamaCus(String namaCus) {
        this.namaCus = namaCus;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public void tambah() {
        try {
            String sql = "INSERT INTO customer VALUES('" + getId() + "', "
                    + "'" + getNamaCus() + "', "
                    + "'" + getEmail() + "', "
                    + "'" + getTelp() + "', "
                    + "'" + getAlamat() + "');";
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
            String sql = "SELECT * FROM customer";
            Statement st = (Statement) KoneksiDB.ConfigDB().createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                Object[] ob = new Object[5];
                ob[0] = res.getString("id");
                ob[1] = res.getString("namaCus");
                ob[2] = res.getString("email");
                ob[3] = res.getString("telp");
                ob[4] = res.getString("alamat");

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
            String sql = "UPDATE customer SET namaCus='" + getNamaCus() + "', "
                    + "email='" + getEmail() + "', "
                    + "telp='" + getTelp() + "', "
                    + "alamat='" + getAlamat() + "'"
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
            String sql = "DELETE FROM customer WHERE id='" + getId() + "'";
            java.sql.Connection conn = KoneksiDB.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
        } catch (HandlerException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
