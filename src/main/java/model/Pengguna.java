package model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pengguna {

    public static final String LAKI_LAKI = "Laki-laki";
    public static final String PEREMPUAN = "Perempuan";

    protected int idPengguna;
    protected String nama;
    protected String email;
    protected String username;
    protected String password;
    protected String jenisKelamin;
    protected LocalDate tanggalLahir;
    protected String role;

    public Pengguna() {
    }

    protected Pengguna(String role) {
        setRole(role);
    }

    public Pengguna(int idPengguna, String nama, String email, String username,
                    String password, String jenisKelamin, LocalDate tanggalLahir, String role) {
        setIdPengguna(idPengguna);
        setNama(nama);
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setJenisKelamin(jenisKelamin);
        setTanggalLahir(tanggalLahir);
        setRole(role);
    }


    public abstract String getHakAkses();


    public boolean login(String username, String password) {
        return this.username != null && this.password != null
                && this.username.equals(username)
                && this.password.equals(password);
    }

    public boolean login(String email, String password, boolean pakaiEmail) {
        if (!pakaiEmail) {
            return login(email, password);
        }
        return this.email != null && this.password != null
                && this.email.equalsIgnoreCase(email)
                && this.password.equals(password);
    }

    public int hitungUsia() {
        if (tanggalLahir == null) {
            return 0;
        }
        return Period.between(tanggalLahir, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "Pengguna{id=" + idPengguna + ", nama=" + getNama()
                + ", username=" + getUsername() + ", role=" + getRole() + "}";
    }


    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        if (idPengguna < 0) {
            throw new IllegalArgumentException("ID pengguna tidak boleh negatif");
        }
        this.idPengguna = idPengguna;
    }

    public String getNama() {
        return (nama != null) ? nama : "-";
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        this.nama = nama.trim();
    }

    public String getEmail() {
        return (email != null) ? email : "-";
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new IllegalArgumentException("Format email tidak valid");
        }
        this.email = email.trim();
    }

    public String getUsername() {
        return (username != null) ? username : "-";
    }

    public void setUsername(String username) {
        if (username == null || username.trim().length() < 4) {
            throw new IllegalArgumentException("Username minimal 4 karakter");
        }
        this.username = username.trim();
    }

    public String getPassword() {
        if (password == null) {
            throw new IllegalStateException("Password belum diset");
        }
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password minimal 6 karakter");
        }
        this.password = password;
    }

    public String getJenisKelamin() {
        return (jenisKelamin != null) ? jenisKelamin : "-";
    }

    public void setJenisKelamin(String jenisKelamin) {
        if (jenisKelamin == null
                || !(jenisKelamin.equalsIgnoreCase(LAKI_LAKI) || jenisKelamin.equalsIgnoreCase(PEREMPUAN))) {
            throw new IllegalArgumentException("Jenis kelamin harus 'Laki-laki' atau 'Perempuan'");
        }
        this.jenisKelamin = jenisKelamin;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        if (tanggalLahir == null) {
            throw new IllegalArgumentException("Tanggal lahir tidak boleh kosong");
        }
        if (tanggalLahir.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal lahir tidak boleh di masa depan");
        }
        this.tanggalLahir = tanggalLahir;
    }

    public void setTanggalLahir(String tanggal) {
        try {
            setTanggalLahir(LocalDate.parse(tanggal));
        } catch (java.time.format.DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Format tanggal harus yyyy-MM-dd");
        }
    }

    public String getRole() {
        return (role != null) ? role : "-";
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role tidak boleh kosong");
        }
        this.role = role.trim();
    }
}