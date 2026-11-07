package dao;

import config.EducateDB;
import model.KelasOnline;
import model.Pengajar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class KelasOnlineDao implements CrudDao<KelasOnline> {


    @Override
    public boolean insert(KelasOnline kelas) {
        String sql = "INSERT INTO kelas_online (nama_kelas, deskripsi_kelas, tanggal_kelas, waktu_mulai, waktu_selesai, link_kelas, harga, status_kelas, kuota_maksimal, pengajar_id_pengguna) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            isiParameter(stmt, kelas);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    kelas.setIdKelas(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(KelasOnline kelas) {
        String sql = "UPDATE kelas_online SET nama_kelas = ?, deskripsi_kelas = ?, tanggal_kelas = ?, waktu_mulai = ?, waktu_selesai = ?, link_kelas = ?, harga = ?, status_kelas = ?, kuota_maksimal = ?, pengajar_id_pengguna = ? WHERE id_kelas = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            isiParameter(stmt, kelas);
            stmt.setInt(11, kelas.getIdKelas());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM kelas_online WHERE id_kelas = ?";
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
    public KelasOnline getById(int id) {
        String sql = "SELECT * FROM kelas_online WHERE id_kelas = ?";
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
    public List<KelasOnline> getAll() {
        List<KelasOnline> listKelas = new ArrayList<>();
        String sql = "SELECT * FROM kelas_online";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listKelas.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKelas;
    }

    public List<KelasOnline> getAllKelas() {
        return getAll();
    }


    public List<KelasOnline> getAll(int idPengajar) {
        List<KelasOnline> listKelas = new ArrayList<>();
        String sql = "SELECT * FROM kelas_online WHERE pengajar_id_pengguna = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPengajar);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listKelas.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKelas;
    }


    private void isiParameter(PreparedStatement stmt, KelasOnline kelas) throws SQLException {
        stmt.setString(1, kelas.getNamaKelas());
        stmt.setString(2, kelas.getDeskripsiKelas());
        stmt.setDate(3, Date.valueOf(kelas.getTanggalKelas()));
        stmt.setString(4, kelas.getWaktuMulai().toString());
        stmt.setString(5, kelas.getWaktuSelesai().toString());
        stmt.setString(6, kelas.getLinkKelas());
        stmt.setDouble(7, kelas.getHarga());
        stmt.setString(8, kelas.getStatusKelas());
        stmt.setInt(9, kelas.getKuotaMaksimal());
        stmt.setInt(10, kelas.getPengajar().getIdPengguna());
    }


    private KelasOnline mapRow(ResultSet rs) throws SQLException {
        KelasOnline kelas = new KelasOnline();
        kelas.setIdKelas(rs.getInt("id_kelas"));
        kelas.setNamaKelas(rs.getString("nama_kelas"));
        kelas.setDeskripsiKelas(rs.getString("deskripsi_kelas"));

        if (rs.getDate("tanggal_kelas") != null) {
            kelas.setTanggalKelas(rs.getDate("tanggal_kelas").toLocalDate());
        }

        LocalTime mulai = parseWaktu(rs.getString("waktu_mulai"));
        if (mulai != null) {
            kelas.setWaktuMulai(mulai);
        }
        LocalTime selesai = parseWaktu(rs.getString("waktu_selesai"));
        if (selesai != null) {
            kelas.setWaktuSelesai(selesai);
        }

        kelas.setLinkKelas(rs.getString("link_kelas"));
        kelas.setHarga(parseHarga(rs.getString("harga")));
        kelas.setStatusKelas(rs.getString("status_kelas"));
        kelas.setKuotaMaksimal(rs.getInt("kuota_maksimal"));

        Pengajar pengajar = new Pengajar();
        pengajar.setIdPengguna(rs.getInt("pengajar_id_pengguna"));
        kelas.setPengajar(pengajar);
        return kelas;
    }


    private LocalTime parseWaktu(String teks) {
        if (teks == null) {
            return null;
        }
        String bersih = teks.replaceAll("[^0-9:]", "").trim();
        return bersih.isEmpty() ? null : LocalTime.parse(bersih);
    }


    private Double parseHarga(String teks) {
        if (teks == null) {
            return 0.0;
        }
        String bersih = teks.replaceAll("[^0-9]", "");
        return bersih.isEmpty() ? 0.0 : Double.parseDouble(bersih);
    }
}