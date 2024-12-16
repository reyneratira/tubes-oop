package Database;

public class QueryHelper {
    // Produk Queries
    public static final String GET_ALL_PRODUK = "SELECT * FROM produk";
    public static final String GET_PRODUK_BY_ID = "SELECT * FROM produk WHERE idProduk = ?";
    public static final String CREATE_PRODUK = "INSERT INTO produk (nama, kategori, harga, jumlah, ambangBatas) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_PRODUK = "UPDATE produk SET nama = ?, kategori = ?, harga = ?, jumlah = ?, ambangBatas = ? WHERE idProduk = ?";
    public static final String DELETE_PRODUK = "DELETE FROM produk WHERE idProduk = ?";

    // // Stok Queries
    // public static final String GET_ALL_STOK = "SELECT s.idStok, s.idProduk, p.nama AS nama, s.jumlah, s.ambangBatas, s.createdAt " +
    // "FROM Stok s " +
    // "JOIN Produk p ON s.idProduk = p.idProduk"; // JOIN query with Produk table
    // public static final String GET_STOK_BY_ID = "SELECT s.idStok, s.idProduk, p.nama AS nama, s.jumlah, s.ambangBatas, s.createdAt " +
    // "FROM Stok s " +
    // "JOIN Produk p ON s.idProduk = p.idProduk " +
    // "WHERE s.idStok = ?"; // JOIN query with Produk table
    // public static final String CREATE_STOK = "INSERT INTO stok (idProduk, jumlah, ambangBatas) VALUES (?, ?, ?)";
    // public static final String UPDATE_STOK = "UPDATE stok SET jumlah = ? , ambangBatas = ? WHERE idStok = ?";
    // public static final String DELETE_STOK = "DELETE FROM stok WHERE idStok = ?";

    // Transaksi Queries
    public static final String GET_ALL_TRANSAKSI = "SELECT * FROM Transaksi";
    public static final String GET_TRANSAKSI_BY_ID = "SELECT * FROM Transaksi WHERE idTransaksi = ?";
    public static final String CREATE_TRANSAKSI = "INSERT INTO Transaksi (total, metodePembayaran) VALUES (?, ?)";
    public static final String UPDATE_TRANSAKSI = "UPDATE Transaksi SET total = ?, metodePembayaran = ?, tanggalTransaksi = ? WHERE idTransaksi = ?";
    public static final String DELETE_TRANSAKSI = "DELETE FROM Transaksi WHERE idTransaksi = ?";
    public static final String GET_TRANSAKSI_TOTAL = "SELECT SUM(total) AS total FROM DetailTransaksi WHERE idTransaksi = ?";
    public static final String UPDATE_TOTAL = "UPDATE transaksi SET total = ? WHERE idTransaksi = ?";

    // DetailTransaksi Queries
    public static final String GET_ALL_DETAIL_TRANSAKSI = "SELECT d.idDetail, d.idTransaksi, d.idProduk, p.nama AS nama, d.jumlah, d.hargaSatuan, d.subtotal" + 
    "FROM DetailTransaksi d" + 
    "JOIN Produk p ON d.idProduk = p.idProduk"; // JOIN query with Produk table
    public static final String GET_DETAIL_TRANSAKSI_BY_ID = "SELECT d.idDetail, d.idTransaksi, d.idProduk, p.nama AS nama, d.jumlah, d.hargaSatuan, d.subtotal" + 
    "FROM DetailTransaksi d" +
    "JOIN Produk p ON d.idProduk = p.idProduk" +
     "WHERE idDetail = ?";
    public static final String CREATE_DETAIL_TRANSAKSI = "INSERT INTO DetailTransaksi (idTransaksi, idProduk, jumlah, hargaSatuan, subtotal) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_DETAIL_TRANSAKSI = "UPDATE DetailTransaksi SET idProduk = ?, jumlah = ?, hargaSatuan = ?, subtotal = ? WHERE idDetail = ?";
    public static final String DELETE_DETAIL_TRANSAKSI = "DELETE FROM DetailTransaksi WHERE idDetail = ?";

    // Transaksi dan DetailTransaksi Join Queries
    public static final String GET_TRANSAKSI_WITH_DETAILS = 
        "SELECT t.idTransaksi, t.tanggalTransaksi, t.total, t.metodePembayaran, " +
        "d.idDetail, p.nama AS nama, d.jumlah, d.hargaSatuan, d.subtotal " +
        "FROM Transaksi t " +
        "JOIN DetailTransaksi d ON t.idTransaksi = d.idTransaksi " +
        "JOIN Produk p ON d.idProduk = p.idProduk";

    public static final String GET_TRANSAKSI_WITH_DETAILS_BY_ID = 
        "SELECT t.idTransaksi, t.tanggalTransaksi, t.total, t.metodePembayaran, " +
        "d.idDetail, p.nama AS nama, d.jumlah, d.hargaSatuan, d.subtotal " +
        "FROM Transaksi t " +
        "JOIN DetailTransaksi d ON t.idTransaksi = d.idTransaksi " +
        "JOIN Produk p ON d.idProduk = p.idProduk " +
        "WHERE t.idTransaksi = ?";

    public static final String GET_TOTAL_BARANG_PER_TRANSAKSI =
        "SELECT t.idTransaksi, t.tanggalTransaksi, t.metodePembayaran, " +
        "COUNT(d.idProduk) AS jumlahProduk, SUM(d.jumlah) AS totalBarang, SUM(d.subtotal) AS totalHarga " +
        "FROM Transaksi t " +
        "JOIN DetailTransaksi d ON t.idTransaksi = d.idTransaksi " +
        "GROUP BY t.idTransaksi, t.tanggalTransaksi, t.metodePembayaran";

    // Prevent instantiation
    private QueryHelper() {}
}