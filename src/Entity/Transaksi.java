package Entity;

import java.util.Date;

public class Transaksi {
    private int idTransaksi;;
    private Date tanggalTransaksi;
    private double total;
    private String metodePembayaran;

    /**
     * Default constructor for the Transaksi class.
     */
    public Transaksi(){}

    /**
     * For new Transaksi
     * Constructs a new Transaksi object with the specified transaction date, total amount, and payment method.
     * idTransaksi is automatically incremented in database.
     *
     * @param tanggalTransaksi the date of the transaction
     * @param total the total amount of the transaction
     * @param metodePembayaran the payment method used for the transaction
     */
    public Transaksi(Date tanggalTransaksi, double total, String metodePembayaran) {
        this.tanggalTransaksi = tanggalTransaksi;
        this.total = total;
        this.metodePembayaran = metodePembayaran;
    }

    /**
     * For existing Transaksi
     * Constructs an existing Transaksi object with the specified details.
     *
     * @param idTransaksi the unique identifier for the transaction
     * @param tanggalTransaksi the date of the transaction
     * @param total the total amount of the transaction
     * @param metodePembayaran the payment method used for the transaction
     */
    public Transaksi(int idTransaksi, Date tanggalTransaksi, double total, String metodePembayaran) {
        this.idTransaksi = idTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.total = total;
        this.metodePembayaran = metodePembayaran;
    }

    // Getters

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public double getTotal() {
        return total;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }


    // Setters

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    @Override
    public String toString() {
        return "Transaksi [idTransaksi=" + idTransaksi + ", tanggalTransaksi=" + tanggalTransaksi + ", total=" + total
                + ", metodePembayaran=" + metodePembayaran + "]";
    }

    
}
