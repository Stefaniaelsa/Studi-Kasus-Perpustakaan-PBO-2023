package entity;

public class PengunjungEntity {
    private String nama;
    private String gender;
    private String alamat;

        public PengunjungEntity(String nama, String alamat, String gender) {
            this.nama = nama;
            this.alamat = alamat;
            this.gender = gender;
        }

        public String getAlamat() {
            return this.alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getGender() {
            return this.gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNama() {
            return this.nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }
    }

