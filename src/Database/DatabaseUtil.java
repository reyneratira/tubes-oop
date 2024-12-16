package Database;

import java.sql.*;

public class DatabaseUtil {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/oop";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static Connection connection = null;

    /**
     * Establishes a connection to the database.
     * 
     * This method attempts to establish a connection to the database using the
     * provided database URL, username, and password. If the connection is already
     * established and open, it returns the existing connection. Otherwise, it
     * creates a new connection.
     * 
     * @return a {@link Connection} object representing the database connection, or
     *         {@code null} if a connection could not be established.
     * @throws Exception if an error occurs while attempting to connect to the
     *                   database.
     */
    public static Connection connect() throws Exception {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                return connection;
            } catch (SQLException e) {
                System.err.println("Failed to connect to the database: " + e.getMessage());
                return null;
            }
        }
        return connection;
    }

    /**
     * Closes the provided database resources (Connection, PreparedStatement, and ResultSet).
     * Each resource is closed in the following order: ResultSet, PreparedStatement, Connection.
     * If an exception occurs while closing a resource, an error message is printed to the standard error stream.
     *
     * @param connection the Connection to be closed, may be null
     * @param preparedStatement the PreparedStatement to be closed, may be null
     * @param resultSet the ResultSet to be closed, may be null
     */
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            System.err.println("Failed to close ResultSet: " + e.getMessage());
        }

        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Failed to close PreparedStatement: " + e.getMessage());
        }

        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println("Failed to close Connection: " + e.getMessage());
        }
    }

    /**
     * Closes the given database resources.
     *
     * @param connection the database connection to be closed
     * @param statement the prepared statement to be closed
     */
    public static void close(Connection connection, PreparedStatement statement) {
        close(connection, statement, null);
    }

    /**
     * Closes the given {@link PreparedStatement} if it is not null.
     * 
     * @param preparedStatement the {@link PreparedStatement} to be closed
     * 
     * @throws SQLException if an SQL error occurs while closing the {@link PreparedStatement}
     */
    public static void close(PreparedStatement preparedStatement) {

        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Failed to close PreparedStatement: " + e.getMessage());
        }
    }
}
