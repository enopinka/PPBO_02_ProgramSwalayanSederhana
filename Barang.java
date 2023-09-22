public class Barang {
  private String nama;
  private int harga;
  private int jumlah;

  public Barang(
      String nama,
      int harga,
      int jumlah
  ) {
    this.nama = nama;
    this.harga = harga;
    this.jumlah = jumlah;
  }

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
