package views;

import controllers.BukuControllers;
import entity.BukuEntity;

import java.util.List;
import java.util.Scanner;

public class BukuView {
    private BukuControllers bukuController;

    public BukuView () {
        this.bukuController = new BukuControllers();
    }

    public void tambahBuku() {
        System.out.println("====== Tambah Buku ======");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan pengarang buku: ");
        String pengarang = scanner.nextLine();
        System.out.print("Masukkan penerbit buku: ");
        String penerbit = scanner.nextLine();
        System.out.print("Masukkan jumlah halaman: ");
        int jumlahHalaman = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan posisi rak buku: ");
        String posisiRak = scanner.nextLine();
        System.out.println("===============================");

        BukuEntity newBuku = new BukuEntity(judul, pengarang, penerbit, jumlahHalaman, posisiRak);
        bukuController.tambahBuku(newBuku);
        System.out.println("Buku berhasil ditambahkan.");
    }

    public void viewAllBuku() {
        System.out.println("=== Daftar Buku ===");
        List<BukuEntity> daftarBuku = bukuController.viewBuku();

        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku yang tersedia.");
        } else {
            for (BukuEntity buku : daftarBuku) {
                System.out.println("===== Informasi Buku =====");
                System.out.println("Judul Buku: " + buku.getJudul());
                System.out.println("Pengarang: " + buku.getPengarang());
                System.out.println("Penerbit: " + buku.getPenerbit());
                System.out.println("Jumalah Halaman: " + buku.getJumlahHalaman());
                System.out.println("Stok Buku: " + buku.getStokBuku());
                System.out.println("Posisi Rak: " + buku.getPosisiRak());
                System.out.println("--------------------------");
            }
        }
    }


    public void cariBuku() {
        System.out.println("=== Cari Buku ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cari Berdasarkan Penerbit/Pengarang/Judul : ");
        String keyword = scanner.nextLine();

        List<BukuEntity> hasilPencarian = bukuController.cariBuku(keyword);
        if (hasilPencarian != null && !hasilPencarian.isEmpty()) {
            System.out.println("=== Hasil Pencarian ===");
            for (BukuEntity buku : hasilPencarian) {
                System.out.println("===== Informasi Buku =====");
                System.out.println("Judul Buku: " + buku.getJudul());
                System.out.println("Pengarang: " + buku.getPengarang());
                System.out.println("Penerbit: " + buku.getPenerbit());
                System.out.println("Jumalah Halaman: " + buku.getJumlahHalaman());
                System.out.println("Stok Buku: " + buku.getStokBuku());
                System.out.println("Posisi Rak: " + buku.getPosisiRak());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    public void updateBuku() {
        System.out.println("=== Update Buku ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku yang akan diupdate: ");
        String judul = scanner.nextLine();

        BukuEntity bukuToUpdate = bukuController.getBukuByTitle(judul);
        if (bukuToUpdate != null) {
            System.out.print("Masukkan stok baru: ");
            int stok = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Masukkan posisi rak baru: ");
            String posisiRak = scanner.nextLine();

            bukuToUpdate.setStokBuku(stok);
            bukuToUpdate.setPosisiRak(posisiRak);

            bukuController.updateBuku(bukuToUpdate);
            System.out.println("Buku berhasil diupdate.");
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    public void hapusBuku() {
        System.out.println("=== Hapus Buku ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku yang akan dihapus: ");
        String judul = scanner.nextLine();

        BukuEntity bukuToDelete = bukuController.getBukuByTitle(judul);
        if (bukuToDelete != null) {
            bukuController.hapusBuku(bukuToDelete);
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }
}