package dao;

import config.EducateDB;
import model.Jawaban;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JawabanDao implements CrudDao<Jawaban> {

    protected Connection connection;

    public JawabanDao() {
        this.connection = EducateDB.getConnection();
    }

    @Override
    public boolean insert(Jawaban j) {
        String query = "INSERT INTO jawaban (id_jawaban, pilihan_jawaban, kunci_jawaban, latihan_soal_id_latihan_soal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, j.getIdJawaban());
            stmt.setString(2, j.getPilihanJawaban());
            stmt.setString(3, j.getKunciJawaban());
            stmt.setInt(4, j.getIdLatihan());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan jawaban: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Jawaban j) {
        String query = "UPDATE jawaban SET pilihan_jawaban = ?, kunci_jawaban = ?, latihan_soal_id_latihan_soal = ? WHERE id_jawaban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, j.getPilihanJawaban());
            stmt.setString(2, j.getKunciJawaban());
            stmt.setInt(3, j.getIdLatihan());
            stmt.setInt(4, j.getIdJawaban());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal mengubah jawaban: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM jawaban WHERE id_jawaban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal menghapus jawaban: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Jawaban getById(int id) {
        String query = "SELECT * FROM jawaban WHERE id_jawaban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil jawaban: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Jawaban> getAll() {
        List<Jawaban> list = new ArrayList<>();
        String query = "SELECT * FROM jawaban";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data jawaban: " + e.getMessage());
        }
        return list;
    }

    public List<Jawaban> getByLatihanId(int idLatihan) {
        List<Jawaban> list = new ArrayList<>();
        String query = "SELECT * FROM jawaban WHERE latihan_soal_id_latihan_soal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idLatihan);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data jawaban: " + e.getMessage());
        }
        return list;
    }

    private Jawaban mapRow(ResultSet rs) throws SQLException {
        Jawaban j = new Jawaban();
        j.setIdJawaban(rs.getInt("id_jawaban"));
        j.setPilihanJawaban(rs.getString("pilihan_jawaban"));
        j.setKunciJawaban(rs.getString("kunci_jawaban"));
        j.setIdLatihan(rs.getInt("latihan_soal_id_latihan_soal"));
        return j;
    }
}