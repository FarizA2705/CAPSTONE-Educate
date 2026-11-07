package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.KelasOnline;
import model.Pelajar;
import model.PendaftaranKelasOnline;

public class PendaftaranKelasDao implements CrudDao<PendaftaranKelasOnline> {
    public boolean daftarKelas(int idPendaftaran, PendaftaranKelasOnline pendaftaran) {
        String sql = "INSERT INTO pendaftaran_kelas_online (id_pendaftaran, tanggal_daftar, harga_bayar, status_pembayaran, pelajar_id_pengguna, kelas_online_id_kelas) " +
                     "VALUES (?, ?, (SELECT harga FROM kelas_online WHERE id_kelas = ?), ?, ?, ?)";

        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPendaftaran);
            stmt.setDate(2, Date.valueOf(pendaftaran.getTanggalDaftar()));
            stmt.setInt(3, pendaftaran.getKelasOnline().getIdKelas()); // Parameter ke-3 untuk mencari harga kelas di dalam subquery
            stmt.setString(4, pendaftaran.getStatusPembayaran());
            stmt.setInt(5, pendaftaran.getPelajar().getIdPengguna());
            stmt.setInt(6, pendaftaran.getKelasOnline().getIdKelas());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(PendaftaranKelasOnline pendaftaran) {
        return daftarKelas(pendaftaran.getIdPendaftaran(), pendaftaran);
    }

    @Override
    public boolean update(PendaftaranKelasOnline pendaftaran) {
        // harga_bayar sengaja tidak diubah karena sudah dikunci saat pendaftaran
        String sql = "UPDATE pendaftaran_kelas_online SET tanggal_daftar = ?, status_pembayaran = ? WHERE id_pendaftaran = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(pendaftaran.getTanggalDaftar()));
            stmt.setString(2, pendaftaran.getStatusPembayaran());
            stmt.setInt(3, pendaftaran.getIdPendaftaran());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idPendaftaran) {
        String sql = "DELETE FROM pendaftaran_kelas_online WHERE id_pendaftaran = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPendaftaran);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(PendaftaranKelasOnline pendaftaran) {
        return pendaftaran != null && delete(pendaftaran.getIdPendaftaran());
    }

    @Override
    public PendaftaranKelasOnline getById(int idPendaftaran) {
        String sql = "SELECT * FROM pendaftaran_kelas_online WHERE id_pendaftaran = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPendaftaran);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PendaftaranKelasOnline> getAll() {
        List<PendaftaranKelasOnline> list = new ArrayList<>();
        String sql = "SELECT * FROM pendaftaran_kelas_online ORDER BY id_pendaftaran";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private PendaftaranKelasOnline map(ResultSet rs) throws SQLException {
        PendaftaranKelasOnline p = new PendaftaranKelasOnline();
        p.setIdPendaftaran(rs.getInt("id_pendaftaran"));
        p.setTanggalDaftar(rs.getDate("tanggal_daftar").toLocalDate());
        p.setHargaBayar(rs.getDouble("harga_bayar"));
        p.setStatusPembayaran(rs.getString("status_pembayaran"));

        Pelajar pelajar = new Pelajar();
        pelajar.setIdPengguna(rs.getInt("pelajar_id_pengguna"));
        p.setPelajar(pelajar);

        KelasOnline kelas = new KelasOnline();
        kelas.setIdKelas(rs.getInt("kelas_online_id_kelas"));
        p.setKelasOnline(kelas);
        return p;
    }
}