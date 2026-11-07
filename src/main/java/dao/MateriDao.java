package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Materi;
import model.Pengajar;

public class MateriDao implements CrudDao<Materi> {

    @Override
    public boolean insert(Materi m) {
        if (m == null || m.getPengajar() == null) {
            return false;
        }
        String sql = "INSERT INTO materi (id_materi, nama_materi, topik, tipe_materi, deskripsi_materi, pengajar_id_pengguna) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection()) {
            int idBaru = idBerikutnya(conn); // id dibuat di sini, tidak bergantung AUTO_INCREMENT
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idBaru);
                stmt.setString(2, m.getNamaMateri());
                stmt.setString(3, m.getTopik());
                stmt.setString(4, m.getTipeMateri());
                stmt.setString(5, m.getDeskripsiMateri());
                stmt.setInt(6, m.getPengajar().getIdPengguna()); // Mengambil ID dari objek Pengajar
                if (stmt.executeUpdate() > 0) {
                    m.setIdMateri(idBaru);
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int idBerikutnya(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(id_materi), 0) + 1 FROM materi";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 1;
        }
    }

    @Override
    public boolean update(Materi m) {
        if (m == null || m.getPengajar() == null) {
            return false;
        }
        String sql = "UPDATE materi SET nama_materi = ?, topik = ?, tipe_materi = ?, deskripsi_materi = ?, pengajar_id_pengguna = ? WHERE id_materi = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNamaMateri());
            stmt.setString(2, m.getTopik());
            stmt.setString(3, m.getTipeMateri());
            stmt.setString(4, m.getDeskripsiMateri());
            stmt.setInt(5, m.getPengajar().getIdPengguna());
            stmt.setInt(6, m.getIdMateri());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idMateri) {
        String sql = "DELETE FROM materi WHERE id_materi = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMateri);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Materi m) {
        return m != null && delete(m.getIdMateri());
    }

    @Override
    public Materi getById(int idMateri) {
        String sql = "SELECT * FROM materi WHERE id_materi = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMateri);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Materi> getAll() {
        String sql = "SELECT * FROM materi";
        try (Connection conn = EducateDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return bacaDaftar(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Materi> getAll(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAll();
        }
        String sql = "SELECT * FROM materi WHERE nama_materi LIKE ? OR topik LIKE ? OR tipe_materi LIKE ?";
        String pola = "%" + keyword.trim() + "%";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pola);
            stmt.setString(2, pola);
            stmt.setString(3, pola);
            try (ResultSet rs = stmt.executeQuery()) {
                return bacaDaftar(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Materi> bacaDaftar(ResultSet rs) throws SQLException {
        List<Materi> list = new ArrayList<>();
        while (rs.next()) {
            try {
                list.add(mapRow(rs));
            } catch (IllegalArgumentException e) {
                System.err.println("Data materi dilewati (id " + rs.getInt("id_materi")
                        + "): " + e.getMessage());
            }
        }
        return list;
    }

    private Materi mapRow(ResultSet rs) throws SQLException {
        Materi m = new Materi();
        m.setIdMateri(rs.getInt("id_materi"));
        m.setNamaMateri(rs.getString("nama_materi"));
        m.setTopik(rs.getString("topik"));
        m.setTipeMateri(rs.getString("tipe_materi"));
        m.setDeskripsiMateri(rs.getString("deskripsi_materi"));
        Pengajar p = new Pengajar();
        p.setIdPengguna(rs.getInt("pengajar_id_pengguna"));
        m.setPengajar(p);
        return m;
    }
}