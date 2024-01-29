package views;
import controllers.AdminControllers;
import entity.AdminEntity;

import java.util.Scanner;
public class AdminView {
        private AdminControllers adminController;
        private Scanner scanner;
        private static int lastAdminId = 0;

        public AdminView() {
            this.adminController = new AdminControllers();
            this.scanner = new Scanner(System.in);
        }

        public void tambahAdmin() {
            System.out.println("=== Tambah Admin ===");

            int id = lastAdminId++;

            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();
            System.out.print("Masukkan nama: ");
            String nama = scanner.nextLine();

            AdminEntity newAdmin = new AdminEntity(username, password, nama, id);
            adminController.tambahAdmin(newAdmin);
            System.out.println("Admin berhasil ditambahkan.");
        }

    public void loginAdmin() {
        Scanner input = new Scanner(System.in);

        boolean loginSuccess = false;
        do {
            System.out.println("Masukkan Username Admin: ");
            String username = input.nextLine();
            System.out.println("Masukkan Password Admin: ");
            String password = input.nextLine();

            AdminEntity admin = adminController.login(username, password);

            if (admin != null) {
                System.out.println("Login berhasil!");
                loginSuccess = true;
                adminController.viewAdmin();
            } else {
                System.out.println("Login gagal. Username atau password salah.");
            }
        } while (!loginSuccess);
    }
}

