package models;

import entity.BukuEntity;
import entity.PeminjamanEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeminjamanModel {
    private static List<PeminjamanEntity> daftarPeminjaman = new ArrayList<>();

    public static void tambahPeminjaman(PeminjamanEntity peminjaman) {
        daftarPeminjaman.add(peminjaman);
    }

    public static List<PeminjamanEntity> lihatDaftarPeminjaman() {
        return daftarPeminjaman;
    }


    public static PeminjamanEntity getPeminjaman(String namaPeminjam) {
        for (PeminjamanEntity peminjamanentity : daftarPeminjaman) {
            if (peminjamanentity.getNama().equals(namaPeminjam)) {
                return peminjamanentity;
            }
        }
        return null;
    }


    public static void kembalikanBuku(PeminjamanEntity peminjaman, Date tanggalKembali, double biaya) {
        peminjaman.setTanggalKembali(tanggalKembali);
        peminjaman.setBiaya(biaya);

        BukuEntity buku = peminjaman.getBuku();
        buku.setStokBuku(buku.getStokBuku() + 1);

        daftarPeminjaman.remove(peminjaman);
    }
}
