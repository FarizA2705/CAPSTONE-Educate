package model;

public class Rekomendasi {

    public static final int NILAI_MIN = 0;
    public static final int NILAI_MAX = 100;

    private int idRekomendasi;
    private int nilaiMinimum = NILAI_MIN;
    private int nilaiMaksimum = NILAI_MAX;
    private String isiRekomendasi;
    private Pengajar pengajar;
    private HasilBelajar hasilBelajar;

    public Rekomendasi() {
    }

    public Rekomendasi(int idRekomendasi, int nilaiMinimum, int nilaiMaksimum,
                       String isiRekomendasi, Pengajar pengajar, HasilBelajar hasilBelajar) {
        setIdRekomendasi(idRekomendasi);
        setRentangNilai(nilaiMinimum, nilaiMaksimum);
        setIsiRekomendasi(isiRekomendasi);
        setPengajar(pengajar);
        setHasilBelajar(hasilBelajar);
    }


    public Rekomendasi(int nilaiMinimum, int nilaiMaksimum,
                       String isiRekomendasi, Pengajar pengajar) {
        setRentangNilai(nilaiMinimum, nilaiMaksimum);
        setIsiRekomendasi(isiRekomendasi);
        setPengajar(pengajar);
    }


    public boolean isNilaiDalamRentang(int nilai) {
        return nilai >= nilaiMinimum && nilai <= nilaiMaksimum;
    }


    public boolean isNilaiDalamRentang(double nilai) {
        return nilai >= nilaiMinimum && nilai <= nilaiMaksimum;
    }

    @Override
    public String toString() {
        return "Rekomendasi{id=" + idRekomendasi
                + ", rentang=" + nilaiMinimum + "-" + nilaiMaksimum
                + ", isi=" + getIsiRekomendasi() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rekomendasi other = (Rekomendasi) obj;
        return this.idRekomendasi == other.idRekomendasi;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idRekomendasi);
    }


    public int getIdRekomendasi() {
        return idRekomendasi;
    }

    public void setIdRekomendasi(int idRekomendasi) {
        if (idRekomendasi < 0) {
            throw new IllegalArgumentException("ID rekomendasi tidak boleh negatif");
        }
        this.idRekomendasi = idRekomendasi;
    }

    public int getNilaiMinimum() {
        return nilaiMinimum;
    }

    public void setNilaiMinimum(int nilaiMinimum) {
        if (nilaiMinimum < NILAI_MIN || nilaiMinimum > NILAI_MAX) {
            throw new IllegalArgumentException("Nilai minimum harus " + NILAI_MIN + " - " + NILAI_MAX);
        }
        if (nilaiMinimum > nilaiMaksimum) {
            throw new IllegalArgumentException("Nilai minimum tidak boleh lebih besar dari nilai maksimum");
        }
        this.nilaiMinimum = nilaiMinimum;
    }

    public int getNilaiMaksimum() {
        return nilaiMaksimum;
    }

    public void setNilaiMaksimum(int nilaiMaksimum) {
        if (nilaiMaksimum < NILAI_MIN || nilaiMaksimum > NILAI_MAX) {
            throw new IllegalArgumentException("Nilai maksimum harus " + NILAI_MIN + " - " + NILAI_MAX);
        }
        if (nilaiMaksimum < nilaiMinimum) {
            throw new IllegalArgumentException("Nilai maksimum tidak boleh lebih kecil dari nilai minimum");
        }
        this.nilaiMaksimum = nilaiMaksimum;
    }

    public void setRentangNilai(int nilaiMinimum, int nilaiMaksimum) {
        if (nilaiMinimum < NILAI_MIN || nilaiMaksimum > NILAI_MAX) {
            throw new IllegalArgumentException("Rentang nilai harus " + NILAI_MIN + " - " + NILAI_MAX);
        }
        if (nilaiMinimum > nilaiMaksimum) {
            throw new IllegalArgumentException("Nilai minimum tidak boleh lebih besar dari nilai maksimum");
        }
        this.nilaiMinimum = nilaiMinimum;
        this.nilaiMaksimum = nilaiMaksimum;
    }

    public String getIsiRekomendasi() {
        return (isiRekomendasi != null) ? isiRekomendasi : "-";
    }

    public void setIsiRekomendasi(String isiRekomendasi) {
        if (isiRekomendasi == null || isiRekomendasi.trim().isEmpty()) {
            throw new IllegalArgumentException("Isi rekomendasi tidak boleh kosong");
        }
        this.isiRekomendasi = isiRekomendasi.trim();
    }

    public Pengajar getPengajar() {
        if (pengajar == null) {
            throw new IllegalStateException("Pengajar belum diset");
        }
        return pengajar;
    }

    public void setPengajar(Pengajar pengajar) {
        if (pengajar == null) {
            throw new IllegalArgumentException("Pengajar tidak boleh kosong");
        }
        this.pengajar = pengajar;
    }

    public HasilBelajar getHasilBelajar() {
        if (hasilBelajar == null) {
            throw new IllegalStateException("Hasil belajar belum diset");
        }
        return hasilBelajar;
    }

    public void setHasilBelajar(HasilBelajar hasilBelajar) {
        if (hasilBelajar == null) {
            throw new IllegalArgumentException("Hasil belajar tidak boleh kosong");
        }
        this.hasilBelajar = hasilBelajar;
    }
}