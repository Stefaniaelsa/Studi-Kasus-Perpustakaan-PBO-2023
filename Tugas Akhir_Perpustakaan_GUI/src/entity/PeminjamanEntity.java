package entity;

import java.util.Date;

public class PeminjamanEntity extends PengunjungEntity {
    private String idPeminjaman;
    private BukuEntity buku;
    private Date tanggalPinjam;
    private Date tanggalKembali;
    private double biaya;

    public PeminjamanEntity(String nama, String alamat, String  gender, String idPeminjaman, BukuEntity buku, Date tanggalPinjam, Date tanggalKembali, double biaya) {
        super(nama, alamat, gender);
        this.idPeminjaman = idPeminjaman;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.biaya = biaya;
    }

    public String getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public BukuEntity getBuku() {
        return buku;
    }

    public void setBuku(BukuEntity buku) {
        this.buku = buku;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public double getBiaya() {
        return biaya;
    }

    public void setBiaya(double biaya) {
        this.biaya = biaya;
    }


}
