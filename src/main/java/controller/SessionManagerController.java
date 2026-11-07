/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Pengguna;

public class SessionManagerController {
    private static Pengguna penggunaAktif;
    private SessionManagerController() { }

    public static void masuk(Pengguna pengguna) { penggunaAktif = pengguna; }
    public static void keluar() { penggunaAktif = null; }
    public static Pengguna getPenggunaAktif() { return penggunaAktif; }
    public static boolean sudahLogin() { return penggunaAktif != null; }
}