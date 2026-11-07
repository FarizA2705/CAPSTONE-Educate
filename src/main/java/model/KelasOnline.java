package model;

import java.time.LocalDate;
import java.time.LocalTime;


public class KelasOnline {
    private int idKelas;
    private String namaKelas;
    private String deskripsiKelas;
    private LocalDate tanggalKelas;
    private LocalTime waktuMulai;
    private LocalTime waktuSelesai;
    private String linkKelas;
    private Double harga;
    private String statusKelas;
    private int kuotaMaksimal;
    private Pengajar pengajar;

    public KelasOnline() {
        this.namaKelas = "";
        this.deskripsiKelas = "";
        this.linkKelas = "";
        this.harga = 0.0;
        this.statusKelas = "";
    }

    public KelasOnline(String namaKelas, String deskripsiKelas,
                       LocalDate tanggalKelas, LocalTime waktuMulai, LocalTime waktuSelesai,
                       String linkKelas, Double harga, String statusKelas,
                       int kuotaMaksimal, Pengajar pengajar) {
        setNamaKelas(namaKelas);
        setDeskripsiKelas(deskripsiKelas);
        setTanggalKelas(tanggalKelas);
        setJadwal(waktuMulai, waktuSelesai);
        setLinkKelas(linkKelas);
        setHarga(harga);
        setStatusKelas(statusKelas);
        setKuotaMaksimal(kuotaMaksimal);
        setPengajar(pengajar);
    }

    public KelasOnline(int idKelas, String namaKelas, String deskripsiKelas,
                       LocalDate tanggalKelas, LocalTime waktuMulai, LocalTime waktuSelesai,
                       String linkKelas, Double harga, String statusKelas,
                       int kuotaMaksimal, Pengajar pengajar) {
        this(namaKelas, deskripsiKelas, tanggalKelas, waktuMulai, waktuSelesai,
                linkKelas, harga, statusKelas, kuotaMaksimal, pengajar);
        setIdKelas(idKelas);
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        if (idKelas < 0) {
            throw new IllegalArgumentException("ID kelas tidak boleh negatif");
        }
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        if (namaKelas == null) {
            return "";
        }
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        if (namaKelas == null || namaKelas.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kelas tidak boleh kosong");
        }
        this.namaKelas = namaKelas.trim();
    }

    public String getDeskripsiKelas() {
        if (deskripsiKelas == null) {
            return "";
        }
        return deskripsiKelas;
    }

    public void setDeskripsiKelas(String deskripsiKelas) {
        if (deskripsiKelas == null || deskripsiKelas.trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi kelas tidak boleh kosong");
        }
        this.deskripsiKelas = deskripsiKelas.trim();
    }

    public LocalDate getTanggalKelas() {
        return tanggalKelas;
    }

    public void setTanggalKelas(LocalDate tanggalKelas) {
        if (tanggalKelas == null) {
            throw new IllegalArgumentException("Tanggal kelas tidak boleh null");
        }
        this.tanggalKelas = tanggalKelas;
    }

    public LocalTime getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(LocalTime waktuMulai) {
        if (waktuMulai == null) {
            throw new IllegalArgumentException("Waktu mulai tidak boleh null");
        }
        if (waktuSelesai != null && !waktuMulai.isBefore(waktuSelesai)) {
            throw new IllegalArgumentException("Waktu mulai harus sebelum waktu selesai");
        }
        this.waktuMulai = waktuMulai;
    }

    public LocalTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(LocalTime waktuSelesai) {
        if (waktuSelesai == null) {
            throw new IllegalArgumentException("Waktu selesai tidak boleh null");
        }
        if (waktuMulai != null && !waktuSelesai.isAfter(waktuMulai)) {
            throw new IllegalArgumentException("Waktu selesai harus setelah waktu mulai");
        }
        this.waktuSelesai = waktuSelesai;
    }

    public void setJadwal(LocalTime waktuMulai, LocalTime waktuSelesai) {
        if (waktuMulai == null || waktuSelesai == null) {
            throw new IllegalArgumentException("Waktu mulai dan waktu selesai tidak boleh null");
        }
        if (!waktuMulai.isBefore(waktuSelesai)) {
            throw new IllegalArgumentException("Waktu mulai harus sebelum waktu selesai");
        }
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
    }

    public String getLinkKelas() {
        if (linkKelas == null) {
            return "";
        }
        return linkKelas;
    }

    public void setLinkKelas(String linkKelas) {
        if (linkKelas == null || linkKelas.trim().isEmpty()) {
            throw new IllegalArgumentException("Link kelas tidak boleh kosong");
        }
        String link = linkKelas.trim();
        if (!link.startsWith("http://") && !link.startsWith("https://")) {
            throw new IllegalArgumentException("Link kelas harus diawali http:// atau https://");
        }
        this.linkKelas = link;
    }

    public Double getHarga() {
        if (harga == null) {
            return 0.0;
        }
        return harga;
    }

    public void setHarga(Double harga) {
        if (harga == null) {
            throw new IllegalArgumentException("Harga tidak boleh null");
        }
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }
        this.harga = harga;
    }

    public String getStatusKelas() {
        if (statusKelas == null) {
            return "";
        }
        return statusKelas;
    }

    public void setStatusKelas(String statusKelas) {
        if (statusKelas == null || statusKelas.trim().isEmpty()) {
            throw new IllegalArgumentException("Status kelas tidak boleh kosong");
        }
        this.statusKelas = statusKelas.trim();
    }

    public int getKuotaMaksimal() {
        return kuotaMaksimal;
    }

    public void setKuotaMaksimal(int kuotaMaksimal) {
        if (kuotaMaksimal <= 0) {
            throw new IllegalArgumentException("Kuota maksimal harus lebih dari 0");
        }
        this.kuotaMaksimal = kuotaMaksimal;
    }

    public Pengajar getPengajar() {
        return pengajar;
    }

    public void setPengajar(Pengajar pengajar) {
        if (pengajar == null) {
            throw new IllegalArgumentException("Pengajar tidak boleh null");
        }
        this.pengajar = pengajar;
    }

    public boolean isGratis() {
        return getHarga() == 0.0;
    }

    @Override
    public String toString() {
        return "KelasOnline{id=" + idKelas
                + ", nama=" + getNamaKelas()
                + ", tanggal=" + tanggalKelas
                + ", harga=" + getHarga()
                + ", status=" + getStatusKelas() + "}";
    }
}