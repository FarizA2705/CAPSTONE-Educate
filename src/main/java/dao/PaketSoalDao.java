package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PaketSoal;

public class PaketSoalDao implements CrudDao<PaketSoal> {

    @Override
    public boolean insert(PaketSoal p) {
        String sql = "INSERT INTO paket_soal (nama_paket_soal, tingkat_kesulitan) VALUES (?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNamaPaketSoal());
            stmt.setString(2, p.getTingkatKesulitan());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PaketSoal p) {
        String sql = "UPDATE paket_soal SET nama_paket_soal = ?, tingkat_kesulitan = ? WHERE id_paket_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNamaPaketSoal());
            stmt.setString(2, p.getTingkatKesulitan());
            stmt.setInt(3, p.getIdPaketSoal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idPaket) {
        String sql = "DELETE FROM paket_soal WHERE id_paket_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaket);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(PaketSoal p) {
        return p != null && delete(p.getIdPaketSoal());
    }

    @Override
    public PaketSoal getById(int idPaket) {
        String sql = "SELECT * FROM paket_soal WHERE id_paket_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaket);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PaketSoal> getAll() {
        List<PaketSoal> list = new ArrayList<>();
        String sql = "SELECT * FROM paket_soal ORDER BY id_paket_soal";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Object[]> getRingkasanPaket(String topik) {
    List<Object[]> list = new ArrayList<>();

    String sql =
        "SELECT p.id_paket_soal, p.nama_paket_soal, p.tingkat_kesulitan, " +
        "       MIN(m.nama_materi) AS topik, " +
        "       COUNT(l.id_latihan_soal) AS jumlah_soal " +
        "FROM paket_soal p " +
        "LEFT JOIN latihan_soal l ON l.paket_soal_id_paket_soal = p.id_paket_soal " +
        "LEFT JOIN materi m ON m.id_materi = l.materi_id_materi " +
        "WHERE (? = 'Semua' OR m.nama_materi = ?) " +
        "GROUP BY p.id_paket_soal, p.nama_paket_soal, p.tingkat_kesulitan " +
        "ORDER BY p.id_paket_soal";

    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, topik);
        stmt.setString(2, topik);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PaketSoal p = map(rs);
                String namaTopik = rs.getString("topik");
                int jumlah = rs.getInt("jumlah_soal");
                list.add(new Object[]{p, namaTopik == null ? "-" : namaTopik, jumlah});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    private PaketSoal map(ResultSet rs) throws SQLException {
        PaketSoal p = new PaketSoal();
        p.setIdPaketSoal(rs.getInt("id_paket_soal"));
        p.setNamaPaketSoal(rs.getString("nama_paket_soal"));
        p.setTingkatKesulitan(rs.getString("tingkat_kesulitan"));
        return p;
    }
}