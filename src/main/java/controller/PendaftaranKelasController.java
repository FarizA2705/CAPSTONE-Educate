package controller;

import dao.KelasOnlineDao;
import dao.PendaftaranKelasDao;
import model.KelasOnline;
import model.Pelajar;
import model.PendaftaranKelasOnline;
import java.time.LocalDate;
import java.util.List;

public class PendaftaranKelasController {
    private final KelasOnlineDao kelasDao = new KelasOnlineDao();
    private final PendaftaranKelasDao pendaftaranDao = new PendaftaranKelasDao();

    public List<KelasOnline> tampilkanDaftarKelas() {
        return kelasDao.getAll();
    }

    public boolean prosesPendaftaranKelas(int idPelajar, int idKelasOnline) {
        if (idPelajar <= 0 || idKelasOnline <= 0) {
            System.out.println("ID pelajar dan ID kelas tidak valid!");
            return false;
        }

        try {
            PendaftaranKelasOnline pendaftaran = new PendaftaranKelasOnline();
            pendaftaran.setTanggalDaftar(LocalDate.now());
            pendaftaran.setStatusPembayaran("Pending");

            Pelajar pelajar = new Pelajar();
            pelajar.setIdPengguna(idPelajar);
            pendaftaran.setPelajar(pelajar);

            KelasOnline kelas = new KelasOnline();
            kelas.setIdKelas(idKelasOnline);
            pendaftaran.setKelasOnline(kelas);

            boolean berhasil = pendaftaranDao.insert(pendaftaran);
            if (berhasil) {
                System.out.println("Pendaftaran kelas online berhasil dilakukan!");
            } else {
                System.out.println("Pendaftaran kelas online gagal.");
            }
            return berhasil;
        } catch (IllegalArgumentException e) {
            System.out.println("Data pendaftaran tidak valid: " + e.getMessage());
            return false;
        }
    }
}