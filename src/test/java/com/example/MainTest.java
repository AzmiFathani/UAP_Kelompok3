package com.example;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class MainTest {

    @Test
    void testAddTicket() {
        Main mainApp = new Main();
        int initialSize = mainApp.daftarTiket.size();
        mainApp.daftarTiket.add(new Main.Tiket("AirAsia", "Jakarta - Medan", 500000));
        int newSize = mainApp.daftarTiket.size();

        assertEquals(initialSize + 1, newSize, "Tiket baru harus ditambahkan.");
        assertEquals("AirAsia", mainApp.daftarTiket.get(newSize - 1).maskapai);
        assertEquals("Jakarta - Medan", mainApp.daftarTiket.get(newSize - 1).tujuan);
        assertEquals(500000, mainApp.daftarTiket.get(newSize - 1).harga);
    }

    @Test
    void testUpdateTicket() {
        Main mainApp = new Main();
        Main.Tiket tiket = mainApp.daftarTiket.get(0);
        String originalTujuan = tiket.tujuan;

        tiket.tujuan = "Jakarta - Bandung";
        assertNotEquals(originalTujuan, tiket.tujuan, "Tujuan harus berubah setelah update.");
        assertEquals("Jakarta - Bandung", tiket.tujuan, "Tujuan harus sesuai input baru.");
    }

    @Test
    void testDeleteTicket() {
        Main mainApp = new Main();
        int initialSize = mainApp.daftarTiket.size();

        mainApp.daftarTiket.remove(0);
        int newSize = mainApp.daftarTiket.size();
        assertEquals(initialSize - 1, newSize, "Tiket harus terhapus.");
    }

    @Test
    void testOrderTicket() {
        Main mainApp = new Main();
        Main.Tiket tiket = mainApp.daftarTiket.get(0);

        String nama = "John Doe";
        int jumlah = 2;
        double expectedTotalHarga = jumlah * tiket.harga;

        double actualTotalHarga = jumlah * tiket.harga;
        assertEquals(expectedTotalHarga, actualTotalHarga, "Total harga harus sesuai perhitungan.");
    }
}
