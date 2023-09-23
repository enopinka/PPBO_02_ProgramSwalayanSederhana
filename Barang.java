// Nama : Retno Pinka Pratiwi
// NIM  : L012237

//kelas barang
public class Barang {
  // atribut-atribut
  private String nama;
  private int harga;
  private int jumlah;

  // constructor
  public Barang(
      String nama,
      int harga,
      int jumlah) {
    this.nama = nama;
    this.harga = harga;
    this.jumlah = jumlah;
  }

  // method-method getter dan setter untuk mengambil nama, jumlah, dan harga
  public String getNama() {
    return nama;
  }

  public int getJumlah() {
    return jumlah;
  }

  public void setJumlah(int jumlah) {
    this.jumlah = jumlah;
  }

  public int getHarga() {
    return harga;
  }
}
