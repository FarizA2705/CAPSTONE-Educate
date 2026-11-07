/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TampilanDataDao;
import java.util.List;

/**
 *
 * @author HP VICTUS
 */

public class TampilanDataController {

    private final TampilanDataDao dao = new TampilanDataDao();

    public List<Object[]> tampilkanKelasOnline() {
        return dao.getDataKelasOnline();
    }

    public List<Object[]> tampilkanLatihanSoal() {
        return dao.getDataLatihanSoal();
    }

    public List<Object[]> tampilkanRekomendasi() {
        return dao.getDataRekomendasi();
    }

    public List<Object[]> tampilkanPelajar() {
        return dao.getDataPelajar();
    }

    public List<Object[]> tampilkanHasilBelajar() {
        return dao.getDataHasilBelajar();
    }
}