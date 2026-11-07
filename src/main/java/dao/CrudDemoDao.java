package dao;

import config.EducateDB;
import controller.SessionManagerController;
import java.sql.*;


public class CrudDemoDao {
  public static int pengajar() { if(SessionManagerController.getPenggunaAktif()==null) throw new IllegalStateException("Silakan login dahulu"); return SessionManagerController.getPenggunaAktif().getIdPengguna(); }
  public static int cariId(String tabel,String kolom,String nilai) throws SQLException {
    if(!tabel.equals("materi")&&!tabel.equals("paket_soal")) throw new SQLException("Tabel tidak diizinkan");
    String id=tabel.equals("materi")?"id_materi":"id_paket_soal";
    try(Connection c=koneksi();PreparedStatement s=c.prepareStatement("SELECT "+id+" FROM "+tabel+" WHERE "+kolom+"=? LIMIT 1")) {
      s.setString(1,nilai);try(ResultSet r=s.executeQuery()){if(r.next())return r.getInt(1);}
    }
    throw new SQLException("Data referensi tidak ditemukan: "+nilai+". Tambahkan data tersebut terlebih dahulu.");
  }
  private static Connection koneksi() throws SQLException {Connection c=EducateDB.getConnection();if(c==null)throw new SQLException("MySQL tidak terhubung. Jalankan MySQL XAMPP dan impor database educate.");return c;}
  public static void jalankan(String sql,Object... nilai) throws SQLException {
    try(Connection c=koneksi();PreparedStatement s=c.prepareStatement(sql)){
      for(int i=0;i<nilai.length;i++)s.setObject(i+1,nilai[i]);
      if(s.executeUpdate()==0)throw new SQLException("Tidak ada baris yang berubah.");
    }
  }
  public static java.sql.Date tanggal(String t){String x=t.trim();if(x.matches("\\d{2}-\\d{2}-\\d{4}")){String[] a=x.split("-");x=a[2]+"-"+a[1]+"-"+a[0];}return java.sql.Date.valueOf(x);}
  public static int angka(String s){return Integer.parseInt(s.replaceAll("[^0-9]",""));}
  public static void pesan(java.awt.Component panel,Exception e){javax.swing.JOptionPane.showMessageDialog(panel,"Operasi database gagal: "+e.getMessage(),"Database",javax.swing.JOptionPane.ERROR_MESSAGE);}
  public static boolean konfirmasi(java.awt.Component panel){return javax.swing.JOptionPane.showConfirmDialog(panel,"Hapus data terpilih BESERTA semua data yang terkait? Tindakan ini tidak dapat dibatalkan.","Konfirmasi",javax.swing.JOptionPane.YES_NO_OPTION)==javax.swing.JOptionPane.YES_OPTION;}
}