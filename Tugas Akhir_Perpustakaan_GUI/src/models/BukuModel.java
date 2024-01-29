package models;

import entity.BukuEntity;

import java.util.ArrayList;
import java.util.List;

public class BukuModel {
    private static List<BukuEntity> daftarBuku = new ArrayList<>();


    public static List<BukuEntity> lihatDaftarBuku() {
        return daftarBuku;
    }

    public static void tambahBuku(BukuEntity buku) {
        daftarBuku.add(buku);
    }


    public static BukuEntity getBukuByTitle(String judul) {
        for (BukuEntity buku : daftarBuku) {
            if (buku.getJudul().equals(judul)) {
                return buku;
            }
        }
        return null;
    }

    public static void updateBuku(BukuEntity bukuentity) {
        int index = daftarBuku.indexOf(bukuentity);
        daftarBuku.set(index, bukuentity);
    }

    public static void hapusBuku(BukuEntity buku) {
        daftarBuku.remove(buku);
    }
}
