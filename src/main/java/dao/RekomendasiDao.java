package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HasilBelajar;
import model.Pengajar;
import model.Rekomendasi;

public class RekomendasiDao implements CrudDao<Rekomendasi> {

    @Override
    public boolean insert(Rekomendasi r) {
        String sql = "INSERT INTO rekomendasi (nilai_minimum, nilai_maksimum, isi_rekomendasi, pengajar_id_pengguna, hasil_belajar_id_hasil) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getNilaiMinimum());
            stmt.setInt(2, r.getNilaiMaksimum());
            stmt.setString(3, r.getIsiRekomendasi());
            stmt.setInt(4, r.getPengajar().getIdPengguna());
            stmt.setInt(5, r.getHasilBelajar().getIdHasil());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Rekomendasi r) {
        String sql = "UPDATE rekomendasi SET nilai_minimum = ?, nilai_maksimum = ?, isi_rekomendasi = ?, pengajar_id_pengguna = ?, hasil_belajar_id_hasil = ? WHERE id_rekomendasi = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getNilaiMinimum());
            stmt.setInt(2, r.getNilaiMaksimum());
            stmt.setString(3, r.getIsiRekomendasi());
            stmt.setInt(4, r.getPengajar().getIdPengguna());
            stmt.setInt(5, r.getHasilBelajar().getIdHasil());
            stmt.setInt(6, r.getIdRekomendasi());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idRekomendasi) {
        String sql = "DELETE FROM rekomendasi WHERE id_rekomendasi = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRekomendasi);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(Rekomendasi r) {
        return r != null && delete(r.getIdRekomendasi());
    }

    @Override
    public Rekomendasi getById(int idRekomendasi) {
        String sql = "SELECT * FROM rekomendasi WHERE id_rekomendasi = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRekomendasi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Rekomendasi> getAll() {
        List<Rekomendasi> list = new ArrayList<>();
        String sql = "SELECT * FROM rekomendasi ORDER BY id_rekomendasi";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Rekomendasi> getByHasil(int idHasil) {
        return query("SELECT * FROM rekomendasi WHERE hasil_belajar_id_hasil = ?", idHasil);
    }

    public List<Rekomendasi> getByPelajar(int idPelajar) {
        return query("SELECT r.* FROM rekomendasi r JOIN hasil_belajar h ON r.hasil_belajar_id_hasil = h.id_hasil "
                + "WHERE h.pelajar_id_pengguna = ? ORDER BY r.id_rekomendasi DESC", idPelajar);
    }

    private List<Rekomendasi> query(String sql, int param) {
        List<Rekomendasi> list = new ArrayList<>();
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Rekomendasi map(ResultSet rs) throws SQLException {
        Rekomendasi r = new Rekomendasi();
        r.setIdRekomendasi(rs.getInt("id_rekomendasi"));
        r.setNilaiMinimum(rs.getInt("nilai_minimum"));
        r.setNilaiMaksimum(rs.getInt("nilai_maksimum"));
        r.setIsiRekomendasi(rs.getString("isi_rekomendasi"));

        Pengajar p = new Pengajar();
        p.setIdPengguna(rs.getInt("pengajar_id_pengguna"));
        r.setPengajar(p);

        HasilBelajar hb = new HasilBelajar();
        hb.setIdHasil(rs.getInt("hasil_belajar_id_hasil"));
        r.setHasilBelajar(hb);
        return r;
    }
}