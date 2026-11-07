package model;


public class DetailLatihan {
    public static final String benar = "Benar";
    public static final String salah = "Salah";

    private int idDetailLatihan;
    private String jawabanPelajar;
    private String statusJawaban;
    private LatihanSoal latihanSoal;
    private HasilBelajar hasilBelajar;

    public DetailLatihan() {
        this.jawabanPelajar = "";
        this.statusJawaban = salah;
    }

    public DetailLatihan(String jawabanPelajar, String statusJawaban, LatihanSoal latihanSoal, HasilBelajar hasilBelajar) {
        setJawabanPelajar(jawabanPelajar);
        setStatusJawaban(statusJawaban);
        setLatihanSoal(latihanSoal);
        setHasilBelajar(hasilBelajar);
    }

    public DetailLatihan(int idDetailLatihan, String jawabanPelajar, String statusJawaban, LatihanSoal latihanSoal, HasilBelajar hasilBelajar) {
        this(jawabanPelajar, statusJawaban, latihanSoal, hasilBelajar);
        setIdDetailLatihan(idDetailLatihan);
    }

    public int getIdDetailLatihan() {
        return idDetailLatihan;
    }

    public void setIdDetailLatihan(int idDetailLatihan) {
        if (idDetailLatihan < 0) {
            throw new IllegalArgumentException("ID detail latihan tidak boleh negatif");
        }
        this.idDetailLatihan = idDetailLatihan;
    }

    public String getJawabanPelajar() {
        if (jawabanPelajar == null) {
            return "";
        }
        return jawabanPelajar;
    }

    public void setJawabanPelajar(String jawabanPelajar) {
        if (jawabanPelajar == null || jawabanPelajar.trim().isEmpty()) {
            throw new IllegalArgumentException("Jawaban pelajar tidak boleh kosong");
        }
        this.jawabanPelajar = jawabanPelajar.trim();
    }

    public String getStatusJawaban() {
        if (statusJawaban == null) {
            return salah;
        }
        return statusJawaban;
    }

    public void setStatusJawaban(String statusJawaban) {
        if (statusJawaban == null) {
            throw new IllegalArgumentException("Status jawaban tidak boleh kosong");
        }
        String status = statusJawaban.trim();
        if (status.equalsIgnoreCase(benar)) {
            this.statusJawaban = benar;
        } else if (status.equalsIgnoreCase(salah)) {
            this.statusJawaban = salah;
        } else {
            throw new IllegalArgumentException("Status jawaban harus '" + benar + "' atau '" + salah + "'");
        }
    }

    public void setStatusJawaban(boolean isBenar) {
        this.statusJawaban = isBenar ? benar : salah;
    }

    public boolean isBenar() {
        return benar.equals(statusJawaban);
    }

    public LatihanSoal getLatihanSoal() {
        return latihanSoal;
    }

    public void setLatihanSoal(LatihanSoal latihanSoal) {
        if (latihanSoal == null) {
            throw new IllegalArgumentException("Latihan soal tidak boleh null");
        }
        this.latihanSoal = latihanSoal;
    }

    public HasilBelajar getHasilBelajar() {
        return hasilBelajar;
    }

    public void setHasilBelajar(HasilBelajar hasilBelajar) {
        if (hasilBelajar == null) {
            throw new IllegalArgumentException("Hasil belajar tidak boleh null");
        }
        this.hasilBelajar = hasilBelajar;
    }

    @Override
    public String toString() {
        return "DetailLatihan{id=" + idDetailLatihan
                + ", jawabanPelajar=" + getJawabanPelajar()
                + ", statusJawaban=" + getStatusJawaban() + "}";
    }
}