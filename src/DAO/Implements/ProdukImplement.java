package DAO.Implements;

import DAO.ProdukDAO;
import Database.QueryHelper;
import Entity.Produk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukImplement implements ProdukDAO {

    private final Connection connection;

    public ProdukImplement(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates a new product in the database.
     *
     * @param produk the product to be created
     * @return the ID of the newly created product
     * @throws Exception if any database error occurs or if the product ID is not obtained
     */
    @Override
    public int createProduk(Produk produk) throws Exception {
        String sql = QueryHelper.CREATE_PRODUK;
        try (PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, produk.getNama());
            statement.setString(2, produk.getKategori());
            statement.setDouble(3, produk.getHarga());
            statement.setInt(4, produk.getJumlah());
            statement.setInt(5, produk.getAmbangBatas());
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            } catch (SQLException e) {
                throw new SQLException("Creating product failed: " + e.getMessage());
            }
        }
    }

    /**
     * Reads a Produk object from the database based on the provided idProduk.
     *
     * @param idProduk the ID of the Produk to be read from the database
     * @return a Produk object if found, otherwise null
     * @throws Exception if a database access error occurs or the SQL statement fails
     */
    @Override
    public Produk readProdukId(int idProduk) throws Exception {
        String sql = QueryHelper.GET_PRODUK_BY_ID;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, idProduk);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Produk(
                            resultSet.getInt("idProduk"),
                            resultSet.getString("nama"),
                            resultSet.getString("kategori"),
                            resultSet.getInt("harga"),
                            resultSet.getInt("jumlah"),
                            resultSet.getInt("ambangBatas"),
                            resultSet.getTimestamp("createdAt")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to retrieve product: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all Produk records from the database.
     *
     * @return a list of Produk objects representing all records in the database.
     * @throws Exception if a database access error occurs or the SQL statement fails.
     */
    @Override
    public List<Produk> readAllProduk() throws Exception {
        String sql = QueryHelper.GET_ALL_PRODUK;
        List<Produk> produkList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                produkList.add(new Produk(
                        resultSet.getInt("idProduk"),
                        resultSet.getString("nama"),
                        resultSet.getString("kategori"),
                        resultSet.getInt("harga"),
                        resultSet.getInt("jumlah"),
                        resultSet.getInt("ambangBatas"),
                        resultSet.getTimestamp("createdAt")
                ));
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to retrieve all products: " + e.getMessage());
        }
        return produkList;
    }

    /**
     * Updates the details of an existing product in the database.
     *
     * @param produk the product object containing updated details
     * @throws Exception if a database access error occurs or the SQL statement fails
     */
    @Override
    public void updateProduk(Produk produk) throws Exception {
        String sql = QueryHelper.UPDATE_PRODUK;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setString(1, produk.getNama());
            preparedStatement.setString(2, produk.getKategori());
            preparedStatement.setDouble(3, produk.getHarga());
            preparedStatement.setInt(4, produk.getIdProduk());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No rows updated. Product with ID " + produk.getIdProduk() + " may not exist.");
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to update product: " + e.getMessage());
        }
    }

    /**
     * Deletes a product from the database based on the provided product ID.
     *
     * @param idProduk the ID of the product to be deleted
     * @throws Exception if there is an error during the deletion process
     */
    @Override
    public void deleteProduk(int idProduk) throws Exception {
        String sql = QueryHelper.DELETE_PRODUK;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idProduk);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to delete product: " + e.getMessage());
        }
    }

}
