package Entity;

public class DetailTransaksi {
    private int idDetail;
    private int idTransaksi;
    private int idProduk;
    private String nama;
    private int jumlah;
    private double hargaSatuan;
    private double subtotal;

    /**
     * Default constructor for the DetailTransaksi class.
     */
    public DetailTransaksi() {}

    /**
     * For new DetailTransaksi
     * Constructs a new DetailTransaksi object with the specified transaction id, product id, quantity, price, and total.
     * idDetail is automatically incremented in database.
     *
     * @param idTransaksi the unique identifier for the transaction
     * @param idProduk the unique identifier for the product
     * @param jumlah the quantity of the product
     * @param harga the price of the product
     * @param total the total amount of the product
     */
    public DetailTransaksi(int idTransaksi, int idProduk, int jumlah, double hargaSatuan, double subtotal) {
        this.idTransaksi = idTransaksi;
        this.idProduk = idProduk;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.subtotal = subtotal;
    }

    /**
     * For existing DetailTransaksi
     * Constructs an existing DetailTransaksi object with the specified details.
     *
     * @param idDetail the unique identifier for the detail transaction
     * @param idTransaksi the unique identifier for the transaction
     * @param idProduk the unique identifier for the product
     * @param nama the name of the product
     * @param jumlah the quantity of the product
     * @param harga the price of the product
     * @param total the total amount of the product
     */
    public DetailTransaksi(int idDetail, int idTransaksi, int idProduk, String nama, int jumlah, double hargaSatuan, double subtotal) {
        this.idDetail = idDetail;
        this.idTransaksi = idTransaksi;
        this.idProduk = idProduk;
        this.nama = nama;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.subtotal = subtotal;
    }

    // Getters

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public String getNama() {
        return nama;
    }

    // Setters

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "DetailTransaksi [idDetail=" + idDetail + ", idTransaksi=" + idTransaksi + ", idProduk=" + idProduk
                + ", jumlah=" + jumlah + ", hargaSatuan=" + hargaSatuan + ", subtotal=" + subtotal + "]";
    }
}
