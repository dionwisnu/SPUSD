/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasukParkir;

import Model.Kunjungan;
import Model.Member;
import Model.Petugas;
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
        kj.setPlat_nomor("AB402JK");
        pt.setId_petugas("K.1024");
        kj.setId_petugas(pt);
        mb.setId_member("125314005");
        kj.setId_member(mb);

        try {
            if (MasukParkirControl.getKoneksiMasukParkir().cekDataMember(mb)) {
                if (!MasukParkirControl.getKoneksiMasukParkir().cekStatusKunjunganMember(mb)) {
                    MasukParkirControl.getKoneksiMasukParkir().tambahDataKunjunganMasuk(kj);
                    MasukParkirControl.getKoneksiMasukParkir().tampilDataMemberMasuk(kj);
                    System.out.println("ID = " + kj.getId_member().getId_member());
                    System.out.println("Nama = " + kj.getId_member().getNama_member());
                    System.out.println("Saldo = " + kj.getId_member().getSaldo());
                    System.out.println("No Parkir = " + kj.getNo_parkir());
                    System.out.println("Tanggal Parkir = " + kj.getTanggal_parkir());
                    System.out.println("Jam Masuk = " + kj.getJam_masuk());
                    System.out.println("Plat Nomor = " + kj.getPlat_nomor());
                    System.out.println("Data Parkir bertambah");
                } else {
                    System.out.println("Gagal, status member sedang parkir");
                }
            } else {
                System.out.println("Data Parkir gagal bertambah");
            }
        } catch (SQLException ex) {
            System.out.println("Gagal = " + ex);
        }
//         try {
//             System.out.println(MasukParkirControl.getKoneksiMasukParkir().getNoParkir());
//             System.out.println("Berhasil");
//        } catch (SQLException ex) {
//             System.out.println("Gagal = "+ex.getMessage());
//        }
    }
}
