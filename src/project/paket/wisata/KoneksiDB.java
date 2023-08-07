package project.paket.wisata;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
import java.sql.*;
public class KoneksiDB {
    public static Connection con = null;
    public static Statement stm = null;
    private static Connection MySQLConfig;
    public static void main(String[] args) throws SQLDataException {
        System.out.println(ConfigDB());
    }
    public static Connection ConfigDB() throws SQLDataException{
        try{
            String url = "jdbc:mysql://localhost/paket_wisata";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            MySQLConfig = DriverManager.getConnection(url, user, pass);
            System.out.println("koneksi berhasil");
        }catch(Exception e){
            System.out.println("koneksi gagal" + e.getMessage());
        }
        return MySQLConfig;
    }
}
