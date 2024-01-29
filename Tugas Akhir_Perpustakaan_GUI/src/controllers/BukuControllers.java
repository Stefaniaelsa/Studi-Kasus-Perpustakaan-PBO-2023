package controllers;

import entity.BukuEntity;
import models.BukuModel;
import java.util.ArrayList;
import java.util.List;

public class BukuControllers {
    private BukuModel bukuModel;

    public BukuControllers() {
        this.bukuModel = new BukuModel();
    }

    public void tambahBuku(BukuEntity buku) {
        bukuModel.tambahBuku(buku);
    }

    public List<BukuEntity> viewBuku() {
        return bukuModel.lihatDaftarBuku();
    }

    public List<BukuEntity> cariBuku(String keyword) {
        List<BukuEntity> daftarBuku = bukuModel.lihatDaftarBuku();
        List<BukuEntity> hasilPencarian = new ArrayList<>();

        for (BukuEntity buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(keyword) ||
                    buku.getPenerbit().equalsIgnoreCase(keyword) ||
                    buku.getPengarang().equalsIgnoreCase(keyword)) {
                hasilPencarian.add(buku);
            }
        }
        return hasilPencarian.isEmpty() ? null : hasilPencarian;
    }

    public BukuEntity getBukuByTitle(String title) {
        return BukuModel.getBukuByTitle(title);
    }
    public void updateBuku(BukuEntity buku) {
        bukuModel.updateBuku(buku);
    }

    public void hapusBuku(BukuEntity buku) {
        bukuModel.hapusBuku(buku);
    }
}