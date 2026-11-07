/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP VICTUS
 */

public class TampilanDataDao {

    // Mengambil semua data kelas online
    public List<Object[]> getDataKelasOnline() {
    String sql = "SELECT u.nama AS pengajar, k.nama_kelas, "
            + "DATE_FORMAT(k.tanggal_kelas, '%d-%m-%Y') AS jadwal, "
            + "k.waktu_mulai, k.waktu_selesai, k.link_kelas, k.harga, k.status_kelas "
            + "FROM kelas_online k JOIN pengguna u ON u.id_pengguna=k.pengajar_id_pengguna ORDER BY k.id_kelas";

    List<Object[]> data = ambilData(sql);

    System.out.println("DAO - Jumlah kelas online: " + data.size());

    return data;
}

    // Mengambil data latihan soal beserta nama paket dan topik materi
    public List<Object[]> getDataLatihanSoal() {
        String sql = "SELECT ls.id_latihan_soal, "
                + "ps.nama_paket_soal, m.topik, "
                + "ps.tingkat_kesulitan, ls.pertanyaan "
                + "FROM latihan_soal ls "
                + "JOIN paket_soal ps "
                + "ON ls.paket_soal_id_paket_soal = ps.id_paket_soal "
                + "JOIN materi m "
                + "ON ls.materi_id_materi = m.id_materi "
                + "ORDER BY ls.id_latihan_soal";

        return ambilData(sql);
    }

    // Mengambil semua data rekomendasi
    public List<Object[]> getDataRekomendasi() {
        String sql = "SELECT id_rekomendasi, nilai_maksimum, "
                + "nilai_minimum, isi_rekomendasi "
                + "FROM rekomendasi ORDER BY id_rekomendasi";

        return ambilData(sql);
    }

    // Mengambil data pelajar beserta informasi dari tabel pengguna
    public List<Object[]> getDataPelajar() {
        String sql = "SELECT u.id_pengguna, u.nama, u.email, "
                + "u.username, u.jenis_kelamin, "
                + "DATE_FORMAT(u.tanggal_lahir, '%d-%m-%Y') AS tanggal_lahir, "
                + "p.kelas, "
                + "DATE_FORMAT(p.tanggal_daftar, '%d-%m-%Y') AS tanggal_daftar "
                + "FROM pelajar p "
                + "JOIN pengguna u ON p.id_pengguna = u.id_pengguna "
                + "ORDER BY u.id_pengguna";

        return ambilData(sql);
    }

    // Mengambil seluruh hasil belajar
    public List<Object[]> getDataHasilBelajar() {
        String sql = "SELECT u.nama AS nama_pelajar, "
                + "DATE_FORMAT(hb.tanggal_pengerjaan, '%d-%m-%Y') AS tanggal, "
                + "hb.total_soal, hb.jumlah_benar, hb.skor "
                + "FROM hasil_belajar hb JOIN pengguna u ON u.id_pengguna=hb.pelajar_id_pengguna ORDER BY hb.tanggal_pengerjaan DESC, hb.id_hasil DESC";

        return ambilData(sql);
    }

    // Method umum untuk menjalankan SELECT
    private List<Object[]> ambilData(String sql) {
        List<Object[]> daftarData = new ArrayList<>();

        try (Connection conn = EducateDB.getConnection()) {

            if (conn == null) {
                throw new SQLException("Koneksi database gagal.");
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                int jumlahKolom = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    Object[] baris = new Object[jumlahKolom];

                    for (int i = 0; i < jumlahKolom; i++) {
                        baris[i] = rs.getObject(i + 1);
                    }

                    daftarData.add(baris);
                }
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data: " + e.getMessage());
            e.printStackTrace();
        }

        return daftarData;
    }
}