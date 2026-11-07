package dao;
import config.EducateDB;
import java.sql.*;
/** Deletes related rows in one transaction; never disables foreign-key checks. */
public final class PenghapusanBerantaiDao {
 private PenghapusanBerantaiDao() {}
 private static int exec(Connection c,String sql,int id) throws SQLException {
  try(PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,id);return p.executeUpdate();}
 }
 public static boolean hapus(String jenis,int id) throws SQLException {
  try(Connection c=EducateDB.getConnection()) {
   if(c==null)throw new SQLException("Tidak terhubung ke MySQL XAMPP");
   c.setAutoCommit(false);
   try {
    int count;
    switch(jenis) {
     case "pelajar":
      exec(c,"DELETE FROM detail_latihan WHERE hasil_belajar_id_hasil IN (SELECT id_hasil FROM hasil_belajar WHERE pelajar_id_pengguna=?)",id);
      exec(c,"DELETE FROM rekomendasi WHERE hasil_belajar_id_hasil IN (SELECT id_hasil FROM hasil_belajar WHERE pelajar_id_pengguna=?)",id);
      exec(c,"DELETE FROM hasil_belajar WHERE pelajar_id_pengguna=?",id);
      exec(c,"DELETE FROM pendaftaran_kelas_online WHERE pelajar_id_pengguna=?",id);
      exec(c,"DELETE FROM pelajar WHERE id_pengguna=?",id);
      count=exec(c,"DELETE FROM pengguna WHERE id_pengguna=? AND role='Pelajar'",id);break;
     case "materi":
      exec(c,"DELETE FROM detail_latihan WHERE latihan_soal_id_latihan_soal IN (SELECT id_latihan_soal FROM latihan_soal WHERE materi_id_materi=?)",id);
      exec(c,"DELETE FROM jawaban WHERE latihan_soal_id_latihan_soal IN (SELECT id_latihan_soal FROM latihan_soal WHERE materi_id_materi=?)",id);
      exec(c,"DELETE FROM latihan_soal WHERE materi_id_materi=?",id);
      count=exec(c,"DELETE FROM materi WHERE id_materi=?",id);break;
     case "kelas":
      exec(c,"DELETE FROM pendaftaran_kelas_online WHERE kelas_online_id_kelas=?",id);
      count=exec(c,"DELETE FROM kelas_online WHERE id_kelas=?",id);break;
     case "soal":
      exec(c,"DELETE FROM detail_latihan WHERE latihan_soal_id_latihan_soal=?",id);
      exec(c,"DELETE FROM jawaban WHERE latihan_soal_id_latihan_soal=?",id);
      count=exec(c,"DELETE FROM latihan_soal WHERE id_latihan_soal=?",id);break;
     case "rekomendasi":count=exec(c,"DELETE FROM rekomendasi WHERE id_rekomendasi=?",id);break;
     default:throw new SQLException("Jenis data tidak diizinkan: "+jenis);
    }
    if(count==0)throw new SQLException("Data tidak ditemukan (ID "+id+")");
    c.commit();return true;
   }catch(Exception e){c.rollback();if(e instanceof SQLException)throw (SQLException)e;throw new SQLException(e);}
  }
 }
}