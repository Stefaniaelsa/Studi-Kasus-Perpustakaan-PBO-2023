package controllers;

import entity.AdminEntity;
import models.AdminModel;
import java.util.List;

public class AdminControllers {
        private AdminModel adminModel;

        public AdminControllers() {
            this.adminModel = new AdminModel();
        }

        public void tambahAdmin(AdminEntity admin) {
            adminModel.tambahAdmin(admin);
        }

        public List <AdminEntity> viewAdmin(){
            return adminModel.lihatDaftarAdmin();
        }

        public AdminEntity login(String username, String password) {
            return adminModel.login(username, password);
        }
    }

