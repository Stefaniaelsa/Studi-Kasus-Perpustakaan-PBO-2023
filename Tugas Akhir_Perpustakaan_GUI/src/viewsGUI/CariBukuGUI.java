package viewsGUI;

import controllers.BukuControllers;
import entity.BukuEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CariBukuGUI {

    private BukuControllers bukuControllers;

    public CariBukuGUI() {
        this.bukuControllers = new BukuControllers();

        // Create the JFrame
        JFrame frame = new JFrame("Cari Buku");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the JButton
        JButton cariBukuButton = new JButton("Cari Buku");
        cariBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Masukkan judul, penerbit, atau pengarang buku yang ingin dicari:");
                if (input != null && !input.isEmpty()) {
                    List<BukuEntity> hasilPencarian = bukuControllers.cariBuku(input);
                    if (hasilPencarian != null && !hasilPencarian.isEmpty()) {
                        StringBuilder output = new StringBuilder();
                        for (BukuEntity buku : hasilPencarian) {
                            output.append("===== Berikut Data Buku Yang diinginkan =====\n");
                            output.append("Judul: ").append(buku.getJudul()).append("\n");
                            output.append("Penerbit: ").append(buku.getPenerbit()).append("\n");
                            output.append("Pengarang: ").append(buku.getPengarang()).append("\n");
                            output.append("Jumlah Halaman: ").append(buku.getJumlahHalaman()).append("\n");
                            output.append("Stok Buku: ").append(buku.getStokBuku()).append("\n");
                            output.append("Posisi Rak: ").append(buku.getPosisiRak()).append("\n");
                            output.append("--------------------------\n");
                        }

                        JOptionPane.showMessageDialog(null, output.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Buku dengan kriteria tersebut tidak ditemukan.");
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.FlowLayout());

        panel.add(cariBukuButton);

        frame.add(panel);

        frame.setVisible(true);
    }
}
