package Entity;

import java.util.Date;

public class Produk {
    private int idProduk;
    private String nama;
    private String kategori;
    private double harga;
    private int jumlah;
    private int ambangBatas;
    private Date createdAt;

    /**
     * Default constructor for the Produk class.
     */
    public Produk() {}

    // For new Produk
    /**
     * Constructs a new Produk object with the specified name, category, price, and creation date.
     * To be used for creating a new Produk in the database.
     * idProduk is automatically incremented in database.
     * createdAt is defaulted to the current timestamp.
     *
     * @param nama the name of the product
     * @param kategori the category of the product
     * @param harga the price of the product
     * @param createdAt the date the product was created
     * @param jumlah the quantity of product in stock
     * @param ambangBatas the threshold limit for the stock
     */
    public Produk(String nama, String kategori, double harga, int jumlah, int ambangBatas) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.ambangBatas = ambangBatas;
    }


    /**
     * Constructs an existing Produk object with the specified details. For Update Query.
     * To be used for updating an existing Produk in the database.
     *
     * @param idProduk the unique identifier for the product
     * @param nama the name of the product
     * @param kategori the category of the product
     * @param harga the price of the product
     * @param jumlah the quantity of product in stock
     * @param ambangBatas the threshold limit for the stock
     */

    public Produk(int idProduk, String nama, String kategori, double harga, int jumlah, int ambangBatas) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.ambangBatas = ambangBatas;
    }

    // For existing Produk
    /**
     * Constructs an existing Produk object with the specified details.
     * to be used for reading existing Produk in the database.
     *
     * @param idProduk the unique identifier for the product
     * @param nama the name of the product
     * @param kategori the category of the product
     * @param harga the price of the product
     * @param jumlah the quantity of product in stock
     * @param ambangBatas the threshold limit for the stock
     * @param createdAt the date when the product was created
     */
    public Produk(int idProduk, String nama, String kategori, double harga, int jumlah, int ambangBatas, Date createdAt) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.ambangBatas = ambangBatas;
        this.createdAt = createdAt;
    }

    // Getters
    public int getIdProduk() {
        return idProduk;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }
    
    public int getAmbangBatas() {
        return ambangBatas;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // Setters

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setharga(int harga) {
        this.harga = harga;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setAmbangBatas(int ambangBatas) {
        this.ambangBatas = ambangBatas;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Produk [idProduk=" + idProduk + ", nama=" + nama + ", kategori=" + kategori + ", harga=" + harga
                + ", jumlah=" + jumlah + ", ambangBatas=" + ambangBatas + ", createdAt=" + createdAt + "]";
    }
}
