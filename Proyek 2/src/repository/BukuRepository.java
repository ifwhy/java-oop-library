package repository;

import entity.Buku;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BukuRepository {
    ArrayList<Buku> getAll();

    ArrayList<Buku> searchBooks(String keyword);

    boolean isBukuFound(String keyword);

    ArrayList<Integer> indexSearchBuku(String keyword);

    void add(Buku buku) throws SQLException;

    void update(int nomorBuku, Buku buku) throws SQLException;

    void remove(int nomorBuku) throws SQLException;
}
