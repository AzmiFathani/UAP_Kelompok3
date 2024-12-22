import java.util.ArrayList;
import java.util.InputMismatchException;
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

        int pilihan = 0;
        while (true) {
            try {
                System.out.print("\nMasukkan nomor tiket yang ingin Anda pesan: ");
                pilihan = scanner.nextInt();
                if (pilihan > 0 && pilihan <= daftarTiket.size()) {
                    break;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.next(); // Membersihkan buffer scanner
            }
        }

        Tiket tiketDipilih = daftarTiket.get(pilihan - 1);
        System.out.println("\nAnda telah memilih tiket berikut:");
        System.out.println(tiketDipilih);

        scanner.nextLine(); // Membersihkan buffer scanner

        String nama = "";
        while (true) {
            try {
                System.out.print("\nMasukkan nama Anda untuk konfirmasi: ");
                nama = scanner.nextLine();
                if (!nama.isEmpty()) {
                    break;
                } else {
                    System.out.println("Nama tidak boleh kosong.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }

        int jumlahTiket = 0;
        while (true) {
            try {
                System.out.print("Masukkan jumlah tiket yang ingin dibeli: ");
                jumlahTiket = scanner.nextInt();
                if (jumlahTiket > 0) {
                    break;
                } else {
                    System.out.println("Jumlah tiket harus lebih dari 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.next(); // Membersihkan buffer scanner
            }
        }

        double totalHarga = tiketDipilih.harga * jumlahTiket;
        System.out.println("\nTerima kasih, " + nama + ". Anda telah memesan " + jumlahTiket + " tiket.");
        System.out.println("Total harga: Rp" + totalHarga);

        scanner.close();
}
}
