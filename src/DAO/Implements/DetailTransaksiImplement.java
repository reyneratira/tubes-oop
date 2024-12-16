package DAO.Implements;
import DAO.DetailTransaksiDAO;
import Database.QueryHelper;
import Entity.DetailTransaksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailTransaksiImplement implements DetailTransaksiDAO {

    private final Connection connection;

    public DetailTransaksiImplement(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createDetailTransaksi(DetailTransaksi detailTransaksi) throws Exception {
        String sql = QueryHelper.CREATE_DETAIL_TRANSAKSI;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, detailTransaksi.getIdTransaksi());
            preparedStatement.setInt(2, detailTransaksi.getIdProduk());
            preparedStatement.setInt(3, detailTransaksi.getJumlah());
            preparedStatement.setDouble(4, detailTransaksi.getHargaSatuan());
            preparedStatement.setDouble(5, detailTransaksi.getSubtotal());
            preparedStatement.executeUpdate();

            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating detail transaksi failed, no ID obtained.");
                }

            } catch (SQLException e){
                throw new SQLException("Creating detail transaksi failed: " + e.getMessage());
            }
        } catch (SQLException e){
            throw new SQLException("Failed to create detail transaksi: " + e.getMessage());
        }
    }

    @Override
    public DetailTransaksi readDetailTransaksiId(int idDetail) throws Exception {
        String sql = QueryHelper.GET_DETAIL_TRANSAKSI_BY_ID;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idDetail);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new DetailTransaksi(
                        resultSet.getInt("idDetail"),
                        resultSet.getInt("idTransaksi"),
                        resultSet.getInt("idProduk"),
                        resultSet.getString("nama"),
                        resultSet.getInt("jumlah"),
                        resultSet.getDouble("hargaSatuan"),
                        resultSet.getDouble("subtotal")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<DetailTransaksi> readAllDetailTransaksi() throws Exception {
        String sql = QueryHelper.GET_ALL_DETAIL_TRANSAKSI;
        List<DetailTransaksi> detailTransaksiList = new ArrayList<>();
        try(Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                DetailTransaksi detailTransaksi = new DetailTransaksi(
                    resultSet.getInt("idDetail"),
                    resultSet.getInt("idTransaksi"),
                    resultSet.getInt("idProduk"),
                    resultSet.getString("nama"),
                    resultSet.getInt("jumlah"),
                    resultSet.getDouble("hargaSatuan"),
                    resultSet.getDouble("subtotal")
                );
                detailTransaksiList.add(detailTransaksi);
            }
            return detailTransaksiList;
        }
    }

    @Override
    public void updateDetailTransaksi(DetailTransaksi detailTransaksi) throws Exception {
        String sql = QueryHelper.UPDATE_DETAIL_TRANSAKSI;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, detailTransaksi.getIdProduk());
            preparedStatement.setInt(2, detailTransaksi.getJumlah());
            preparedStatement.setDouble(3, detailTransaksi.getHargaSatuan());
            preparedStatement.setDouble(4, detailTransaksi.getSubtotal());
            preparedStatement.setInt(5, detailTransaksi.getIdDetail());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0){
                throw new SQLException("Failed to update rows. Detail transaksi with ID " + detailTransaksi.getIdDetail() + " may not exist.");
            }
        } catch (SQLException e){
            throw new SQLException("Failed to update detail transaksi: " + e.getMessage());
        }
    }

    @Override
    public void deleteDetailTransaksi(int idDetail) throws Exception {
        String sql = QueryHelper.DELETE_DETAIL_TRANSAKSI;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idDetail);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Failed to delete detail transaksi: " + e.getMessage());
        }
    }

}