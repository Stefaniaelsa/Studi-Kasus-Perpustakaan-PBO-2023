package viewsGUI;

import controllers.BukuControllers;
import entity.BukuEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewAllBukuGUI {

    private BukuControllers bukuControllers;

    public ViewAllBukuGUI() {
        this.bukuControllers = new BukuControllers();

        JFrame frame = new JFrame("View All Buku");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton viewAllBukuButton = new JButton("View All Buku");
        viewAllBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<BukuEntity> allBukus = bukuControllers.viewBuku();
                if (allBukus != null && !allBukus.isEmpty()) {
                    StringBuilder output = new StringBuilder();

                    for (BukuEntity buku : allBukus) {
                        output.append(String.format("===== Daftar Buku =====\n"));
                        output.append(String.format("Judul: %s\n", buku.getJudul()));
                        output.append(String.format("Penerbit: %s\n", buku.getPenerbit()));
                        output.append(String.format("Pengarang: %s\n", buku.getPengarang()));
                        output.append(String.format("Jumlah Halaman: %d\n", buku.getJumlahHalaman()));
                        output.append(String.format("Stok Buku: %d\n", buku.getStokBuku()));
                        output.append(String.format("Posisi Rak: %s\n", buku.getPosisiRak()));
                        output.append("--------------------------\n");
                    }

                    JOptionPane.showMessageDialog(null, output.toString(), "Daftar Buku", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak ada buku yang tersedia.", "Daftar Buku Kosong", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.FlowLayout());

        panel.add(viewAllBukuButton);

        frame.add(panel);

        frame.setLayout(new java.awt.FlowLayout());
        frame.setVisible(true);
    }
}
