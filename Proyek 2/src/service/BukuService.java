package service;

import entity.Buku;

public interface BukuService {
    void showBuku();

    void addBuku(Buku buku);

    void searchBooks(String keywords);

    void removeBuku(int nomorBuku);

    void updateDuku(int nomorBuku, Buku buku);
}
