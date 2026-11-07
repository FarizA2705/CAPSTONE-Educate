package controller;

import model.Materi;
import model.Pengajar;
import dao.MateriDao;
import java.util.List;

public class MateriController {

    private final MateriDao materiDao = new MateriDao();

    public boolean tambahMateri(String namaMateri, String topik, String tipeMateri,
            String deskripsi, int idPengajar) {
        try {
            Materi m = new Materi();
            m.setNamaMateri(namaMateri);
            m.setTopik(topik);
            m.setTipeMateri(tipeMateri);
            m.setDeskripsiMateri(deskripsi);

            Pengajar pengajar = new Pengajar();
            pengajar.setIdPengguna(idPengajar);
            m.setPengajar(pengajar);

            boolean berhasil = materiDao.insert(m);
            if (berhasil) {
                System.out.println("Materi " + namaMateri + " berhasil disimpan ke database.");
            } else {
                System.out.println("Gagal menyimpan materi.");
            }
            return berhasil;
        } catch (IllegalArgumentException e) {
            System.out.println("Data materi tidak valid: " + e.getMessage());
            return false;
        }
    }

    public List<Materi> tampilkanMateri() {
        return materiDao.getAll();
    }

    // Overload: tampilkan materi yang cocok dengan kata kunci (nama, topik, atau tipe)
    public List<Materi> tampilkanMateri(String keyword) {
        return materiDao.getAll(keyword);
    }

    public Materi cariMateri(int idMateri) {
        return materiDao.getById(idMateri);
    }

    public boolean ubahMateri(Materi materi) {
        return materi != null && materiDao.update(materi);
    }

    // Overload: ubah dengan data terpisah (dipakai KelolaMateriPanel)
    public boolean ubahMateri(int idMateri, String namaMateri, String topik,
            String tipeMateri, String deskripsi) {
        try {
            Materi m = materiDao.getById(idMateri);
            if (m == null) {
                System.out.println("Materi dengan id " + idMateri + " tidak ditemukan.");
                return false;
            }
            m.setNamaMateri(namaMateri);
            m.setTopik(topik);
            m.setTipeMateri(tipeMateri);
            m.setDeskripsiMateri(deskripsi);
            return materiDao.update(m);
        } catch (IllegalArgumentException e) {
            System.out.println("Data materi tidak valid: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusMateri(int idMateri) {
        return materiDao.delete(idMateri);
    }
}