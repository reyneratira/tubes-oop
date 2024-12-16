package DAO;

import Entity.DetailTransaksi;
import java.util.List;

public interface DetailTransaksiDAO {
    int createDetailTransaksi(DetailTransaksi detailTransaksi) throws Exception;
    DetailTransaksi readDetailTransaksiId(int idDetail) throws Exception;
    List<DetailTransaksi> readAllDetailTransaksi() throws Exception;
    void updateDetailTransaksi(DetailTransaksi detailTransaksi) throws Exception;
    void deleteDetailTransaksi(int idDetail) throws Exception;
}