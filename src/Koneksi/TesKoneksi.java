/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Koneksi;

import Koneksi.Koneksi;

/**
 *
 * @author Dion Wisnu
 */
public class TesKoneksi {
    public static void main(String[] args) {
        Koneksi dh = new Koneksi();
        dh.getDBConnection();
        dh.closeConnection();
    }
}
