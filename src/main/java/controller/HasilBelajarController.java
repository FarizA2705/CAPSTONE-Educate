package controller;

import dao.HasilBelajarDao;
import model.DetailLatihan;
import model.HasilBelajar;
import model.Pelajar;
import java.time.LocalDate;
import java.util.List;

public class HasilBelajarController {
    private final HasilBelajarDao dao = new HasilBelajarDao();

    public boolean simpanHasilBelajarPelajar(int idPelajar, int totalSoal,
            int jumlahBenar, int skor, List<DetailLatihan> listDetailJawaban) {

        if (totalSoal <= 0 || jumlahBenar < 0 || jumlahBenar > totalSoal) {
            System.out.println("Data hasil belajar tidak valid!");
            return false;
        }

        Pelajar pelajar = new Pelajar();
        pelajar.setIdPengguna(idPelajar);

        HasilBelajar hasil = new HasilBelajar();
        hasil.setTanggalPengerjaan(LocalDate.now());
        hasil.setTotalSoal(totalSoal);
        hasil.setJumlahBenar(jumlahBenar);
        hasil.setSkor(skor);
        hasil.setPelajar(pelajar);

        if (!dao.insert(hasil)) {
            System.out.println("Gagal menyimpan hasil belajar.");
            return false;
        }

        if (listDetailJawaban != null) {
            for (DetailLatihan dl : listDetailJawaban) {
                dl.setHasilBelajar(hasil);   // hasil sudah punya idHasil
                dao.insertDetail(dl);
            }
        }

        System.out.println("Hasil belajar pelajar ID " + idPelajar
                + " berhasil disimpan. Skor: " + skor);
        return true;
    }

    public List<HasilBelajar> ambilRiwayatPelajar(int idPelajar) {
        return dao.getByPelajar(idPelajar);
    }
}