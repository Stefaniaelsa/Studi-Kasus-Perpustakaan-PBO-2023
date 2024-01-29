package entity;

public class AdminEntity extends User{
    private int id;
    private String nama;


    public AdminEntity(String username, String password, String nama, int id) {
        super(username, password);
        this.nama = nama;
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void ViewAdmin() {
        System.out.println("===============");
        System.out.println("Data Admin ");
        System.out.println("Id Admin: " + getId());
        System.out.println("Nama Admin: " + getNama());
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
        System.out.println("===============");
    }

}
