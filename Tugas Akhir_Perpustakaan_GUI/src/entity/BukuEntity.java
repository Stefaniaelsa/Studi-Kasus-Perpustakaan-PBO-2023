package entity;
public class BukuEntity {
    private String judul;
    private String pengarang;
    private String penerbit;
    private int jumlahHalaman;
    private int stokBuku;
    private String posisiRak;

    public BukuEntity() {

    }

    public BukuEntity(String judul, String pengarang, String penerbit, int jumlahHalaman, String posisiRak) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.jumlahHalaman = jumlahHalaman;
        this.posisiRak = posisiRak;
        this.stokBuku = 0;
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public int getStokBuku() {
        return stokBuku;
    }

    public void setStokBuku(int stokBuku) {
        this.stokBuku = stokBuku;
    }

    public String getPosisiRak() {
        return posisiRak;
    }
    public void setPosisiRak(String posisiRak) {
        this.posisiRak = posisiRak;
    }


}
