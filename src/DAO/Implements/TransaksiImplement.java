package DAO.Implements;

import DAO.TransaksiDAO;
import Database.QueryHelper;
import Entity.Transaksi;
import java.sql.*;

public class TransaksiImplement implements TransaksiDAO {

    private final Connection connection;

    public TransaksiImplement(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createTransaksi(Transaksi transaksi) throws Exception {
        String sql = QueryHelper.CREATE_TRANSAKSI;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setDouble(1, 0.0);
            preparedStatement.setString(2, transaksi.getMetodePembayaran());
            preparedStatement.executeUpdate();

            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating transaksi failed, no ID obtained.");
                }
            } catch (SQLException e){
                throw new SQLException("Creating transaksi failed: " + e.getMessage());
            }
        } catch (SQLException e){
            throw new SQLException("Failed to create transaksi: " + e.getMessage());
        }
    }

    @Override
    public void deleteTransaksi(int idTransaksi) throws Exception {
        String sql = QueryHelper.DELETE_TRANSAKSI;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idTransaksi);
            int rowsDeleted = preparedStatement.executeUpdate();
            if(rowsDeleted == 0){
                throw new SQLException("Failed to delete transaksi. Transaksi with ID " + idTransaksi + " may not exist.");
    }
        } catch (SQLException e){
            throw new SQLException("Failed to delete transaksi: " + e.getMessage());
        }
    }

    @Override
    public void updateTransaksi(Transaksi transaksi) throws Exception {
        String sql = QueryHelper.UPDATE_TRANSAKSI;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setDouble(1, transaksi.getTotal());
            preparedStatement.setString(2, transaksi.getMetodePembayaran());
            preparedStatement.setInt(3, transaksi.getIdTransaksi());
            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated == 0){
                throw new SQLException("Failed to update transaksi. Transaksi with ID " + transaksi.getIdTransaksi() + " may not exist.");
            }
        } catch (SQLException e){
            throw new SQLException("Failed to update transaksi: " + e.getMessage());
        }
    }

    @Override
    public void calculateTotal(Transaksi transaksi) throws Exception {
        String sql = QueryHelper.GET_TRANSAKSI_TOTAL;
        double total;
        try(PreparedStatement calcStatement = this.connection.prepareStatement(sql)){
            calcStatement.setInt(1, transaksi.getIdTransaksi());
            ResultSet rs = calcStatement.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            } else {
                throw new SQLException("Failed to calculate total. Transaksi with ID " + transaksi.getIdTransaksi() + " may not exist.");
            }
        } catch (SQLException e){
            throw new SQLException("Failed to calculate total: " + e.getMessage());
        }

        String updateSql = QueryHelper.UPDATE_TOTAL;
        try(PreparedStatement updateStatement = this.connection.prepareStatement(updateSql)){
            updateStatement.setDouble(1, total);
            updateStatement.setInt(2, transaksi.getIdTransaksi());
            int rowsUpdated = updateStatement.executeUpdate();
            if(rowsUpdated == 0){
                throw new SQLException("Failed to update total. Transaksi with ID " + transaksi.getIdTransaksi() + " may not exist.");
            }
        } catch (SQLException e){
            throw new SQLException("Failed to update total: " + e.getMessage());
        }
    }
}
