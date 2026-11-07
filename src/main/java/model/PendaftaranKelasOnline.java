package model;

import java.time.LocalDate;

public class PendaftaranKelasOnline {
    public static final String lunas = "Lunas";
    public static final String pending = "Pending";

    protected int idPendaftaran;
    protected LocalDate tanggalDaftar;
    protected Double hargaBayar;
    protected String statusPembayaran;
    protected KelasOnline kelasOnline;
    protected Pelajar pelajar;

    public PendaftaranKelasOnline() {
        this.statusPembayaran = pending;
    }


    public PendaftaranKelasOnline(int idPendaftaran, LocalDate tanggalDaftar, Double hargaBayar,
                                  String statusPembayaran, KelasOnline kelasOnline, Pelajar pelajar) {
        setIdPendaftaran(idPendaftaran);
        setTanggalDaftar(tanggalDaftar);
        setHargaBayar(hargaBayar);
        setStatusPembayaran(statusPembayaran);
        setKelasOnline(kelasOnline);
        setPelajar(pelajar);
    }


    public PendaftaranKelasOnline(Double hargaBayar, KelasOnline kelasOnline, Pelajar pelajar) {
        this(0, LocalDate.now(), hargaBayar, pending, kelasOnline, pelajar);
    }


    public double hitungTotalBayar() {
        return getHargaBayar();
    }


    
    public double hitungTotalBayar(double diskonPersen) {
        if (diskonPersen < 0 || diskonPersen > 100) {
            throw new IllegalArgumentException("Diskon harus 0 - 100");
        }
        return getHargaBayar() - (getHargaBayar() * diskonPersen / 100);
    }

    public boolean isLunas() {
        return lunas.equalsIgnoreCase(statusPembayaran);
    }

    
    @Override
    public String toString() {
        return "PendaftaranKelasOnline{id=" + idPendaftaran
                + ", tanggal=" + tanggalDaftar
                + ", harga=" + hargaBayar
                + ", status=" + statusPembayaran + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PendaftaranKelasOnline other = (PendaftaranKelasOnline) obj;
        return this.idPendaftaran == other.idPendaftaran;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idPendaftaran);
    }


    public int getIdPendaftaran() {
        return idPendaftaran;
    }

    public void setIdPendaftaran(int idPendaftaran) {
        if (idPendaftaran < 0) {
            throw new IllegalArgumentException("ID pendaftaran tidak boleh negatif");
        }
        this.idPendaftaran = idPendaftaran;
    }

    public LocalDate getTanggalDaftar() {
        return (tanggalDaftar != null) ? tanggalDaftar : LocalDate.now();
    }

    public void setTanggalDaftar(LocalDate tanggalDaftar) {
        if (tanggalDaftar == null) {
            throw new IllegalArgumentException("Tanggal daftar tidak boleh kosong");
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

    public Double getHargaBayar() {
        return (hargaBayar != null) ? hargaBayar : 0.0;
    }

    public void setHargaBayar(Double hargaBayar) {
        if (hargaBayar == null || hargaBayar < 0) {
            throw new IllegalArgumentException("Harga bayar tidak boleh kosong atau negatif");
        }
        this.hargaBayar = hargaBayar;
    }

    public String getStatusPembayaran() {
        return (statusPembayaran != null) ? statusPembayaran : pending;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        if (statusPembayaran == null
                || !(statusPembayaran.equalsIgnoreCase(lunas) || statusPembayaran.equalsIgnoreCase(pending))) {
            throw new IllegalArgumentException("Status harus 'Lunas' atau 'Pending'");
        }
        this.statusPembayaran = statusPembayaran;
    }

    public KelasOnline getKelasOnline() {
        if (kelasOnline == null) {
            throw new IllegalStateException("Kelas online belum diset");
        }
        return kelasOnline;
    }

    public void setKelasOnline(KelasOnline kelasOnline) {
        if (kelasOnline == null) {
            throw new IllegalArgumentException("Kelas online tidak boleh kosong");
        }
        this.kelasOnline = kelasOnline;
    }

    public Pelajar getPelajar() {
        if (pelajar == null) {
            throw new IllegalStateException("Pelajar belum diset");
        }
        return pelajar;
    }

    public void setPelajar(Pelajar pelajar) {
        if (pelajar == null) {
            throw new IllegalArgumentException("Pelajar tidak boleh kosong");
        }
        this.pelajar = pelajar;
    }
}