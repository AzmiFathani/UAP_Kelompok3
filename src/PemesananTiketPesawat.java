import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate; //API LocalDate
import java.time.LocalTime; //API LocalTime

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

        // Menambahkan beberapa tiket awal
        daftarTiket.add(new Tiket("Garuda Indonesia", "Jakarta - Bali", 1500000));
        daftarTiket.add(new Tiket("Lion Air", "Jakarta - Surabaya", 800000));
        daftarTiket.add(new Tiket("Citilink", "Jakarta - Yogyakarta", 600000));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tampilkan Daftar Tiket");
            System.out.println("2. Tambah Tiket");
            System.out.println("3. Update Tiket");
            System.out.println("4. Hapus Tiket");
            System.out.println("5. Pesan Tiket");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");

            int menu = 0;
            try {
                menu = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.next(); // Clear the invalid input
                continue; // Restart the loop
            }

            switch (menu) {
                case 1:
                    // Read: Menampilkan daftar tiket
                    System.out.println("\nDaftar tiket yang tersedia:");
                    for (int i = 0; i < daftarTiket.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarTiket.get(i));
                    }
                    break;

                case 2:
                    // Create: Menambahkan tiket baru
                    try {
                        System.out.print("Masukkan nama maskapai: ");
                        String maskapai = scanner.nextLine();
                        System.out.print("Masukkan tujuan: ");
                        String tujuan = scanner.nextLine();
                        System.out.print("Masukkan harga: ");
                        double harga = scanner.nextDouble();
                        daftarTiket.add(new Tiket(maskapai, tujuan, harga));
                        System.out.println("Tiket berhasil ditambahkan.");
                    } catch (InputMismatchException e) {
                        System.out.println("Input tidak valid. Silakan masukkan harga yang benar.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;

                case 3:
                    // Update: Memperbarui tiket yang ada
                    try {
                        System.out.print("Masukkan nomor tiket yang ingin diupdate: ");
                        int nomorUpdate = scanner.nextInt();
                        if (nomorUpdate > 0 && nomorUpdate <= daftarTiket.size()) {
                            scanner.nextLine(); // Clear the buffer
                            System.out.print("Masukkan nama maskapai baru: ");
                            String maskapai = scanner.nextLine();
                            System.out.print("Masukkan tujuan baru: ");
                            String tujuan = scanner.nextLine();
                            System.out.print("Masukkan harga baru: ");
                            double harga = scanner.nextDouble();
                            Tiket tiketUpdate = new Tiket(maskapai, tujuan, harga);
                            daftarTiket.set(nomorUpdate - 1, tiketUpdate);
                            System.out.println("Tiket berhasil diupdate.");
                        } else {
                            System.out.println("Nomor tiket tidak valid.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input tidak valid. Silakan masukkan angka.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;

                case 4:
                    // Delete: Menghapus tiket
                    try {
                        System.out.print("Masukkan nomor tiket yang ingin dihapus: ");
                        int nomorHapus = scanner.nextInt();
                        if (nomorHapus > 0 && nomorHapus <= daftarTiket.size()) {
                            daftarTiket.remove(nomorHapus - 1);
                            System.out.println("Tiket berhasil dihapus.");
                        } else {
                            System.out.println("Nomor tiket tidak valid.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input tidak valid. Silakan masukkan angka.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;

                case 5:
                    // Pesan Tiket
                    if (daftarTiket.isEmpty()) {
                        System.out.println("Tidak ada tiket yang tersedia untuk dipesan.");
                        break;
                    }
                    try {
                        System.out.println("\nDaftar tiket yang tersedia:");
                        for (int i = 0; i < daftarTiket.size(); i++) {
                            System.out.println((i + 1) + ". " + daftarTiket.get(i));
                        }

                        System.out.print("\nMasukkan nomor tiket yang ingin Anda pesan: ");
                        int pilihan = scanner.nextInt();
                        if (pilihan > 0 && pilihan <= daftarTiket.size()) {
                            Tiket tiketDipilih = daftarTiket.get(pilihan - 1);
                            System.out.println("\nAnda telah memilih tiket berikut:");
                            System.out.println(tiketDipilih);

                            scanner.nextLine(); // Clear the buffer

                            String nama = "";
                            while (true) {
                                System.out.print("\nMasukkan nama Anda untuk konfirmasi: ");
                                nama = scanner.nextLine();
                                if (!nama.isEmpty()) {
                                    break;
                                } else {
                                    System.out.println("Nama tidak boleh kosong.");
                                }
                            }

                            int jumlahTiket = 0;
                            while (true) {
                                System.out.print("Masukkan jumlah tiket yang ingin dibeli: ");
                                jumlahTiket = scanner.nextInt();
                                if (jumlahTiket > 0) {
                                    break;
                                } else {
                                    System.out.println("Jumlah tiket harus lebih dari 0.");
                                }
                            }

                            // Meminta pengguna untuk memasukkan tanggal penerbangan
                            LocalDate tanggalPenerbangan = null;
                            while (true) {
                                try {
                                    System.out.print("Masukkan tanggal penerbangan (yyyy-MM-dd): ");
                                    String inputTanggal = scanner.next();
                                    tanggalPenerbangan = LocalDate.parse(inputTanggal); // Menggunakan API LocalDate untuk mengonversi string menjadi tanggal
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Format tanggal tidak valid. Silakan masukkan dalam format yyyy-MM-dd.");
                                }
                            }

                            // Meminta pengguna untuk memasukkan waktu penerbangan
                            LocalTime waktuPenerbangan = null;
                            while (true) {
                                try {
                                    System.out.print("Masukkan waktu penerbangan (HH:mm): ");
                                    String inputWaktu = scanner.next();
                                    waktuPenerbangan = LocalTime.parse(inputWaktu);  // Menggunakan API LocalTime untuk mengonversi string menjadi waktu
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Format waktu tidak valid. Silakan masukkan dalam format HH:mm.");
                                }
                            }

                            double totalHarga = tiketDipilih.harga * jumlahTiket;
                            System.out.println("\nTerima kasih, " + nama + ". Anda telah memesan " + jumlahTiket + " tiket.");
                            System.out.println("Tanggal Penerbangan: " + tanggalPenerbangan + ", Waktu Penerbangan: " + waktuPenerbangan);
                            System.out.println("Total harga: Rp" + totalHarga);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input tidak valid. Silakan masukkan angka.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;

                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem pemesanan tiket pesawat.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}