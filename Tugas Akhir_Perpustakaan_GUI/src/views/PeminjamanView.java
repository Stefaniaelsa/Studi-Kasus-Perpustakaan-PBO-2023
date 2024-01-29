package views;

import controllers.PeminjamanControllers;
import controllers.BukuControllers;
import entity.BukuEntity;
import entity.PeminjamanEntity;
import models.BukuModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PeminjamanView {
    private PeminjamanControllers peminjamanControllers;
    private BukuControllers bukuController;

    private Scanner scanner;

    public PeminjamanView() {
        this.peminjamanControllers = new PeminjamanControllers();
        this.bukuController = new BukuControllers();
        this.scanner = new Scanner(System.in);
    }
    private static int lastPeminjamanId = 1;
    public void tambahPeminjaman() {
        System.out.println("===== Tambah Peminjaman Buku =====");
        System.out.print("Masukkan Judul Buku: ");
        String judulBuku = scanner.nextLine();

        BukuEntity buku = bukuController.getBukuByTitle(judulBuku);
        if (buku != null) {
            if (buku.getStokBuku() > 0) {
                int idPeminjaman = lastPeminjamanId++;
                System.out.print("Masukkan Nama Peminjam: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan Alamat Peminjam: ");
                String alamat = scanner.nextLine();
                System.out.print("Masukkan Gender Peminjam: ");
                String gender = scanner.nextLine();

                System.out.print("Masukkan Tanggal Pinjam (dd-MM-yyyy): ");
                String strTanggalPinjam = scanner.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tanggalPinjam = dateFormat.parse(strTanggalPinjam);

                    Date tanggalKembali = new Date(tanggalPinjam.getTime() + (3 * 24 * 60 * 60 * 1000));
                    double biaya = 0;

                    buku.setStokBuku(buku.getStokBuku() - 1);

                    PeminjamanEntity peminjaman = new PeminjamanEntity(nama, alamat, gender, String.valueOf(idPeminjaman), buku, tanggalPinjam, tanggalKembali, biaya);
                    peminjamanControllers.tambahPeminjaman(peminjaman);

                    System.out.println("Peminjaman berhasil ditambahkan.");
                } catch (ParseException e) {
                    System.out.println("Format tanggal salah. Peminjaman gagal ditambahkan.");
                }
            } else {
                System.out.println("Stok buku dengan judul '" + judulBuku + "' habis. Peminjaman tidak dapat dilakukan.");
            }
        } else {
            System.out.println("Buku dengan judul '" + judulBuku + "' tidak ditemukan.");
        }
    }

    public void kembalikanBuku() {
        System.out.println("===== Kembalikan Buku =====");
        System.out.print("Masukkan Nama Peminjam: ");
        String namaPeminjam = scanner.nextLine();

        PeminjamanEntity peminjaman = peminjamanControllers.getPeminjamanByNamaPeminjam(namaPeminjam);

        if (peminjaman != null) {
            peminjamanControllers.viewPeminjaman();

            System.out.print("Masukkan Tanggal Kembali (dd-MM-yyyy): ");
            String strTanggalKembali = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date tanggalKembali = dateFormat.parse(strTanggalKembali);

                if (tanggalKembali.after(peminjaman.getTanggalKembali())) {
                    long diffInDays = (tanggalKembali.getTime() - peminjaman.getTanggalKembali().getTime()) / (24 * 60 * 60 * 1000);
                    double biayaDenda = diffInDays * 5000;

                    peminjamanControllers.kembalikanBuku(peminjaman, tanggalKembali, biayaDenda);
                    System.out.println("Buku berhasil dikembalikan dengan denda sebesar Rp." + biayaDenda);
                } else {
                    peminjamanControllers.kembalikanBuku(peminjaman, tanggalKembali, 0);
                    System.out.println("Buku berhasil dikembalikan tanpa denda.");
                }
            } catch (ParseException e) {
                System.out.println("Format tanggal salah. Pengembalian buku gagal.");
            }
        } else {
            System.out.println("Peminjam tidak ditemukan.");
        }
    }
    public void lihatDaftarPeminjaman() {
        List<PeminjamanEntity> daftarPeminjaman = peminjamanControllers.viewPeminjaman();

        System.out.println("Menu Menampilkan Semua Admin");
        System.out.println();

        if (daftarPeminjaman.isEmpty()) {
            System.out.println("Tidak ada peminjaman saat ini.");
        } else {
            boolean adaPeminjamanAktif = false;

            for (PeminjamanEntity pinjam : new ArrayList<>(daftarPeminjaman)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                System.out.println("------------------------------------");
                System.out.println("===== Detail Peminjaman =====");
                System.out.println("------------------------------------");
                System.out.println("Id Peminjaman: " + pinjam.getIdPeminjaman());
                System.out.println("Tanggal Pinjam: " + dateFormat.format(pinjam.getTanggalPinjam()));
                System.out.println("Tanggal Kembali: " + dateFormat.format(pinjam.getTanggalKembali()));
                System.out.println("------------------------------------");
                System.out.println("===== Informasi Peminjam =====");
                System.out.println("------------------------------------");
                System.out.println("Nama Peminjam: " + pinjam.getNama());
                System.out.println("Alamat: " + pinjam.getAlamat());
                System.out.println("Gender: " + pinjam.getGender());
                System.out.println("------------------------------------");
                System.out.println(" ===== Informasi Buku =====");
                System.out.println("Judul Buku: " + pinjam.getBuku().getJudul());
                System.out.println("Pengarang: " + pinjam.getBuku().getPengarang());
                System.out.println("Penerbit: " + pinjam.getBuku().getPenerbit());
                System.out.println("Jumalah Halaman: " + pinjam.getBuku().getJumlahHalaman());
                System.out.println("Stok Buku: " + pinjam.getBuku().getStokBuku());
                System.out.println("Posisi Rak: " + pinjam.getBuku().getPosisiRak());
                System.out.println("------------------------------------");

                // Tambahkan pengecekan jika ada peminjaman yang belum dikembalikan
                if (pinjam.getTanggalKembali().after(new Date())) {
                    adaPeminjamanAktif = true;
                }
            }

            if (!adaPeminjamanAktif) {
                System.out.println("Tidak ada peminjaman yang aktif saat ini.");
            }
        }
    }

}

