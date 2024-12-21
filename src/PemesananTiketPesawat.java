import java.util.ArrayList;
import java.util.Scanner;

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
            return "Maskapai: " + maskapai + ", Tujuan: " + tujuan + ", Harga: Rp" + harga;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tiket> daftarTiket = new ArrayList<>();

        daftarTiket.add(new Tiket("Garuda Indonesia", "Jakarta - Bali", 1500000));
        daftarTiket.add(new Tiket("Lion Air", "Jakarta - Surabaya", 800000));
        daftarTiket.add(new Tiket("Citilink", "Jakarta - Yogyakarta", 600000));

        System.out.println("Selamat datang di sistem pemesanan tiket pesawat!\n");

        System.out.println("Daftar tiket yang tersedia:");
        for (int i = 0; i < daftarTiket.size(); i++) {
            System.out.println((i + 1) + ". " + daftarTiket.get(i));
        }

        System.out.print("\nMasukkan nomor tiket yang ingin Anda pesan: ");
        int pilihan = scanner.nextInt();

        if (pilihan > 0 && pilihan <= daftarTiket.size()) {
            Tiket tiketDipilih = daftarTiket.get(pilihan - 1);
            System.out.println("\nAnda telah memilih tiket berikut:");
            System.out.println(tiketDipilih);

            System.out.print("\nMasukkan nama Anda untuk konfirmasi: ");
            scanner.nextLine(); // Membersihkan buffer scanner
            String nama = scanner.nextLine();

            System.out.print("Masukkan jumlah tiket yang ingin dibeli: ");
            int jumlahTiket = scanner.nextInt();

            double totalHarga = tiketDipilih.harga * jumlahTiket;
            System.out.println("\nTerima kasih, " + nama + ". Anda telah memesan " + jumlahTiket + " tiket.");
            System.out.println("Total harga: Rp" + totalHarga);
        } else {
            System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
        }

        scanner.close();
    }
}