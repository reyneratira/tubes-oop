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

    /**
     * Creates a new transaksi record in the database.
     *
     * @param transaksi The Transaksi object containing the details of the transaction to be created.
     * @return The generated ID of the newly created transaksi.
     * @throws Exception If an error occurs while creating the transaksi.
     */
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

    /**
     * Deletes a transaksi record from the database based on the provided transaksi ID.
     *
     * @param idTransaksi the ID of the transaksi to be deleted
     * @throws Exception if there is an error during the deletion process
     */
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

    /**
     * Updates an existing transaksi in the database.
     *
     * @param transaksi the Transaksi object containing updated data
     * @throws Exception if an error occurs during the update process
     */
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

    /**
     * Calculates the total for a given Transaksi and updates the total in the database.
     *
     * @param transaksi The Transaksi object for which the total is to be calculated.
     * @throws Exception If there is an error during the calculation or update process.
     *
     * This method performs the following steps:
     * 1. Prepares a SQL statement to retrieve the total amount for the given Transaksi ID.
     * 2. Executes the query and retrieves the total amount from the result set.
     * 3. Throws an SQLException if the Transaksi ID does not exist or if there is an error during the query execution.
     * 4. Prepares a SQL statement to update the total amount for the given Transaksi ID.
     * 5. Executes the update statement and checks if any rows were updated.
     * 6. Throws an SQLException if the update fails or if the Transaksi ID does not exist.
     */
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
