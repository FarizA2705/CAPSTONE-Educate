package controller;

import model.LatihanSoal;
import model.Materi;
import model.PaketSoal;
import model.Jawaban;
import dao.LatihanSoalDao;
import dao.JawabanDao;
import java.util.List;

public class LatihanSoalController {

    private final LatihanSoalDao latihanSoalDao = new LatihanSoalDao();
    private final JawabanDao jawabanDao = new JawabanDao();

    public boolean buatSoalDanJawaban(String pertanyaan, int idMateri,
            int idPaketSoal, List<Jawaban> daftarJawaban) {

        if (pertanyaan == null || pertanyaan.trim().isEmpty()
                || daftarJawaban == null || daftarJawaban.isEmpty()) {
            System.out.println("Pertanyaan dan daftar jawaban tidak boleh kosong!");
            return false;
        }

        LatihanSoal soal = new LatihanSoal();
        soal.setPertanyaan(pertanyaan);

        Materi materi = new Materi();
        materi.setIdMateri(idMateri);
        soal.setMateri(materi);

        PaketSoal paketSoal = new PaketSoal();
        paketSoal.setIdPaketSoal(idPaketSoal);
        soal.setPaketSoal(paketSoal);

        if (!latihanSoalDao.insert(soal)) {
            System.out.println("Gagal menambahkan soal latihan.");
            return false;
        }

        boolean semuaBerhasil = true;
        for (Jawaban jb : daftarJawaban) {
            jb.setLatihanSoal(soal); // soal sudah punya id dari database
            if (!jawabanDao.insert(jb)) {
                semuaBerhasil = false;
            }
        }

        if (semuaBerhasil) {
            System.out.println("Soal latihan dan jawaban berhasil ditambahkan.");
        } else {
            System.out.println("Soal tersimpan, tetapi ada jawaban yang gagal disimpan.");
        }
        return semuaBerhasil;
    }
}