package DAO;

import Entity.Transaksi;

public interface TransaksiDAO {
    int createTransaksi(Transaksi transaksi) throws Exception;
    void updateTransaksi(Transaksi transaksi) throws Exception;
    void calculateTotal(Transaksi transaksi) throws Exception;
    void deleteTransaksi(int idTransaksi) throws Exception;
}