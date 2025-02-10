package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/perpustakaan";
    private static final String USER = "root"; // Ubah jika perlu
    private static final String PASSWORD = ""; // Ubah jika perlu

    /**
     * Establishes a connection to the MySQL database.
     * @return Connection object if successful
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Gagal membuat koneksi ke database: " + e.getMessage());
        }
    }


    /**
     * Closes the given connection safely.
     * @param connection the connection to close
     */
    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new SQLException(e.getMessage());
            }
        }
    }
}
