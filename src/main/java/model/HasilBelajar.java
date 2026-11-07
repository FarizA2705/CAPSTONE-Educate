package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HasilBelajar {
    private int idHasil;
    private LocalDate tanggalPengerjaan;
    private int totalSoal;
    private int jumlahBenar;
    private int skor;
    private Pelajar pelajar;
    private int pelajarIdPengguna;
    private List<DetailLatihan> daftarDetail = new ArrayList<>();

    public HasilBelajar() {
    }


    public HasilBelajar(LocalDate tanggalPengerjaan, int totalSoal,
                        int jumlahBenar, int skor, Pelajar pelajar) {
        setTanggalPengerjaan(tanggalPengerjaan);
        setTotalSoal(totalSoal);      
        setJumlahBenar(jumlahBenar);
        setSkor(skor);
        setPelajar(pelajar);
    }

    public HasilBelajar(int idHasil, LocalDate tanggalPengerjaan, int totalSoal,
                        int jumlahBenar, int skor, Pelajar pelajar) {
        this(tanggalPengerjaan, totalSoal, jumlahBenar, skor, pelajar);
        setIdHasil(idHasil);
    }

    public int getIdHasil() {
        return idHasil;
    }

    public void setIdHasil(int idHasil) {
        if (idHasil < 0) {
            throw new IllegalArgumentException("ID hasil tidak boleh negatif");
        }
        this.idHasil = idHasil;
    }

    public LocalDate getTanggalPengerjaan() {
        if (tanggalPengerjaan == null) {
            return LocalDate.now();
        }
        return tanggalPengerjaan;
    }

    public void setTanggalPengerjaan(LocalDate tanggalPengerjaan) {
        if (tanggalPengerjaan == null) {
            throw new IllegalArgumentException("Tanggal pengerjaan tidak boleh null");
        }
        if (tanggalPengerjaan.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal pengerjaan tidak boleh di masa depan");
        }
        this.tanggalPengerjaan = tanggalPengerjaan;
    }

    public int getTotalSoal() {
        return totalSoal;
    }

    public void setTotalSoal(int totalSoal) {
        if (totalSoal < 0) {
            throw new IllegalArgumentException("Total soal tidak boleh negatif");
        }
        if (totalSoal < jumlahBenar) {
            throw new IllegalArgumentException("Total soal tidak boleh kurang dari jumlah benar");
        }
        this.totalSoal = totalSoal;
    }

    public int getJumlahBenar() {
        return jumlahBenar;
    }

    public void setJumlahBenar(int jumlahBenar) {
        if (jumlahBenar < 0) {
            throw new IllegalArgumentException("Jumlah benar tidak boleh negatif");
        }
        if (jumlahBenar > totalSoal) {
            throw new IllegalArgumentException("Jumlah benar tidak boleh melebihi total soal");
        }
        this.jumlahBenar = jumlahBenar;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        if (skor < 0 || skor > 100) {
            throw new IllegalArgumentException("Skor harus di antara 0 sampai 100");
        }
        this.skor = skor;
    }

    public void setSkor() {
        if (totalSoal == 0) {
            this.skor = 0;
        } else {
            this.skor = (int) Math.round((jumlahBenar * 100.0) / totalSoal);
        }
    }

    public Pelajar getPelajar() {
        return pelajar;
    }

    public void setPelajar(Pelajar pelajar) {
        if (pelajar == null) {
            throw new IllegalArgumentException("Pelajar tidak boleh null");
        }
        this.pelajar = pelajar;
    }

    public int getPelajarIdPengguna() {
        return pelajarIdPengguna;
    }

    public void setPelajarIdPengguna(int pelajarIdPengguna) {
        if (pelajarIdPengguna < 0) {
            throw new IllegalArgumentException("ID pelajar tidak boleh negatif");
        }
        this.pelajarIdPengguna = pelajarIdPengguna;
    }

    public List<DetailLatihan> getDaftarDetail() {
        if (daftarDetail == null) {
            daftarDetail = new ArrayList<>();
        }
        return daftarDetail;
    }

    public void setDaftarDetail(List<DetailLatihan> daftarDetail) {
        if (daftarDetail == null) {
            throw new IllegalArgumentException("Daftar detail tidak boleh null");
        }
        this.daftarDetail = daftarDetail;
    }

    public void tambahDetail(DetailLatihan detail) {
        if (detail == null) {
            throw new IllegalArgumentException("Detail latihan tidak boleh null");
        }
        getDaftarDetail().add(detail);
    }

    @Override
    public String toString() {
        return "HasilBelajar{id=" + idHasil
                + ", tanggal=" + getTanggalPengerjaan()
                + ", totalSoal=" + totalSoal
                + ", jumlahBenar=" + jumlahBenar
                + ", skor=" + skor + "}";
    }
}