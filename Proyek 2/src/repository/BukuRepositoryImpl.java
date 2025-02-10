package repository;

import connection.ConnectionMySQL;
import entity.Buku;
import static validation.BukuValidation.*;

import static utils.ConsoleColors.*;

import java.sql.*;
import java.util.ArrayList;

public class BukuRepositoryImpl implements  BukuRepository {
    private final ArrayList<Buku> books = new ArrayList<>();
    private Connection connection = null;
    private int affectedRows;

    public BukuRepositoryImpl(){
        try {
            this.connection = ConnectionMySQL.getConnection();
        } catch (SQLException e){
            printError(e.getMessage());
        }
        this.readAll();
    }

    public int getAffectedRows() {
        return this.affectedRows;
    }

    private void readAll() {
        this.books.clear(); // Menghapus semua data di list sebelum mengisi dari database
        try {
            // Membuat statement untuk membaca data
            String query = "SELECT id, judul, penulis, penerbit, tahun_terbit FROM buku";
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Membaca setiap baris hasil query
            while (resultSet.next()) {
                // Membuat objek Buku dari data dalam database
                Buku buku = new Buku();
                buku.setJudul(resultSet.getString("judul"));
                buku.setPenulis(resultSet.getString("penulis"));
                buku.setPenerbit(resultSet.getString("penerbit"));
                buku.setTahunTerbit(resultSet.getInt("tahun_terbit"));

                // Menambahkan buku ke dalam list
                this.books.add(buku);
            }
        } catch (SQLException e) {
            printError("Terjadi kesalahan saat membaca dari database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Buku> getAll() {
        this.readAll();
        return this.books;
    }

    @Override
    public ArrayList<Buku> searchBooks(String keyword) {
        ArrayList<Buku> booksFound = new ArrayList<>();
        for (var book : this.books){
            if(containKeyword(book, keyword)) booksFound.add(book);
        }
        return booksFound;
    }

    @Override
    public boolean isBukuFound(String keyword) {
        this.readAll();
        for(var book : this.books){
            if(book.getJudul().contains(keyword) || book.getPenulis().contains(keyword) || book.getPenerbit().contains(keyword) || String.valueOf(book.getTahunTerbit()).contains(keyword)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Integer> indexSearchBuku(String keyword){
        int index = 1;
        ArrayList<Integer> bookIndex = new ArrayList<>();
        for (var book : this.books){
            if(containKeyword(book, keyword)) bookIndex.add(index);
            index++;
        }
        return bookIndex;
    }

    @Override
    public void add(Buku buku) throws SQLException {
        String insertQuery = "INSERT INTO buku (judul, penulis, penerbit, tahun_terbit) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, buku.getJudul());
        preparedStatement.setString(2, buku.getPenulis());
        preparedStatement.setString(3, buku.getPenerbit());
        preparedStatement.setInt(4, buku.getTahunTerbit());

        this.affectedRows = preparedStatement.executeUpdate();
        this.readAll();
    }

    @Override
    public void update(int nomorBuku, Buku buku) throws SQLException {
        String removeQuery = "UPDATE buku SET judul = ?, penulis = ?, penerbit = ?, tahun_terbit = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);
        preparedStatement.setString(1, buku.getJudul());
        preparedStatement.setString(2, buku.getPenulis());
        preparedStatement.setString(3, buku.getPenerbit());
        preparedStatement.setInt(4, buku.getTahunTerbit());
        preparedStatement.setString(5, String.valueOf(getBukuByOrder(nomorBuku).getId()));

        this.affectedRows = preparedStatement.executeUpdate();
        this.readAll();
    }

    @Override
    public void remove(int nomorBuku) throws SQLException {
        String removeQuery = "DELETE FROM buku WHERE id =  ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(removeQuery);
        preparedStatement.setString(1, String.valueOf(getBukuByOrder(nomorBuku).getId()));
        this.affectedRows = preparedStatement.executeUpdate();
        this.readAll();
    }

    public Buku getBukuByOrder(int index) throws SQLException {
        Buku buku = null;
        // Query untuk mengambil buku berdasarkan urutan baris
        String query = "SELECT * FROM buku ORDER BY id LIMIT 1 OFFSET ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        // Offset ditentukan berdasarkan urutan baris (indeks dimulai dari 0)
        preparedStatement.setInt(1, index - 1);  // Offset menggunakan zero-based index

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            // Mengambil data dan membuat objek Buku
            buku = new Buku(
                    resultSet.getInt("id"),
                    resultSet.getString("judul"),
                    resultSet.getString("penulis"),
                    resultSet.getString("penerbit"),
                    resultSet.getInt("tahun_terbit")
            );
        }
        return buku;
    }
}
