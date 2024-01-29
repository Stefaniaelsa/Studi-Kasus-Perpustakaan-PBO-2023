package viewsGUI;

import controllers.BukuControllers;
import controllers.PeminjamanControllers;
import entity.AdminEntity;
import entity.BukuEntity;
import entity.PeminjamanEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class MenuAdminGUI extends JFrame {
    private AdminEntity admin;
    private BukuControllers bukuController;
    private PeminjamanControllers peminjamanControllers;

    public MenuAdminGUI(AdminEntity admin) {
        this.admin = admin;
        this.bukuController = new BukuControllers();
        this.peminjamanControllers = new PeminjamanControllers();
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel header dengan label selamat datang
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Selamat datang, " + admin.getNama() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(welcomeLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JButton viewAllBukuButton = new JButton("View All Buku");
        viewAllBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAllBukuGUI();
            }
        });
        buttonPanel.add(viewAllBukuButton);

        JButton cariBukuButton = new JButton("Cari Buku");
        cariBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CariBukuGUI();
            }
        });
        buttonPanel.add(cariBukuButton);

        // Tombol Tambah Buku
        JButton tambahBukuButton = new JButton("Tambah Buku");
        tambahBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel judulLabel = new JLabel("Judul Buku: ");
                JTextField judulField = new JTextField();

                JLabel penulisLabel = new JLabel("Pengarang: ");
                JTextField penulisField = new JTextField();

                JLabel penerbit = new JLabel("Penerbit: ");
                JTextField penerbitField = new JTextField();

                JLabel jmlHalaman = new JLabel("Jumlah Halaman: ");
                JTextField jmlHalamanField = new JTextField();

                JLabel rak = new JLabel("Posisi Rak: ");
                JTextField rakField = new JTextField();

                JButton tambahButton = new JButton("Tambah");
                tambahButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BukuEntity buku = new BukuEntity();
                        buku.setJudul(judulField.getText());
                        buku.setPengarang(penulisField.getText());
                        buku.setPenerbit(penerbitField.getText());
                        buku.setJumlahHalaman(Integer.parseInt(jmlHalamanField.getText()));
                        buku.setPosisiRak(rakField.getText());
                        bukuController.tambahBuku(buku);
                        frame.dispose();
                    }
                });

                Container container = frame.getContentPane();
                container.setLayout(new GridLayout(6, 2));
                container.add(judulLabel);
                container.add(judulField);
                container.add(penulisLabel);
                container.add(penulisField);
                container.add(penerbit);
                container.add(penerbitField);
                container.add(jmlHalaman);
                container.add(jmlHalamanField);
                container.add(rak);
                container.add(rakField);
                container.add(tambahButton);

                // Menampilkan frame untuk menambah buku
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        tambahBukuButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(tambahBukuButton);

        // Tombol Hapus Buku
        JButton hapusBukuButton = new JButton("Hapus Buku");
        hapusBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputTitle = JOptionPane.showInputDialog(null, "Masukkan judul buku yang ingin dihapus:");

                if (inputTitle != null && !inputTitle.isEmpty()) {
                    BukuEntity buku = bukuController.getBukuByTitle(inputTitle);

                    if (buku != null) {
                        // Konfirmasi penghapusan dengan dialog konfirmasi
                        int option = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus buku ini?", "Konfirmasi Hapus Buku", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {
                            // Hapus buku dari data dan tampilkan pesan sukses
                            bukuController.hapusBuku(buku);
                            JOptionPane.showMessageDialog(null, "Buku berhasil dihapus.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Buku dengan judul tersebut tidak ditemukan.");
                    }
                }
            }
        });
        hapusBukuButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(hapusBukuButton);

        // Tombol Update Buku
        JButton updateBukuButton = new JButton("Update Buku");
        updateBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputTitle = JOptionPane.showInputDialog(null, "Masukkan judul buku yang ingin diupdate:");
                if (inputTitle != null && !inputTitle.isEmpty()) {
                    BukuEntity buku = bukuController.getBukuByTitle(inputTitle);
                    if (buku != null) {
                        String inputStok = JOptionPane.showInputDialog(null, "Masukkan stok buku baru:");
                        if (inputStok != null && !inputStok.isEmpty()) {
                            buku.setStokBuku(Integer.parseInt(inputStok));
                        }

                        String inputRak = JOptionPane.showInputDialog(null, "Masukkan posisi rak baru:");
                        if (inputRak != null && !inputRak.isEmpty()) {
                            buku.setPosisiRak(inputRak);
                        }

                        bukuController.updateBuku(buku);
                    } else {
                        JOptionPane.showMessageDialog(null, "Buku dengan judul tersebut tidak ditemukan.");
                    }
                }
            }
        });
        updateBukuButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(updateBukuButton);




        JButton lihatDaftarButton = new JButton("Lihat Daftar Peminjaman");
        lihatDaftarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<PeminjamanEntity> daftarPeminjaman = peminjamanControllers.viewPeminjaman();

                if (daftarPeminjaman.isEmpty()) {
                    JOptionPane.showMessageDialog(MenuAdminGUI.this, "Tidak ada peminjaman saat ini.");
                } else {
                    StringBuilder result = new StringBuilder("Daftar Peminjaman:\n");

                    for (PeminjamanEntity peminjaman : daftarPeminjaman) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        result.append("===== Berikut Data Peminjaman =====\n");
                        result.append("ID Peminjaman: ").append(peminjaman.getIdPeminjaman()).append("\n");
                        result.append("Tanggal Pinjam: ").append(dateFormat.format(peminjaman.getTanggalPinjam())).append("\n");
                        result.append("Tanggal Kembali: ").append(dateFormat.format(peminjaman.getTanggalKembali())).append("\n");
                        result.append("===== Data Peminjam =====\n");
                        result.append("Nama Peminjam: ").append(peminjaman.getNama()).append("\n");
                        result.append("Alamat: ").append(peminjaman.getAlamat()).append("\n");
                        result.append("Gender: ").append(peminjaman.getGender()).append("\n");
                        result.append("===== Data Buku =====\n");
                        result.append("Judul Buku: ").append(peminjaman.getBuku().getJudul()).append("\n");
                        result.append("Penerbit: ").append(peminjaman.getBuku().getPenerbit()).append("\n");
                        result.append("Pengarang: ").append(peminjaman.getBuku().getPengarang()).append("\n");
                        result.append("Jumlah Halaman: ").append(peminjaman.getBuku().getJumlahHalaman()).append("\n");
                        result.append("Stok Buku: ").append(peminjaman.getBuku().getStokBuku()).append("\n");
                        result.append("Posisi Rak: ").append(peminjaman.getBuku().getPosisiRak()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(MenuAdminGUI.this, result.toString());
                }
            }
        });
        lihatDaftarButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(lihatDaftarButton);

        // Tombol Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginAdminGUI();
                dispose();
            }
        });
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(logoutButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }
}
