package model;



public class Materi {
    private int idMateri;
    private String namaMateri;
    private String topik;
    private String tipeMateri;
    private String deskripsiMateri;
    private Pengajar pengajar;

    public Materi() {
        this.namaMateri = "";
        this.topik = "";
        this.tipeMateri = "";
        this.deskripsiMateri = "";
    }

    public Materi(String namaMateri, String topik, String tipeMateri, String deskripsiMateri, Pengajar pengajar) {
        setNamaMateri(namaMateri);
        setTopik(topik);
        setTipeMateri(tipeMateri);
        setDeskripsiMateri(deskripsiMateri);
        setPengajar(pengajar);
    }


    public Materi(int idMateri, String namaMateri, String topik, String tipeMateri, String deskripsiMateri, Pengajar pengajar) {
        this(namaMateri, topik, tipeMateri, deskripsiMateri, pengajar);
        setIdMateri(idMateri);
    }

    public int getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(int idMateri) {
        if (idMateri < 0) {
            throw new IllegalArgumentException("ID materi tidak boleh negatif");
        }
        this.idMateri = idMateri;
    }

    public String getNamaMateri() {
        if (namaMateri == null) {
            return "";
        }
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        if (namaMateri == null || namaMateri.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama materi tidak boleh kosong");
        }
        this.namaMateri = namaMateri.trim();
    }

    public String getTopik() {
        if (topik == null) {
            return "";
        }
        return topik;
    }

    public void setTopik(String topik) {
        if (topik == null || topik.trim().isEmpty()) {
            throw new IllegalArgumentException("Topik tidak boleh kosong");
        }
        this.topik = topik.trim();
    }

    public String getTipeMateri() {
        if (tipeMateri == null) {
            return "";
        }
        return tipeMateri;
    }

    public void setTipeMateri(String tipeMateri) {
        if (tipeMateri == null || tipeMateri.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipe materi tidak boleh kosong");
        }
        this.tipeMateri = tipeMateri.trim();
    }

    public String getDeskripsiMateri() {
        if (deskripsiMateri == null) {
            return "";
        }
        return deskripsiMateri;
    }

    public void setDeskripsiMateri(String deskripsiMateri) {
        if (deskripsiMateri == null || deskripsiMateri.trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi materi tidak boleh kosong");
        }
        this.deskripsiMateri = deskripsiMateri.trim();
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


    @Override
    public String toString() {
        return "Materi{id=" + idMateri
                + ", nama=" + getNamaMateri()
                + ", topik=" + getTopik()
                + ", tipe=" + getTipeMateri() + "}";
    }
}