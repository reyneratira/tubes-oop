package DAO;

import Entity.Produk;
import java.util.List;

public interface ProdukDAO {
    int createProduk (Produk produk) throws Exception;
    Produk readProdukId (int idProduk) throws Exception;
    List<Produk> readAllProduk() throws Exception;
    void updateProduk (Produk produk) throws Exception;
    void deleteProduk (int idProduk) throws Exception;
}