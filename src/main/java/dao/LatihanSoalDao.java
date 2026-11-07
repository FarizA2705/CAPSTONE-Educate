package dao;

import config.EducateDB;
import model.LatihanSoal;
import model.Materi;
import model.PaketSoal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LatihanSoalDao implements CrudDao<LatihanSoal> {

    
    @Override
    public synchronized boolean insert(LatihanSoal latihan) {
    String sqlId = "SELECT COALESCE(MAX(id_latihan_soal), 0) + 1 FROM latihan_soal";
    String sql = "INSERT INTO latihan_soal (id_latihan_soal, pertanyaan, materi_id_materi, paket_soal_id_paket_soal) VALUES (?, ?, ?, ?)";

    try (Connection conn = EducateDB.getConnection()) {

        // 1. Hitung ID baru
        int idBaru = 1;
        try (PreparedStatement stmtId = conn.prepareStatement(sqlId);
             ResultSet rs = stmtId.executeQuery()) {
            if (rs.next()) {
                idBaru = rs.getInt(1);
            }
        }

        // 2. Insert dengan ID tersebut
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idBaru);
            stmt.setString(2, latihan.getPertanyaan());
            stmt.setInt(3, latihan.getMateri().getIdMateri());
            stmt.setInt(4, latihan.getPaketSoal().getIdPaketSoal());
            stmt.executeUpdate();
        }

        latihan.setIdLatihanSoal(idBaru);
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    @Override
    public boolean update(LatihanSoal latihan) {
        String sql = "UPDATE latihan_soal SET pertanyaan = ?, materi_id_materi = ?, paket_soal_id_paket_soal = ? WHERE id_latihan_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, latihan.getPertanyaan());
            stmt.setInt(2, latihan.getMateri().getIdMateri());
            stmt.setInt(3, latihan.getPaketSoal().getIdPaketSoal());
            stmt.setInt(4, latihan.getIdLatihanSoal());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM latihan_soal WHERE id_latihan_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public LatihanSoal getById(int id) {
        String sql = "SELECT * FROM latihan_soal WHERE id_latihan_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LatihanSoal> getAll() {
        List<LatihanSoal> list = new ArrayList<>();
        String sql = "SELECT * FROM latihan_soal";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<LatihanSoal> getAll(int idPaketSoal) {
        List<LatihanSoal> list = new ArrayList<>();
        String sql = "SELECT * FROM latihan_soal WHERE paket_soal_id_paket_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaketSoal);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<LatihanSoal> getByPaketId(int idPaketSoal) {
    return getAll(idPaketSoal);
}


    private LatihanSoal mapRow(ResultSet rs) throws SQLException {
        LatihanSoal latihan = new LatihanSoal();
        latihan.setIdLatihanSoal(rs.getInt("id_latihan_soal"));
        latihan.setPertanyaan(rs.getString("pertanyaan"));

        Materi materi = new Materi();
        materi.setIdMateri(rs.getInt("materi_id_materi"));
        latihan.setMateri(materi);

        PaketSoal paketSoal = new PaketSoal();
        paketSoal.setIdPaketSoal(rs.getInt("paket_soal_id_paket_soal"));
        latihan.setPaketSoal(paketSoal);
        return latihan;
    }
}