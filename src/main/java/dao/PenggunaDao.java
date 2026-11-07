package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pelajar;
import model.Pengajar;
import model.Pengguna;

public class PenggunaDao implements CrudDao<Pengguna> {
    public Pengguna login(String usernameOrEmail, String password) {
        Pengguna pengguna = null;
        String sql = "SELECT * FROM pengguna WHERE (username = ? OR email = ?) AND password = ?";

        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pengguna = map(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pengguna;
    }

    @Override
public boolean insert(Pengguna p) {
    boolean pelajar = p instanceof Pelajar;
    String sql = pelajar
        ? "INSERT INTO pengguna (nama, email, username, password, role, jenis_kelamin, tanggal_lahir, kelas, tanggal_daftar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        : "INSERT INTO pengguna (nama, email, username, password, role, jenis_kelamin, tanggal_lahir) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = EducateDB.getConnection()) {
        conn.setAutoCommit(false);
        try (PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getUsername());
            stmt.setString(4, p.getPassword());
            stmt.setString(5, p.getRole());
            stmt.setString(6, p.getJenisKelamin());
            stmt.setDate(7, Date.valueOf(p.getTanggalLahir()));
            if (pelajar) {
                Pelajar pl = (Pelajar) p;
                stmt.setInt(8, pl.getKelas());
                stmt.setDate(9, Date.valueOf(pl.getTanggalDaftar()));
            }
            stmt.executeUpdate();

            if (pelajar) {
                int idBaru;
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (!keys.next()) throw new SQLException("ID pengguna baru tidak terbaca");
                    idBaru = keys.getInt(1);
                }
                Pelajar pl = (Pelajar) p;
                try (PreparedStatement ps2 = conn.prepareStatement(
                        "INSERT INTO pelajar (id_pengguna, tanggal_daftar, kelas) VALUES (?, ?, ?)")) {
                    ps2.setInt(1, idBaru);
                    ps2.setDate(2, Date.valueOf(pl.getTanggalDaftar()));
                    ps2.setInt(3, pl.getKelas());
                    ps2.executeUpdate();
                }
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    @Override
    public boolean update(Pengguna p) {
        String sql = "UPDATE pengguna SET nama = ?, email = ?, username = ?, role = ?, jenis_kelamin = ?, tanggal_lahir = ? WHERE id_pengguna = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getUsername());
            stmt.setString(4, p.getRole());
            stmt.setString(5, p.getJenisKelamin());
            stmt.setDate(6, Date.valueOf(p.getTanggalLahir()));
            stmt.setInt(7, p.getIdPengguna());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idPengguna) {
        String sql = "DELETE FROM pengguna WHERE id_pengguna = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPengguna);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Pengguna p) {
        return p != null && delete(p.getIdPengguna());
    }

    @Override
    public Pengguna getById(int idPengguna) {
        String sql = "SELECT * FROM pengguna WHERE id_pengguna = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPengguna);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pengguna> getAll() {
        List<Pengguna> list = new ArrayList<>();
        String sql = "SELECT * FROM pengguna ORDER BY id_pengguna";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean sudahAda(String username, String email) {
    String sql = "SELECT 1 FROM pengguna WHERE username = ? OR LOWER(email) = LOWER(?)";
    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, email);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return true; // anggap ada supaya tidak menyimpan saat database bermasalah
    }
}
    public boolean updateProfil(Pelajar p, String passwordBaru) {
    boolean gantiPassword = passwordBaru != null && !passwordBaru.isEmpty();
    String sql1 = "UPDATE pengguna SET nama = ?, email = ?, kelas = ?"
            + (gantiPassword ? ", password = ?" : "")
            + " WHERE id_pengguna = ?";
    String sql2 = "UPDATE pelajar SET kelas = ? WHERE id_pengguna = ?";
    try (Connection conn = EducateDB.getConnection()) {
        conn.setAutoCommit(false);
        try (PreparedStatement s1 = conn.prepareStatement(sql1);
             PreparedStatement s2 = conn.prepareStatement(sql2)) {
            int i = 1;
            s1.setString(i++, p.getNama());
            s1.setString(i++, p.getEmail());
            s1.setInt(i++, p.getKelas());
            if (gantiPassword) s1.setString(i++, passwordBaru);
            s1.setInt(i, p.getIdPengguna());
            s1.executeUpdate();

            s2.setInt(1, p.getKelas());
            s2.setInt(2, p.getIdPengguna());
            s2.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    

    private Pengguna map(ResultSet rs) throws SQLException {
        String role = rs.getString("role");

        Pengguna p;
        if ("pengajar".equalsIgnoreCase(role)) {
            p = new Pengajar();
        } else {
            p = new Pelajar();
        }

        p.setIdPengguna(rs.getInt("id_pengguna"));
        p.setNama(rs.getString("nama"));
        p.setEmail(rs.getString("email"));
        p.setUsername(rs.getString("username"));
        p.setRole(role);
        p.setJenisKelamin(rs.getString("jenis_kelamin"));
        Date tanggalLahir = rs.getDate("tanggal_lahir");
        if (tanggalLahir != null) {
            p.setTanggalLahir(tanggalLahir.toLocalDate());
        }
        if (p instanceof Pelajar) {
        Pelajar pl = (Pelajar) p;
        int kelas = rs.getInt("kelas");
        if (kelas >= Pelajar.KELAS_MIN && kelas <= Pelajar.KELAS_MAX) pl.setKelas(kelas);
        Date td = rs.getDate("tanggal_daftar");
        if (td != null) pl.setTanggalDaftar(td.toLocalDate());
        }
        return p;
    }
}