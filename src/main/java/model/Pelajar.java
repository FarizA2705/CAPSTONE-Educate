package model;

import java.time.LocalDate;

public class Pelajar extends Pengguna {

 
    public static final int KELAS_MIN = 1;
    public static final int KELAS_MAX = 12;

 
    private LocalDate tanggalDaftar;
    private int kelas;

    public Pelajar() {
        super("Pelajar");
        this.tanggalDaftar = LocalDate.now();
        this.kelas = KELAS_MIN;
    }

    public Pelajar(int idPengguna, String nama, String email, String username,
                   String password, String jenisKelamin, LocalDate tanggalLahir,
                   LocalDate tanggalDaftar, int kelas) {
        super(idPengguna, nama, email, username, password, jenisKelamin, tanggalLahir, "Pelajar");
        setTanggalDaftar(tanggalDaftar);
        setKelas(kelas);
    }

 
    @Override
    public String getHakAkses() {
        return "Mengikuti kelas";
    }

    public LocalDate getTanggalDaftar() {
        if (tanggalDaftar == null) {
            return LocalDate.now();
        }
        return tanggalDaftar;
    }

    public void setTanggalDaftar(LocalDate tanggalDaftar) {
        if (tanggalDaftar == null) {
            throw new IllegalArgumentException("Tanggal daftar tidak boleh null");
        }
        if (tanggalDaftar.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal daftar tidak boleh di masa depan");
        }
        this.tanggalDaftar = tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggal) {
        try {
            setTanggalDaftar(LocalDate.parse(tanggal));
        } catch (java.time.format.DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Format tanggal harus yyyy-MM-dd");
        }
    }

    public int getKelas() {
        return kelas;
    }

    public void setKelas(int kelas) {
        if (kelas < KELAS_MIN || kelas > KELAS_MAX) {
            throw new IllegalArgumentException("Kelas harus di antara " + KELAS_MIN + " sampai " + KELAS_MAX);
        }
        this.kelas = kelas;
    }

    @Override
    public String toString() {
        return super.toString() + " Pelajar{tanggalDaftar=" + getTanggalDaftar() + ", kelas=" + kelas + "}";
    }
}