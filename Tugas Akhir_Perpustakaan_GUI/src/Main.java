
import entity.BukuEntity;
import models.BukuModel;
import views.*;
public class Main {
    public static void main(String[] args) {
        AdminView adminView = new AdminView();
        PeminjamanView peminjamanView = new PeminjamanView();
        BukuView bukuView = new BukuView();
        BukuModel bukuModel = new BukuModel();

        App app = new App(adminView, bukuView,peminjamanView);
        BukuEntity buku = new BukuEntity("Kamuu","Putri","Erlangga",30,"A1");
        bukuModel.tambahBuku(buku);

        BukuEntity buku1 = new BukuEntity("Puisi Cinta","Aissy","erlangga",20,"A2");
        bukuModel.tambahBuku(buku1);

        app.TampilanAwal();
    }
}