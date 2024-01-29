package models;
import entity.AdminEntity;

import java.util.ArrayList;
import java.util.List;
public class AdminModel {
    private static List<AdminEntity> daftarAdmin = new ArrayList<>();

        public AdminModel() {
            AdminEntity admin1 = new AdminEntity("Gress", "1234", "Grace", 1);
            AdminEntity admin = new AdminEntity("Deldel", "4567", "Delyn", 2);

            daftarAdmin.add(admin);
            daftarAdmin.add(admin1);
        }

    public static List<AdminEntity> lihatDaftarAdmin() {
        return daftarAdmin;
    }

    public static void tambahAdmin(AdminEntity admin) {
            daftarAdmin.add(admin);
        }

        public AdminEntity login(String username, String password) {
            for (AdminEntity admin : daftarAdmin) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    return admin;
                }
            }
            return null;
        }
    }

