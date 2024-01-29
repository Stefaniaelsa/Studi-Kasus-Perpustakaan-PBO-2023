package viewsGUI;


import controllers.BukuControllers;
import controllers.PeminjamanControllers;
import entity.BukuEntity;
import entity.PeminjamanEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MenuPengunjungGUI extends JFrame {

    private PeminjamanControllers peminjamanControllers;
    private BukuControllers bukuControllers;
    public MenuPengunjungGUI() {
        this.peminjamanControllers = new PeminjamanControllers();
        this.bukuControllers = new BukuControllers();
        initializeGUI();
    }


    private void initializeGUI() {
        setTitle("Menu Peminjaman");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Selamat datang di Perpustakaan!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(welcomeLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton tambahButton = new JButton("Tambah Peminjaman");
        JButton kembalikanButton = new JButton("Kembalikan Buku");
        JButton viewAllBukuButton = new JButton("View All Buku");
        JButton cariBukuButton = new JButton("Cari Buku");
        JButton logoutButton = new JButton("Logout");

        final int[] lastPeminjamanId = {0};

        viewAllBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAllBukuGUI();
            }
        });

        cariBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CariBukuGUI();
            }
        });

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judulBuku = JOptionPane.showInputDialog("Masukkan Judul Buku:");

                BukuEntity buku = bukuControllers.getBukuByTitle(judulBuku);

                if (buku != null) {
                    if (buku.getStokBuku() > 0) {

                        int idPeminjaman = lastPeminjamanId[0]++;
                        String nama = JOptionPane.showInputDialog("Masukkan Nama Peminjam:");
                        String alamat = JOptionPane.showInputDialog("Masukkan Alamat Peminjam:");
                        String gender = JOptionPane.showInputDialog("Masukkan Gender Peminjam:");

                        // Step 5: Input the borrowing date
                        String strTanggalPinjam = JOptionPane.showInputDialog("Masukkan Tanggal Pinjam (dd-MM-yyyy):");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                        try {
                            Date tanggalPinjam = dateFormat.parse(strTanggalPinjam);

                            Date tanggalKembali = new Date(tanggalPinjam.getTime() + (3 * 24 * 60 * 60 * 1000));
                            double biaya = 0;

                            buku.setStokBuku(buku.getStokBuku() - 1);
                            PeminjamanEntity peminjaman = new PeminjamanEntity(nama, alamat, gender, String.valueOf(idPeminjaman), buku, tanggalPinjam, tanggalKembali, biaya);
                            peminjamanControllers.tambahPeminjaman(peminjaman);

                            JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Peminjaman berhasil ditambahkan.");
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Format tanggal salah. Peminjaman gagal ditambahkan.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Stok buku dengan judul '" + judulBuku + "' habis. Peminjaman tidak dapat dilakukan.");
                    }
                } else {
                    JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Buku dengan judul '" + judulBuku + "' tidak ditemukan.");
                }
            }
        });


        kembalikanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaPeminjam = JOptionPane.showInputDialog("Masukkan Nama Peminjam:");

                PeminjamanEntity peminjaman = peminjamanControllers.getPeminjamanByNamaPeminjam(namaPeminjam);

                if (peminjaman != null) {
                    peminjamanControllers.viewPeminjaman();

                    String strTanggalKembali = JOptionPane.showInputDialog("Masukkan Tanggal Kembali (dd-MM-yyyy):");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    try {
                        Date tanggalKembali = dateFormat.parse(strTanggalKembali);

                        if (tanggalKembali.after(peminjaman.getTanggalKembali())) {
                            long diffInDays = (tanggalKembali.getTime() - peminjaman.getTanggalKembali().getTime()) / (24 * 60 * 60 * 1000);
                            double biayaDenda = diffInDays * 5000;


                            peminjamanControllers.kembalikanBuku(peminjaman, tanggalKembali, biayaDenda);

                            JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Buku berhasil dikembalikan dengan denda sebesar Rp." + biayaDenda);
                        } else {
                            peminjamanControllers.kembalikanBuku(peminjaman, tanggalKembali, 0);

                            JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Buku berhasil dikembalikan tanpa denda.");
                        }
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Format tanggal salah. Pengembalian buku gagal.");
                    }
                } else {
                    JOptionPane.showMessageDialog(MenuPengunjungGUI.this, "Peminjam tidak ditemukan.");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuGUI();
                dispose();
            }
        });
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));


        buttonPanel.add(tambahButton);
        buttonPanel.add(kembalikanButton);
        buttonPanel.add(viewAllBukuButton);
        buttonPanel.add(cariBukuButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setLayout(new FlowLayout());
        add(mainPanel);

        setVisible(true);
    }

}

