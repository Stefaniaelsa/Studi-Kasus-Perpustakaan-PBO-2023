package controllers;
import entity.PeminjamanEntity;
import models.PeminjamanModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class PeminjamanControllers {
    private PeminjamanModel peminjamanModel;

    public PeminjamanControllers() {
        this.peminjamanModel = new PeminjamanModel();
    }


    public void tambahPeminjaman(PeminjamanEntity peminjaman) {
        peminjamanModel.tambahPeminjaman(peminjaman);
    }

    public List<PeminjamanEntity> viewPeminjaman() {
        return peminjamanModel.lihatDaftarPeminjaman();
    }

    public void kembalikanBuku(PeminjamanEntity peminjaman, Date tanggalKembali, double biaya) {
       peminjamanModel.kembalikanBuku(peminjaman,tanggalKembali,biaya);
    }

    public PeminjamanEntity getPeminjamanByNamaPeminjam(String namaPeminjam) {
        return peminjamanModel.getPeminjaman(namaPeminjam);
    }

//    public Date calculateDueDate(Date tanggalPinjam) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(tanggalPinjam);
//
//        // Menambahkan 3 hari ke tanggal pinjam
//        calendar.add(Calendar.DAY_OF_MONTH, 3);
//
//        return calendar.getTime();
//    }

}


