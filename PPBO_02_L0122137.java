// Nama : Retno Pinka Pratiwi
// NIM  : L0122137

//import packages yang digunakan 
import java.util.ArrayList;
import java.util.Scanner;

public class PPBO_02_L0122137 {
  // atribut-atribut berisi variabel yang diminta pada soal
  public static final int PPN = 11;
  public static int totalTransaksi;
  private int kasToko = 0;
  // arraylist untuk menambahkan objek barang ke dalam list
  private ArrayList<Barang> arrayListBarang = new ArrayList<Barang>();

  public static void main(String[] args) {
    // membuat objek baru dengan nama swalayan
    PPBO_02_L0122137 swalayan = new PPBO_02_L0122137();

    // membuka scanner
    Scanner scanner = new Scanner(System.in);

    // menambahkan barang-barang setelah program dijalankan
    swalayan.arrayListBarang.add(new Barang("Bear Brand", 11000, 24));
    swalayan.arrayListBarang.add(new Barang("Cimory Fresh Milk", 7500, 24));
    swalayan.arrayListBarang.add(new Barang("Frisian Flag", 7000, 24));
    swalayan.arrayListBarang.add(new Barang("Greenfields", 8500, 24));
    swalayan.arrayListBarang.add(new Barang("Ultra Milk", 6500, 24));

    while (true) {
      // mencetak menu layanan swalayan
      int opsi = swalayan.cetakMenu(scanner);

      if (opsi == 1) {
        // penjualan barang
        System.out.println("Penjualan barang");

        // memasukkan nama dan jumlah barang yang akan dijual
        System.out.print("Nama barang   : ");
        String nama = scanner.nextLine();

        System.out.print("Jumlah barang : ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        // memanggil method jualBarang() untuk melanjutkan transaksi
        swalayan.jualBarang(nama, jumlah, scanner);

      } else if (opsi == 2) {
        // restock barang jika ada barang baru yang masuk ke dalam gudang
        System.out.println("Restock barang");

        // memasukkan nama dan jumlah barang yang bertambah jumlahnya
        System.out.println("Nama barang : ");
        String nama = scanner.nextLine();

        System.out.print("Jumlah barang : ");
        int jumlah = scanner.nextInt();

        // memanggil method restockBarang() untuk menambahkan jumlah barang
        swalayan.restokBarang(nama, jumlah);

      } else if (opsi == 3) {
        // tambah barang baru yang belum ada di gudang
        // memasukkan nama, harga, dan jumlah barang yang ingin ditambahkan
        System.out.print("Nama barang baru: ");
        String nama = scanner.nextLine();

        System.out.print("Harga barang baru: ");
        int harga = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Jumlah barang baru: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        // memanggil method tambahBarangBaru() untuk membuat objek baru
        swalayan.tambahBarangBaru(nama, harga, jumlah);
      } else if (opsi == 4) {
        // laporan penjualan dan transaksi toko
        swalayan.cetakLaporanToko();

      } else {
        // opsi selain 1-4 keluar program
        break;
      }

    }

    // memanggil method untuk mencetak daftar barang di dalam gudang
    swalayan.cetakDaftarBarang();

    // menutup scanner
    scanner.close();
  }

  // method untuk mencetak laporan toko
  private void cetakLaporanToko() {
    System.out.println("Laporan Toko");

    // mencetak jumlah transaksi yang dilakukan dan total uang kas yang masuk
    System.out.printf("Total transaksi : %d\n", PPBO_02_L0122137.totalTransaksi);
    System.out.printf("Total uang kas  : Rp %d\n", this.kasToko);
  }

  // mencetak menu layanan seperti jual, restock, dll yang
  // mengembalikan integer berupa opsi yang dipilih user
  private int cetakMenu(Scanner scanner) {
    System.out.println("Minimarket Sederhana");

    // memanggil method cetakDaftarBarang untuk mencetak barang yang tersedia
    cetakDaftarBarang();

    System.out.println("Opsi:");
    System.out.println("1. Jual barang");
    System.out.println("2. Restok barang");
    System.out.println("3. Tambah barang baru");
    System.out.println("4. Laporan toko");
    System.out.println("5. Keluar");
    System.out.print(">> ");

    // meminta user memasukkan angka sesuai menu
    int opsi = scanner.nextInt();
    scanner.nextLine();

    return opsi;
  }

  // mencetak daftar barang di dalam gudang yang disajikan dalam bentuk tabel
  private void cetakDaftarBarang() {
    System.out.println("----------------------------------------------------------");
    System.out.printf("| %-24s | %-16s | %-8s |\n", "Nama barang", "Harga satuan", "Jumlah");
    System.out.println("----------------------------------------------------------");

    // mencetak nama, harga, dan jumlah dengan getter secara berulang
    for (Barang barang : this.arrayListBarang) {
      System.out.printf(
          "| %-24s | Rp%14d | %8d |\n",
          barang.getNama(),
          barang.getHarga(),
          barang.getJumlah());
    }

    System.out.println("----------------------------------------------------------");
  }

  // method jualBarang() ketika ada pembeli
  private void jualBarang(String nama, int jumlah, Scanner scanner) {
    // inisialisasi index dengan -1 yang artinya barang yang dicari belum ada
    int index = -1;
    // mencari barang dengan nama yang sama seperti yang diketikkan pada input
    for (int i = 0; i < arrayListBarang.size(); i++) {
      if (arrayListBarang.get(i).getNama().equalsIgnoreCase(nama)) {
        // ketika barang ditemukan, nilai index berubah menjadi index barang yang dicari
        index = i;
        break;
      }
    }

    if (index == -1) {
      // ketika barang yang dicari tidak ditemukan
      System.out.println("Barang tidak ditemukan!");
    } else {
      // ketika barang yang dicari sudah ditemukan, maka ambil objek tersebut dari
      // arrayList
      Barang barangKeIndex = arrayListBarang.get(index);

      // validasi jumlah barang di gudang dengan jumlah barang yang diminta
      if (barangKeIndex.getJumlah() >= jumlah) {
        // barang di dalam gudang memenuhi jumlah yang diminta
        arrayListBarang.get(index).setJumlah(barangKeIndex.getJumlah() - jumlah);

        // aritmatika untuk transaksi penjualan
        // total harga adalah jumlah harga dari barang dikali dengan jumlah yang dibeli
        int totalHarga = arrayListBarang.get(index).getHarga() * jumlah;
        // PPN dihitung dengan mengalikan 11% dengan total harga yang telah dihitung
        // sebelumnya
        double totalPPN = totalHarga * ((double) PPBO_02_L0122137.PPN / 100);
        int totalBayar = totalHarga + (int) totalPPN;

        // cetak hasil transaksi penjualan
        System.out.printf("Total harga      : Rp%16d\n", totalHarga);
        System.out.printf("PPN              : Rp%16d\n", (int) totalPPN);
        System.out.printf("Total pembayaran : Rp%16d\n", (int) totalBayar);

        // inisialisasi transaksiBerhasil dengan false
        boolean transaksiBerhasil = false;

        while (!transaksiBerhasil) {
          // memasukkan uang untuk membayar
          System.out.println("Masukkan uang Anda");
          System.out.print(">> ");
          int bayar = scanner.nextInt();
          scanner.nextLine();

          if (bayar >= totalBayar) {
            // ketika uang yang dibayarkan cukup
            kasToko += totalBayar;
            PPBO_02_L0122137.totalTransaksi++;

            int kembalian = bayar - totalBayar;
            System.out.printf("Kembalian        : Rp%16d\n", kembalian);

            System.out.println("Transaksi sukses!");

            // diinisialisasi ulang dengan nilai true karena transaksi berhasil dilakukan
            transaksiBerhasil = true;
          } else {
            // ketika uang tidak memenuhi, menampilkan pesan eror dan program meminta
            // jumlah uang lagi
            System.out.println("Uang tidak memenuhi");
          }
        }
      } else {
        // ketika barang di dalam gudang tidak memenuhi jumlah yang diminta
        System.out.println("Barang tidak mencukupi");
      }
    }
    System.out.println("");
  }

  // method restokBarang() untuk menambahkan barang dalam gudang
  private void restokBarang(String nama, int jumlah) {
    // inisialisasi index dengan -1 yang artinya barang yang dicari belum ada
    int index = -1;
    // mencari barang dengan nama yang sama seperti yang diketikkan pada input
    for (int i = 0; i < arrayListBarang.size(); i++) {
      if (arrayListBarang.get(i).getNama().equalsIgnoreCase(nama)) {
        // ketika barang ditemukan, nilai index berubah menjadi index barang yang dicari
        index = i;
        break;
      }
    }

    if (index != -1) {
      // ketika barang yang dicari sudah ditemukan, maka ambil objek tersebut dari
      // arrayList
      Barang barangKeIndex = arrayListBarang.get(index);

      // mengubah jumlah barang dalam gudang menggunakan setter
      arrayListBarang.get(index).setJumlah(barangKeIndex.getJumlah() + jumlah);

    } else {
      // ketika barang yang dicari tidak ditemukan
      System.out.println("Nama barang tidak ditemukan!");
      System.out.println("Cek kesalahan tulis atau tambahkan barang baru");
    }

    System.out.println("");
  }

  // method untuk menambahkan barang baru yang belum pernah ada di gudang
  private void tambahBarangBaru(String nama, int harga, int jumlah) {
    // cek apakah barang sudah ada
    // inisialisasi index dengan -1 yang artinya barang yang dicari belum ada
    int index = -1;
    // mencari barang dengan nama yang sama seperti yang diketikkan pada input
    for (int i = 0; i < this.arrayListBarang.size(); i++) {
      if (this.arrayListBarang.get(i).getNama().equalsIgnoreCase(nama)) {
        // ketika barang ditemukan, nilai index berubah menjadi index barang yang dicari
        index = i;
        break;
      }
    }

    if (index != -1) {
      // barang yang ingin ditambahkan ternyata sudah ada di dalam gudang
      System.out.println("barang sudah ada");
    } else {
      // membuat barang(objek) baru
      System.out.println("Menambahkan barang baru...");
      Barang barangBaru = new Barang(nama, harga, jumlah);
      this.arrayListBarang.add(barangBaru);
    }
    System.out.println("");
  }
}
