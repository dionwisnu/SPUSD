/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TambahSaldo;

import Koneksi.Koneksi;
import Model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dion Wisnu
 */
public class TambahSaldoControl {
    private Connection conn;

    public TambahSaldoControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static TambahSaldoControl getKoneksiTambahSaldo() throws SQLException {
        TambahSaldoControl kon = new TambahSaldoControl(Koneksi.getDBConnection());
        return kon;
    }
    
    public boolean cekDataMember(Member member) throws SQLException {
        Statement stmt = conn.createStatement();
        String id_member = member.getId_member();
        String sql = "select nama_member from member where id_member = '" + id_member + "'";
        boolean status = false;
        ResultSet rset = stmt.executeQuery(sql);
        while (rset.next()) {
            if (rset.getString("nama_member").equals("")) {
                status = false;
            } else {
                status = true;
            }
        }
        conn.commit();
        conn.close();
        System.out.println("status : " + status);
        return status;
    }
    
    public void tambahSaldo(Member member)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "update member set saldo = ? where id_member = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getSaldo());
            pstmt.setString(2, member.getId_member());                     
            pstmt.executeUpdate();
            
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.commit();
        conn.close();
    }
}
