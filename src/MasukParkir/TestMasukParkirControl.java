/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasukParkir;

import Model.Kunjungan;
import Model.Member;
import Model.Petugas;
import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dion Wisnu
 */
public class TestMasukParkirControl {

    public static void main(String[] args) {
        Member mb = new Member();
        Kunjungan kj = new Kunjungan();
        Petugas pt = new Petugas();
        kj.setNo_parkir("1705001");
        kj.setPlat_nomor("AA4055JK");
        pt.setId_petugas("K.1024");
        kj.setId_petugas(pt);
        mb.setId_member("125314000");
        kj.setId_member(mb);
//        System.out.println(kj.getId_petugas().getId_petugas());
        try {
            if (MasukParkirControl.getKoneksiMasukParkir().cekDataMember(mb)){
                MasukParkirControl.getKoneksiMasukParkir().tambahDataKunjunganMasuk(kj);
                System.out.println("Data Parkir bertambah");
            } else {
                System.out.println("Data Parkir gagal bertambah");
            }

        } catch (SQLException ex) {
            System.out.println("Gagal = " + ex);
        }
    }
}
