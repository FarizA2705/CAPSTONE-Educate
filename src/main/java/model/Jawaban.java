package model;

/**
 *
 * @author HP VICTUS
 */
public class Jawaban {
    public static final String benar = "Benar";
    public static final String salah = "Salah";

    private int idJawaban;
    private int idLatihan;
    private int idPelajar;
    private String pilihanJawaban;
    private String kunciJawaban;
    private String opsi;
    private String status;
    private LatihanSoal latihanSoal;

    public Jawaban() {
        this.pilihanJawaban = "";
        this.kunciJawaban = "";
        this.opsi = "";
        this.status = salah;
    }

    public Jawaban(int idLatihan, int idPelajar, String pilihanJawaban, String kunciJawaban,
                   String opsi, String status, LatihanSoal latihanSoal) {
        setIdLatihan(idLatihan);
        setIdPelajar(idPelajar);
        setPilihanJawaban(pilihanJawaban);
        setKunciJawaban(kunciJawaban);
        setOpsi(opsi);
        setStatus(status);
        setLatihanSoal(latihanSoal);
    }

    public Jawaban(int idJawaban, int idLatihan, int idPelajar, String pilihanJawaban,
                   String kunciJawaban, String opsi, String status, LatihanSoal latihanSoal) {
        this(idLatihan, idPelajar, pilihanJawaban, kunciJawaban, opsi, status, latihanSoal);
        setIdJawaban(idJawaban);
    }

    public int getIdJawaban() {
        return idJawaban;
    }

    public void setIdJawaban(int idJawaban) {
        if (idJawaban < 0) {
            throw new IllegalArgumentException("ID jawaban tidak boleh negatif");
        }
        this.idJawaban = idJawaban;
    }

    public int getIdLatihan() {
        return idLatihan;
    }

    public void setIdLatihan(int idLatihan) {
        if (idLatihan < 0) {
            throw new IllegalArgumentException("ID latihan tidak boleh negatif");
        }
        this.idLatihan = idLatihan;
    }

    public int getIdPelajar() {
        return idPelajar;
    }

    public void setIdPelajar(int idPelajar) {
        if (idPelajar < 0) {
            throw new IllegalArgumentException("ID pelajar tidak boleh negatif");
        }
        this.idPelajar = idPelajar;
    }

    public String getPilihanJawaban() {
        if (pilihanJawaban == null) {
            return "";
        }
        return pilihanJawaban;
    }

    public void setPilihanJawaban(String pilihanJawaban) {
        if (pilihanJawaban == null || pilihanJawaban.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilihan jawaban tidak boleh kosong");
        }
        this.pilihanJawaban = pilihanJawaban.trim();
    }

    public String getKunciJawaban() {
        if (kunciJawaban == null) {
            return "";
        }
        return kunciJawaban;
    }

    public void setKunciJawaban(String kunciJawaban) {
        if (kunciJawaban == null || kunciJawaban.trim().isEmpty()) {
            throw new IllegalArgumentException("Kunci jawaban tidak boleh kosong");
        }
        this.kunciJawaban = kunciJawaban.trim();
    }

    public String getOpsi() {
        if (opsi == null) {
            return "";
        }
        return opsi;
    }

    public void setOpsi(String opsi) {
        if (opsi == null || opsi.trim().isEmpty()) {
            throw new IllegalArgumentException("Opsi tidak boleh kosong");
        }
        this.opsi = opsi.trim();
    }

    public String getStatus() {
        if (status == null) {
            return salah;
        }
        return status;
    }

    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status tidak boleh kosong");
        }
        String s = status.trim();
        if (s.equalsIgnoreCase(benar)) {
            this.status = benar;
        } else if (s.equalsIgnoreCase(salah)) {
            this.status = salah;
        } else {
            throw new IllegalArgumentException("Status harus '" + benar + "' atau '" + salah + "'");
        }
    }

    public void setStatus(boolean isBenar) {
        this.status = isBenar ? benar : salah;
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

    public boolean isBenar() {
        return benar.equals(status);
    }

    public void periksaJawaban() {
        setStatus(getPilihanJawaban().equalsIgnoreCase(getKunciJawaban()));
    }

    @Override
    public String toString() {
        return "Jawaban{id=" + idJawaban
                + ", pilihan=" + getPilihanJawaban()
                + ", kunci=" + getKunciJawaban()
                + ", status=" + getStatus() + "}";
    }
}