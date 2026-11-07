package dao;

import config.EducateDB;
import model.DetailLatihan;
import model.HasilBelajar;
import model.Pelajar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HasilBelajarDao implements CrudDao<HasilBelajar> {

    @Override
    public boolean insert(HasilBelajar hb) {
        String sql = "INSERT INTO hasil_belajar (tanggal_pengerjaan, total_soal, jumlah_benar, skor, pelajar_id_pengguna) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(hb.getTanggalPengerjaan()));
            stmt.setInt(2, hb.getTotalSoal());
            stmt.setInt(3, hb.getJumlahBenar());
            stmt.setDouble(4, hb.getSkor());
            stmt.setInt(5, hb.getPelajar().getIdPengguna());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    hb.setIdHasil(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertHasil(HasilBelajar hb) {
        return insert(hb);
    }

    public boolean insertDetail(DetailLatihan dl) {
        String sql = "INSERT INTO detail_latihan (jawaban_pelajar, status_jawaban, latihan_soal_id_latihan_soal, hasil_belajar_id_hasil) VALUES (?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dl.getJawabanPelajar());
            stmt.setString(2, dl.getStatusJawaban());
            stmt.setInt(3, dl.getLatihanSoal().getIdLatihanSoal());
            stmt.setInt(4, dl.getHasilBelajar().getIdHasil());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(HasilBelajar hb) {
        String sql = "UPDATE hasil_belajar SET tanggal_pengerjaan = ?, total_soal = ?, jumlah_benar = ?, skor = ?, pelajar_id_pengguna = ? WHERE id_hasil = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(hb.getTanggalPengerjaan()));
            stmt.setInt(2, hb.getTotalSoal());
            stmt.setInt(3, hb.getJumlahBenar());
            stmt.setDouble(4, hb.getSkor());
            stmt.setInt(5, hb.getPelajar().getIdPengguna());
            stmt.setInt(6, hb.getIdHasil());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        String sqlDetail = "DELETE FROM detail_latihan WHERE hasil_belajar_id_hasil = ?";
        String sqlHasil = "DELETE FROM hasil_belajar WHERE id_hasil = ?";
        try (Connection conn = EducateDB.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtDetail = conn.prepareStatement(sqlDetail);
                 PreparedStatement stmtHasil = conn.prepareStatement(sqlHasil)) {
                stmtDetail.setInt(1, id);
                stmtDetail.executeUpdate();

                stmtHasil.setInt(1, id);
                int baris = stmtHasil.executeUpdate();

                conn.commit();
                return baris > 0;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public HasilBelajar getById(int id) {
        String sql = "SELECT * FROM hasil_belajar WHERE id_hasil = ?";
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
    public List<HasilBelajar> getAll() {
        List<HasilBelajar> list = new ArrayList<>();
        String sql = "SELECT * FROM hasil_belajar";
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

    public List<HasilBelajar> getByPelajar(int idPelajar) {
        List<HasilBelajar> list = new ArrayList<>();
        String sql = "SELECT * FROM hasil_belajar WHERE pelajar_id_pengguna = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelajar);
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


    public java.util.List<Object[]> getRiwayatPelajar(int idPengguna) {
    java.util.List<Object[]> list = new java.util.ArrayList<>();
    String sql = "SELECT h.tanggal_pengerjaan, "
               + "       (SELECT p.nama_paket_soal "
               + "          FROM detail_latihan d "
               + "          JOIN latihan_soal l ON l.id_latihan_soal = d.latihan_soal_id_latihan_soal "
               + "          JOIN paket_soal p ON p.id_paket_soal = l.paket_soal_id_paket_soal "
               + "         WHERE d.hasil_belajar_id_hasil = h.id_hasil LIMIT 1) AS nama_paket, "
               + "       h.skor, h.jumlah_benar, h.total_soal "
               + "FROM hasil_belajar h "
               + "WHERE h.pelajar_id_pengguna = ? "
               + "ORDER BY h.tanggal_pengerjaan DESC, h.id_hasil DESC";
    try (java.sql.Connection conn = config.EducateDB.getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idPengguna);
        try (java.sql.ResultSet rs = stmt.executeQuery()) {
            java.time.format.DateTimeFormatter f =
                    java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
            while (rs.next()) {
                String tgl = rs.getDate(1).toLocalDate().format(f);
                String paket = rs.getString(2);
                list.add(new Object[]{
                    tgl, paket != null ? paket : "-", (int) Math.round(rs.getDouble(3)),
                    rs.getInt(4) + " / " + rs.getInt(5)
                });
            }
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
    return list;
}


    private HasilBelajar mapRow(ResultSet rs) throws SQLException {
        HasilBelajar hb = new HasilBelajar();
        hb.setIdHasil(rs.getInt("id_hasil"));
        hb.setTanggalPengerjaan(rs.getDate("tanggal_pengerjaan").toLocalDate());
        hb.setTotalSoal(rs.getInt("total_soal"));
        hb.setJumlahBenar(rs.getInt("jumlah_benar"));
        hb.setSkor((int) rs.getDouble("skor")); // sesuaikan kalau tipe skor di model adalah double

        Pelajar pelajar = new Pelajar();
        pelajar.setIdPengguna(rs.getInt("pelajar_id_pengguna"));
        hb.setPelajar(pelajar);
        return hb;
    }
}