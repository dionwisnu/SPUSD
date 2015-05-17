/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeluarParkir;

import Koneksi.Koneksi;
import Model.Kunjungan;
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
public class KeluarParkirControl {

    private Connection conn;

    public KeluarParkirControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static KeluarParkirControl getKoneksiKeluarParkir() throws SQLException {
        KeluarParkirControl kon = new KeluarParkirControl(Koneksi.getDBConnection());
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

    public int cekMemberMasuk(Kunjungan member) throws SQLException {
        Statement stmt = conn.createStatement();
        String id_member = member.getId_member().getId_member();
        int status = 0;
        String sql = "select status from kunjungan where tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND id_member='" + id_member + "'";
        ResultSet rset = stmt.executeQuery(sql);
        //status masuk nilainya false
        //status keluar nilainya true
        while (rset.next()) {
            if (rset.getString("status")=="masuk") {
                status = 1;
            } else if (rset.getString("status")=="keluar") {
                status = 0;
            } else {
            }
        }
        conn.commit();
        conn.close();
        System.out.println("status : " + status);
        return status;
    }

    public void tambahDataKunjunganKeluar(Kunjungan kunjungan) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into kunjungan (no_parkir, plat_nomor, tanggal_parkir, jam_masuk, id_petugas, id_member, status)"
                    + "VALUES (?, ?, TO_CHAR(SYSDATE, 'fmDD MON YYYY'), TO_CHAR(SYSDATE, 'fmHH24:MI:SS'), ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kunjungan.getNo_parkir());
            pstmt.setString(2, kunjungan.getPlat_nomor());
            pstmt.setString(3, kunjungan.getId_petugas().getId_petugas());
            pstmt.setString(4, kunjungan.getId_member().getId_member());
            pstmt.setString(5, "masuk");
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
}
