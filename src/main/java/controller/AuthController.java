package controller;

import dao.PenggunaDao;
import model.Pengguna;

public class AuthController {
    private final PenggunaDao dao = new PenggunaDao();

    // static supaya tetap tersimpan walau AuthController dibuat baru (new AuthController())
    private static Pengguna penggunaLogin;

    public Pengguna prosesLogin(String username, String password) {
        if (username == null || password == null
                || username.trim().isEmpty() || password.trim().isEmpty()) {
            System.out.println("Username dan Password tidak boleh kosong!");
            return null;
        }

        Pengguna user = dao.login(username.trim(), password);
        if (user != null) {
            penggunaLogin = user;
            System.out.println("Login Berhasil! Selamat datang, " + user.getNama()
                    + " (" + user.getRole() + ")");
        } else {
            System.out.println("Login Gagal! Username atau Password salah.");
        }
        return user;
    }
    
   public String prosesRegistrasi(String nama, String username, String email,
                               String password, String jenisKelamin,
                               String tanggalLahir, String kelas) {
    try {
        model.Pelajar p = new model.Pelajar();
        p.setNama(nama);
        p.setUsername(username);
        p.setEmail(email);
        p.setPassword(password);
        // combo berisi "Laki-Laki", konstanta model "Laki-laki": samakan
        p.setJenisKelamin(jenisKelamin.equalsIgnoreCase("Laki-Laki")
                ? model.Pengguna.LAKI_LAKI : model.Pengguna.PEREMPUAN);
        p.setTanggalLahir(tanggalLahir);
        p.setKelas(Integer.parseInt(kelas));

        if (dao.sudahAda(p.getUsername(), p.getEmail())) {
            return "Username atau email sudah dipakai";
        }
        return dao.insert(p) ? null : "Gagal menyimpan ke database";
    } catch (NumberFormatException e) {
        return "Kelas tidak valid";
    } catch (IllegalArgumentException e) {
        return e.getMessage();
    }
}

    public void logout() {
        if (penggunaLogin != null) {
            System.out.println("Logout berhasil. Sampai jumpa, " + penggunaLogin.getNama());
        }
        penggunaLogin = null;
    }

    public Pengguna getPenggunaLogin() {
        return penggunaLogin;
    }

    public boolean isSudahLogin() {
        return penggunaLogin != null;
    }
}