package model;

import java.util.ArrayList;
import java.util.List;


public class LatihanSoal {
    private int idLatihanSoal;
    private String pertanyaan;
    private Materi materi;
    private PaketSoal paketSoal;
    private List<Jawaban> daftarJawaban = new ArrayList<>();

    public LatihanSoal() {
        this.pertanyaan = "";
    }

    public LatihanSoal(String pertanyaan, Materi materi, PaketSoal paketSoal) {
        setPertanyaan(pertanyaan);
        setMateri(materi);
        setPaketSoal(paketSoal);
    }

    public LatihanSoal(int idLatihanSoal, String pertanyaan, Materi materi, PaketSoal paketSoal) {
        this(pertanyaan, materi, paketSoal);
        setIdLatihanSoal(idLatihanSoal);
    }

    public int getIdLatihanSoal() {
        return idLatihanSoal;
    }

    public void setIdLatihanSoal(int idLatihanSoal) {
        if (idLatihanSoal < 0) {
            throw new IllegalArgumentException("ID latihan soal tidak boleh negatif");
        }
        this.idLatihanSoal = idLatihanSoal;
    }

    public String getPertanyaan() {
        if (pertanyaan == null) {
            return "";
        }
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        if (pertanyaan == null || pertanyaan.trim().isEmpty()) {
            throw new IllegalArgumentException("Pertanyaan tidak boleh kosong");
        }
        this.pertanyaan = pertanyaan.trim();
    }

    public Materi getMateri() {
        return materi;
    }

    public void setMateri(Materi materi) {
        if (materi == null) {
            throw new IllegalArgumentException("Materi tidak boleh null");
        }
        this.materi = materi;
    }

    public PaketSoal getPaketSoal() {
        return paketSoal;
    }

    public void setPaketSoal(PaketSoal paketSoal) {
        if (paketSoal == null) {
            throw new IllegalArgumentException("Paket soal tidak boleh null");
        }
        this.paketSoal = paketSoal;
    }

    public List<Jawaban> getDaftarJawaban() {
        if (daftarJawaban == null) {
            daftarJawaban = new ArrayList<>();
        }
        return daftarJawaban;
    }

    public void setDaftarJawaban(List<Jawaban> daftarJawaban) {
        if (daftarJawaban == null) {
            throw new IllegalArgumentException("Daftar jawaban tidak boleh null");
        }
        this.daftarJawaban = daftarJawaban;
    }

    public void tambahJawaban(Jawaban jawaban) {
        if (jawaban == null) {
            throw new IllegalArgumentException("Jawaban tidak boleh null");
        }
        getDaftarJawaban().add(jawaban);
    }

    @Override
    public String toString() {
        return "LatihanSoal{id=" + idLatihanSoal
                + ", pertanyaan=" + getPertanyaan() + "}";
    }
}