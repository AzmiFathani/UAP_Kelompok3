// Code Review
package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Main {

    // Kelas Tiket yang merepresentasikan data tiket pesawat
    static class Tiket {
        String maskapai; // Nama maskapai penerbangan
        String tujuan;   // Tujuan penerbangan
        double harga;    // Harga tiket

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

    private JFrame frame; // Frame utama GUI
    private JTable tiketTable; // Tabel untuk menampilkan daftar tiket
    private DefaultTableModel tableModel; // Model tabel untuk memanipulasi data tabel
    protected ArrayList<Tiket> daftarTiket; // Daftar tiket

    // Konstruktor utama
    public Main() {
        // Inisialisasi daftar tiket dengan data awal
        daftarTiket = new ArrayList<>();
        daftarTiket.add(new Tiket("Garuda Indonesia", "Jakarta - Bali", 1500000));
        daftarTiket.add(new Tiket("Lion Air", "Jakarta - Surabaya", 800000));
        daftarTiket.add(new Tiket("Citilink", "Jakarta - Yogyakarta", 600000));

        initialize(); // Memulai GUI
    }

    // Metode untuk menginisialisasi GUI
    private void initialize() {
        frame = new JFrame("Sistem Pemesanan Tiket Pesawat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Warna latar belakang lembut (biru muda)
        Color softBlue = new Color(173, 216, 230);
        frame.getContentPane().setBackground(softBlue);

        // Kolom tabel
        String[] columnNames = {"Maskapai", "Tujuan", "Harga"};
        tableModel = new DefaultTableModel(columnNames, 0); // Model tabel
        tiketTable = new JTable(tableModel);
        tiketTable.setBackground(softBlue); // Warna latar tabel
        tiketTable.setFillsViewportHeight(true);

        refreshTable(); // Menampilkan data awal

        JScrollPane scrollPane = new JScrollPane(tiketTable); // Scroll view untuk tabel
        scrollPane.getViewport().setBackground(softBlue); // Warna viewport
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk tombol operasi
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(softBlue);

        // Tombol-tombol operasi
        JButton addButton = new JButton("Tambah Tiket");
        JButton updateButton = new JButton("Update Tiket");
        JButton deleteButton = new JButton("Hapus Tiket");
        JButton orderButton = new JButton("Pesan Tiket");

        // Menambahkan tombol ke panel
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(orderButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Event listener untuk tombol
        addButton.addActionListener(e -> addTicket()); // Tambah tiket
        updateButton.addActionListener(e -> updateTicket(tiketTable.getSelectedRow())); // Update tiket
        deleteButton.addActionListener(e -> deleteTicket(tiketTable.getSelectedRow())); // Hapus tiket
        orderButton.addActionListener(e -> orderTicket(tiketTable.getSelectedRow())); // Pesan tiket

        frame.setVisible(true); // Menampilkan frame
    }

    // Memperbarui data dalam tabel
    private void refreshTable() {
        tableModel.setRowCount(0); // Menghapus semua data sebelumnya
        for (Tiket tiket : daftarTiket) {
            tableModel.addRow(new Object[]{tiket.maskapai, tiket.tujuan, "Rp" + tiket.harga});
        }
    }

    // Menambahkan tiket baru
    private void addTicket() {
        JTextField maskapaiField = new JTextField();
        JTextField tujuanField = new JTextField();
        JTextField hargaField = new JTextField();

        Object[] message = {
                "Nama Maskapai:", maskapaiField,
                "Tujuan:", tujuanField,
                "Harga:", hargaField
        };

        // Dialog input untuk menambahkan tiket
        int option = JOptionPane.showConfirmDialog(frame, message, "Tambah Tiket", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String maskapai = maskapaiField.getText();
                String tujuan = tujuanField.getText();
                double harga = Double.parseDouble(hargaField.getText());
                Tiket tiket = new Tiket(maskapai, tujuan, harga); // Membuat tiket baru
                daftarTiket.add(tiket); // Menambahkan ke daftar
                refreshTable(); // Refresh tabel
                JOptionPane.showMessageDialog(frame, "Tiket berhasil ditambahkan.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Memperbarui tiket yang dipilih
    private void updateTicket(int index) {
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih tiket yang ingin diupdate.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tiket tiket = daftarTiket.get(index);

        // Input data baru
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

    // Menghapus tiket yang dipilih
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

    // Memesan tiket
    private void orderTicket(int index) {
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih tiket yang ingin dipesan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tiket tiket = daftarTiket.get(index);

        // Form input data pemesanan
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

                double totalHarga = jumlah * tiket.harga; // Menghitung total harga
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

    // Metode utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
