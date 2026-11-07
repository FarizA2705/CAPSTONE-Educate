/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatistikDao {
    public int hitungPelajar()     { return hitung("SELECT COUNT(*) FROM pelajar"); }
    public int hitungMateri()      { return hitung("SELECT COUNT(*) FROM materi"); }
    public int hitungKelasOnline() { return hitung("SELECT COUNT(*) FROM kelas_online"); }

    /** @return jumlah baris, atau -1 jika database tidak bisa diakses. */
    private int hitung(String sql) {
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) {
            System.err.println("Gagal menghitung data: " + e.getMessage());
            return -1;
        }
    }
}