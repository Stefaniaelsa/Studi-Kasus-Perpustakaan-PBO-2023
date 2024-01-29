package views;

import java.util.Scanner;

public class App {
    private AdminView adminView;
    private BukuView bukuView;
    private PeminjamanView peminjamanView;
    public App(AdminView adminView, BukuView bukuView, PeminjamanView peminjamanView){
        this.adminView = adminView;
        this.bukuView = bukuView;
        this.peminjamanView = peminjamanView;

    }

    public void TampilanAwal (){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Masuk Sebagai:");
            System.out.println("1. Admin");
            System.out.println("2. Pengunjung");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    MenuPengunjung();
                    break;
                case 0:
                    System.out.println("Keluar dari Aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (choice != 4);
    }

    public void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        int Adminpil;

        do {
            System.out.println("Menu Admin:");
            System.out.println("1. Tambah Admin");
            System.out.println("2. Login");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");
            Adminpil = scanner.nextInt();

            switch (Adminpil) {
                case 1:
                    adminView.tambahAdmin();
                    break;
                case 2:
                    adminView.loginAdmin();
                    subMenuAdmin();
                    break;
                case 0:
                    System.out.println("Keluar dari Menu Admin.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (Adminpil != 0);
    }

    public void MenuPengunjung (){
        Scanner input = new Scanner(System.in);
        int pengunjung;

        do {
            System.out.println("Menu Pengunjung: ");
            System.out.println("1. Lihat Buku");
            System.out.println("2. Cari Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("0. Keluar");
            System.out.println("Masukan pilihan anda: ");
            pengunjung = input.nextInt();

            switch (pengunjung){
                case 1:
                    bukuView.viewAllBuku();
                    break;
                case 2:
                    bukuView.cariBuku();
                    break;
                case 3:
                    peminjamanView.tambahPeminjaman();
                    break;
                case 4:
                    peminjamanView.kembalikanBuku();
                    break;
                case 0:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid");
                    break;
            }
        }while (pengunjung != 0);
    }

//    public void menuPeminjaman() {
//        Scanner input = new Scanner(System.in);
//        int peminjaman;
//        do {
//            System.out.println("Menu Peminjaman:");
//            System.out.println("1. Tambah Peminjaman");
//            System.out.println("2. Lihat Daftar Peminjaman");
//            System.out.println("3. Kembalikan Buku");
//            System.out.println("4. Keluar");
//            System.out.print("Pilih menu (1/2/3/4): ");
//            peminjaman = input.nextInt();
//            input.nextLine();
//            switch (peminjaman) {
//                case 1:
//                    peminjamanView.tambahPeminjaman();
//                    break;
//                case 2:
//                    peminjamanView.lihatDaftarPeminjaman();
//                    break;
//                case 3:
//                    peminjamanView.kembalikanBuku();
//                    break;
//                case 4:
//                    System.out.println("Keluar dari Menu Peminjaman.");
//                    break;
//                default:
//                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
//                    break;
//            }
//        } while (peminjaman != 4);
//    }

    private void subMenuAdmin (){
        Scanner input = new Scanner(System.in);
        int subAdmin;

        do{
            System.out.println("Menu Admin:");
            System.out.println("1. Menu CRUD Buku");
            System.out.println("2. Lihat Daftar Peminjaman");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");
            subAdmin = input.nextInt();
            switch (subAdmin){
                case 1:
                    menuBuku();
                    break;
                case 2:
                    peminjamanView.lihatDaftarPeminjaman();
                case 0:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid/Tidak Sesuai");
            }
        }while (subAdmin != 0);
    }
    private void menuBuku() {
        Scanner input = new Scanner(System.in);
        int buku;
        do {
            System.out.println("Menu CRUD Buku:");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Lihat Daftar Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Update Buku");
            System.out.println("5. Hapus Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");
            buku = input.nextInt();

            switch (buku) {
                case 1:
                    bukuView.tambahBuku();
                    break;
                case 2:
                    bukuView.viewAllBuku();
                    break;
                case 3:
                    bukuView.cariBuku();
                    break;
                case 4:
                    bukuView.updateBuku();
                    break;
                case 5:
                    bukuView.hapusBuku();
                    break;
                case 0:
                    System.out.println("Keluar dari Menu Buku.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (buku!= 0);
    }

//        private void AdminMenu (){
//            Scanner scanner = new Scanner(System.in);
//            int adminpil;
//
//            do {
//                System.out.println("Admin Menu");
//                System.out.println("1. T");
//                System.out.println("2. Pengunjung");
//                System.out.println("0. Keluar");
//                System.out.print("Pilih menu (1/2/3/4): ");
//                adminpil = scanner.nextInt();
//                scanner.nextLine();
//            }
//        }

}


