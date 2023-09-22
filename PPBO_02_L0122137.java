import java.util.ArrayList;
import java.util.Scanner;

public class PPBO_02_L0122137 {
  public static final int PPN = 11;
  public static int totalTransaksi;
  private ArrayList<Barang> arrayListBarang = new ArrayList<Barang>();
  private int kasToko = 0;

  public static void main(String[] args) {
    PPBO_02_L0122137 swalayan = new PPBO_02_L0122137();
    Scanner scanner = new Scanner(System.in);

    swalayan.arrayListBarang.add(new Barang("Barang1", 10000, 5));
    swalayan.arrayListBarang.add(new Barang("Barang2", 12000, 5));
    swalayan.arrayListBarang.add(new Barang("Barang3", 15000, 5));

    while (true) {
      int opsi = swalayan.cetakMenu(scanner);

      if (opsi == 1) {
        System.out.println("Penjualan barang");

        System.out.print("Nama barang   : ");
        String nama = scanner.nextLine();

        System.out.print("Jumlah barang : ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        swalayan.jualBarang(nama, jumlah, scanner);
      } else if (opsi == 2) {
        System.out.println("Restock barang");

        System.out.println("Nama barang : ");
        String nama = scanner.nextLine();

        System.out.print("Jumlah barang : ");
        int jumlah = scanner.nextInt();

        swalayan.restokBarang(nama, jumlah);
      } else if (opsi == 3) {
        // tambah barang baru
        System.out.println("Nama barang baru: ");
        System.out.print(">> ");
        String nama = scanner.nextLine();

        System.out.println("Harga barang baru: ");
        System.out.print(">> ");
        int harga = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Jumlah barang baru: ");
        System.out.print(">> ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        swalayan.tambahBarangBaru(nama, harga, jumlah);
      } else if (opsi == 4) {
        // status toko
        swalayan.tampilkanStatusToko();
      } else {
        break;
      }

    }

    swalayan.restokBarang("barang2", 3);
    swalayan.cetakDaftarBarang();
    scanner.close();
  }

  private void tampilkanStatusToko() {
    System.out.println("Laporan Toko");

    System.out.printf("Total transaksi : %d\n", PPBO_02_L0122137.totalTransaksi);
    System.out.printf("Total uang kas  : Rp %d\n", this.kasToko);
  }

  private int cetakMenu(Scanner scanner) {
    System.out.println("Minimarket Sederhana");

    cetakDaftarBarang();

    System.out.println("Opsi:");
    System.out.println("1. Jual barang");
    System.out.println("2. Restok barang");
    System.out.println("3. Tambah barang baru");
    System.out.println("4. Status toko");
    System.out.println("5. Keluar");
    System.out.print(">> ");

    int opsi = scanner.nextInt();
    scanner.nextLine();

    return opsi;
  }

  private void cetakDaftarBarang() {
    System.out.println("----------------------------------------------------------");
    System.out.printf("| %-24s | %-16s | %-8s |\n", "Nama barang", "Harga satuan", "Jumlah");
    System.out.println("----------------------------------------------------------");

    for (Barang barang : this.arrayListBarang) {
      System.out.printf(
          "| %-24s | Rp%14d | %8d |\n",
          barang.getNama(),
          barang.getHarga(),
          barang.getJumlah()
      );
    }

    System.out.println("----------------------------------------------------------");
  }

  private void jualBarang(String nama, int jumlah, Scanner scanner) {
    int index = -1;
    for (int i = 0; i < arrayListBarang.size(); i++) {
      if (arrayListBarang.get(i).getNama().equalsIgnoreCase(nama)) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("Barang tidak ada");
    } else {
      Barang barangKeIndex = arrayListBarang.get(index);

      // validasi jumlah barang
      if (barangKeIndex.getJumlah() >= jumlah) {
        arrayListBarang.get(index).setJumlah(barangKeIndex.getJumlah() - jumlah);

        int totalHarga = arrayListBarang.get(index).getHarga() * jumlah;
        double totalPPN = totalHarga * ((double) PPBO_02_L0122137.PPN / 100);
        int totalBayar = totalHarga + (int) totalPPN;

        System.out.printf("Total harga      : Rp%16d\n", totalHarga);
        System.out.printf("PPN              : Rp%16d\n", (int) totalPPN);
        System.out.printf("Total pembayaran : Rp%16d\n", (int) totalBayar);

        boolean transaksiBerhasil = false;

        while (!transaksiBerhasil) {
          System.out.println("Masukkan uang Anda");
          System.out.print(">> ");
          int bayar = scanner.nextInt();
          scanner.nextLine();

          if (bayar >= totalBayar) {
            kasToko += totalBayar;
            PPBO_02_L0122137.totalTransaksi++;

            int kembalian = bayar - totalBayar;
            System.out.printf("Kembalian        : Rp%16d\n", kembalian);

            System.out.println("Transaksi sukses");
            transaksiBerhasil = true;
          } else {
            System.out.println("duit ga cukup");
          }
        }
      } else {
        System.out.println("Barang tidak cukup");
      }
    }
  }

  private void restokBarang(String nama, int jumlah) {
    int index = -1;
    for (int i = 0; i < arrayListBarang.size(); i++) {
      if (arrayListBarang.get(i).getNama().equalsIgnoreCase(nama)) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      Barang barangKeIndex = arrayListBarang.get(index);

      arrayListBarang.get(index).setJumlah(barangKeIndex.getJumlah() + jumlah);

      //

      // if (arrayListBarang.get(index).getJumlah() >= jumlah) {
      // arrayListBarang.get(index).setJumlah(arrayListBarang.get(index).getJumlah() -
      // jumlah);
      // }
    } else {
      System.out.println("tdk ada");
    }

  }

  private void tambahBarangBaru(String nama, int harga, int jumlah) {
    // cek apakah barang sudah ada
    int index = -1;
    for (int a = 0; a < this.arrayListBarang.size(); a++) {
      if (this.arrayListBarang.get(a).getNama().equalsIgnoreCase(nama)) {
        index = a;
        break;
      }
    }

    if (index != -1) {
      // barang sudah ada
      System.out.println("barang sudah ada");
    } else {
      System.out.println("Menambahkan barang baru...");
      Barang barangBaru = new Barang(nama, harga, jumlah);
      this.arrayListBarang.add(barangBaru);
    }
  }
}
