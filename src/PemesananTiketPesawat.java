import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PemesananTiketPesawat {

    static class Tiket {
        String maskapai;
        String tujuan;
        double harga;

        Tiket(String maskapai, String tujuan, double harga) {
            this.maskapai = maskapai;
            this.tujuan = tujuan;
            this.harga = harga;
        }

        @Override
        public String toString() {
            return maskapai + ", " + tujuan + ", Rp" + harga;
        }
    }

    private JFrame frame;
    private JTable tiketTable;
    private DefaultTableModel tableModel;
    private ArrayList<Tiket> daftarTiket;

    public PemesananTiketPesawat() {
        daftarTiket = new ArrayList<>();
        daftarTiket.add(new Tiket("Garuda Indonesia", "Jakarta - Bali", 1500000));
        daftarTiket.add(new Tiket("Lion Air", "Jakarta - Surabaya", 800000));
        daftarTiket.add(new Tiket("Citilink", "Jakarta - Yogyakarta", 600000));

        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistem Pemesanan Tiket Pesawat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Set background color to a soft light blue
        Color softBlue = new Color(173, 216, 230);
        frame.getContentPane().setBackground(softBlue);

        String[] columnNames = {"Maskapai", "Tujuan", "Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tiketTable = new JTable(tableModel);
        tiketTable.setBackground(softBlue);
        tiketTable.setFillsViewportHeight(true);

        refreshTable();

        JScrollPane scrollPane = new JScrollPane(tiketTable);
        scrollPane.getViewport().setBackground(softBlue); // Set background for the viewport
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(softBlue); // Set panel background to match

        JButton addButton = new JButton("Tambah Tiket");
        JButton updateButton = new JButton("Update Tiket");
        JButton deleteButton = new JButton("Hapus Tiket");
        JButton orderButton = new JButton("Pesan Tiket");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(orderButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTicket());
        updateButton.addActionListener(e -> updateTicket(tiketTable.getSelectedRow()));
        deleteButton.addActionListener(e -> deleteTicket(tiketTable.getSelectedRow()));
        orderButton.addActionListener(e -> orderTicket(tiketTable.getSelectedRow()));

        frame.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Tiket tiket : daftarTiket) {
            tableModel.addRow(new Object[]{tiket.maskapai, tiket.tujuan, "Rp" + tiket.harga});
        }
    }

    private void addTicket() {
        JTextField maskapaiField = new JTextField();
        JTextField tujuanField = new JTextField();
        JTextField hargaField = new JTextField();

        Object[] message = {
                "Nama Maskapai:", maskapaiField,
                "Tujuan:", tujuanField,
                "Harga:", hargaField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Tambah Tiket", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String maskapai = maskapaiField.getText();
                String tujuan = tujuanField.getText();
                double harga = Double.parseDouble(hargaField.getText());
                Tiket tiket = new Tiket(maskapai, tujuan, harga);
                daftarTiket.add(tiket);
                refreshTable();
                JOptionPane.showMessageDialog(frame, "Tiket berhasil ditambahkan.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTicket(int index) {
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih tiket yang ingin diupdate.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tiket tiket = daftarTiket.get(index);

        JTextField maskapaiField = new JTextField(tiket.maskapai);
        JTextField tujuanField = new JTextField(tiket.tujuan);
        JTextField hargaField = new JTextField(String.valueOf(tiket.harga));

        Object[] message = {
                "Nama Maskapai:", maskapaiField,
                "Tujuan:", tujuanField,
                "Harga:", hargaField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Update Tiket", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                tiket.maskapai = maskapaiField.getText();
                tiket.tujuan = tujuanField.getText();
                tiket.harga = Double.parseDouble(hargaField.getText());
                refreshTable();
                JOptionPane.showMessageDialog(frame, "Tiket berhasil diupdate.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteTicket(int index) {
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih tiket yang ingin dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(frame, "Yakin ingin menghapus tiket ini?", "Hapus Tiket", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            daftarTiket.remove(index);
            refreshTable();
            JOptionPane.showMessageDialog(frame, "Tiket berhasil dihapus.");
        }
    }

    private void orderTicket(int index) {
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih tiket yang ingin dipesan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tiket tiket = daftarTiket.get(index);

        JTextField namaField = new JTextField();
        JTextField jumlahField = new JTextField();
        JTextField tanggalField = new JTextField("yyyy-MM-dd");
        JTextField waktuField = new JTextField("HH:mm");

        Object[] message = {
                "Nama:", namaField,
                "Jumlah Tiket:", jumlahField,
                "Tanggal Penerbangan (yyyy-MM-dd):", tanggalField,
                "Waktu Penerbangan (HH:mm):", waktuField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Pesan Tiket", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nama = namaField.getText();
                int jumlah = Integer.parseInt(jumlahField.getText());
                LocalDate tanggal = LocalDate.parse(tanggalField.getText());
                LocalTime waktu = LocalTime.parse(waktuField.getText());

                double totalHarga = jumlah * tiket.harga;
                JOptionPane.showMessageDialog(frame, "Pesanan berhasil!\n" +
                        "Nama: " + nama + "\n" +
                        "Tanggal: " + tanggal + "\n" +
                        "Waktu: " + waktu + "\n" +
                        "Total Harga: Rp" + totalHarga);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Jumlah harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(frame, "Format tanggal atau waktu tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PemesananTiketPesawat::new);
    }
}