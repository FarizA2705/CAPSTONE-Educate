package model;


public class PaketSoal {

    private int idPaketSoal;
    private String namaPaketSoal;
    private String tingkatKesulitan;


    public PaketSoal() {
        this.namaPaketSoal = "";
        this.tingkatKesulitan = "";
    }

    public PaketSoal(String namaPaketSoal, String tingkatKesulitan) {
        setNamaPaketSoal(namaPaketSoal);
        setTingkatKesulitan(tingkatKesulitan);
    }


    public PaketSoal(int idPaketSoal, String namaPaketSoal, String tingkatKesulitan) {
        this(namaPaketSoal, tingkatKesulitan);
        setIdPaketSoal(idPaketSoal);
    }

    public int getIdPaketSoal() {
        return idPaketSoal;
    }

    public void setIdPaketSoal(int idPaketSoal) {
        if (idPaketSoal < 0) {
            throw new IllegalArgumentException("ID paket soal tidak boleh negatif");
        }
        this.idPaketSoal = idPaketSoal;
    }

    public String getNamaPaketSoal() {
        if (namaPaketSoal == null) {
            return "";
        }
        return namaPaketSoal;
    }

    public void setNamaPaketSoal(String namaPaketSoal) {
        if (namaPaketSoal == null || namaPaketSoal.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama paket soal tidak boleh kosong");
        }
        this.namaPaketSoal = namaPaketSoal.trim();
    }

    public String getTingkatKesulitan() {
        if (tingkatKesulitan == null) {
            return "";
        }
        return tingkatKesulitan;
    }

    public void setTingkatKesulitan(String tingkatKesulitan) {
        if (tingkatKesulitan == null || tingkatKesulitan.trim().isEmpty()) {
            throw new IllegalArgumentException("Tingkat kesulitan tidak boleh kosong");
        }
        this.tingkatKesulitan = tingkatKesulitan.trim();
    }

    @Override
    public String toString() {
        return "PaketSoal{id=" + idPaketSoal
                + ", nama=" + getNamaPaketSoal()
                + ", tingkatKesulitan=" + getTingkatKesulitan() + "}";
    }
}