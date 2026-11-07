package dao;

import config.EducateDB;
import java.sql.*;
import java.util.*;


public class RekapHasilDao {
 public static List<Object[]> ambil(int batas) throws SQLException {
  List<Object[]> hasil=new ArrayList<>();
  String sql="SELECT u.nama, COALESCE(GROUP_CONCAT(DISTINCT ps.nama_paket_soal ORDER BY ps.nama_paket_soal SEPARATOR ', '),'Latihan Soal') paket, DATE_FORMAT(h.tanggal_pengerjaan,'%d-%m-%Y') tanggal, h.skor FROM hasil_belajar h JOIN pengguna u ON u.id_pengguna=h.pelajar_id_pengguna LEFT JOIN detail_latihan d ON d.hasil_belajar_id_hasil=h.id_hasil LEFT JOIN latihan_soal ls ON ls.id_latihan_soal=d.latihan_soal_id_latihan_soal LEFT JOIN paket_soal ps ON ps.id_paket_soal=ls.paket_soal_id_paket_soal GROUP BY h.id_hasil,u.nama,h.tanggal_pengerjaan,h.skor ORDER BY h.tanggal_pengerjaan DESC,h.id_hasil DESC"+(batas>0?" LIMIT ?":"");
  try(Connection c=EducateDB.getConnection();PreparedStatement p=c.prepareStatement(sql)) {
   if(batas>0)p.setInt(1,batas);
   try(ResultSet r=p.executeQuery()){while(r.next())hasil.add(new Object[]{r.getString(1),r.getString(2),r.getString(3),r.getInt(4)});}
  }
  return hasil;
 }
}