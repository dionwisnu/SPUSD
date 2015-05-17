package Koneksi;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

public class Koneksi {

    public Koneksi() {
    }

    public static Connection getDBConnection() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "hr";
        String password = "hr";

        Connection conn = null;
        try {
            OracleDataSource ds;
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid, password);
            System.out.println("Koneksi Sip no...");
        } catch (SQLException ex) {
            System.out.println("Belum Terkoneksi = " + ex);
        }
        return conn;
    }

    public void closeConnection() {
        Connection conn = null;
        try {
            conn.close();
            System.out.println("Tutup Koneksi Berhasil");
        } catch (SQLException ex) {
            System.out.println("Tutup Koneksi Gagal");
        }
    }
}
