package model;

import java.time.LocalDate;

public class Pengajar extends Pengguna {

    public static final String AKTIF = "Aktif";
    public static final String NONAKTIF = "Nonaktif";

    protected String keahlian;
    protected String statusPengajar;

    public Pengajar() {
        super("Pengajar");
        this.statusPengajar = AKTIF;
    }

    public Pengajar(int idPengguna, String nama, String email, String username,
                    String password, String jenisKelamin, LocalDate tanggalLahir, String role,
                    String keahlian, String statusPengajar) {
        super(idPengguna, nama, email, username, password, jenisKelamin, tanggalLahir, role);
        setKeahlian(keahlian);
        setStatusPengajar(statusPengajar);
    }

    public Pengajar(int idPengguna, String nama, String email, String username,
                    String password, String jenisKelamin, LocalDate tanggalLahir,
                    String keahlian) {
        this(idPengguna, nama, email, username, password, jenisKelamin,
                tanggalLahir, "Pengajar", keahlian, AKTIF);
    }

    public boolean isAktif() {
        return AKTIF.equalsIgnoreCase(statusPengajar);
    }

    @Override
    public String toString() {
        return "Pengajar{keahlian=" + getKeahlian()
                + ", status=" + getStatusPengajar() + "}";
    }

    public String getKeahlian() {
        return (keahlian != null) ? keahlian : "-";
    }

    public void setKeahlian(String keahlian) {
        if (keahlian == null || keahlian.trim().isEmpty()) {
            throw new IllegalArgumentException("Keahlian tidak boleh kosong");
        }
        this.keahlian = keahlian.trim();
    }

    public void setKeahlian(String keahlian1, String keahlian2) {
        if (keahlian1 == null || keahlian1.trim().isEmpty()
                || keahlian2 == null || keahlian2.trim().isEmpty()) {
            throw new IllegalArgumentException("Keahlian tidak boleh kosong");
        }
        this.keahlian = keahlian1.trim() + ", " + keahlian2.trim();
    }

    public String getStatusPengajar() {
        return (statusPengajar != null) ? statusPengajar : NONAKTIF;
    }

    public void setStatusPengajar(String statusPengajar) {
        if (statusPengajar == null
                || !(statusPengajar.equalsIgnoreCase(AKTIF) || statusPengajar.equalsIgnoreCase(NONAKTIF))) {
            throw new IllegalArgumentException("Status harus 'Aktif' atau 'Nonaktif'");
        }
        this.statusPengajar = statusPengajar;
    }

    @Override
    public String getHakAkses() {
        return "Kelola kelas dan materi";
    }
}