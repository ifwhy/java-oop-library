package test.connection;

import entity.Buku;
import repository.BukuRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        testAddBuku(new Buku("Aku Kecewa", "Wahyu Nugroho", "Gramedia", 2025));
//        testUpdateBuku(14, new Buku("Aku Menangis", "Ivan Wahyu Nugroho", "Gramedia", 2025));
//        testDeleteBuku(14);
//        testDeleteBuku(14);
        ArrayList<Buku> books = testReadALlBooks();
        int urutanBuku = 1;
        for(var book : books){
            System.out.println("No.             : " + urutanBuku);
            System.out.println("Judul           : " + book.getJudul());
            System.out.println("Penulis         : " + book.getPenulis());
            System.out.println("Tahun terbit    : " + book.getTahunTerbit() + "\n");
            urutanBuku++;
        }
    }

    private static ArrayList<Buku> testReadALlBooks(){
        return (new BukuRepositoryImpl()).getAll();
    }

    private static void testAddBuku(Buku buku) throws SQLException {
        BukuRepositoryImpl bukuRepo = new BukuRepositoryImpl();
        bukuRepo.add(buku);
    }

//    private static Buku testGetBook(int nomor){
//        return (new BukuRepositoryImpl()).getBukuByIndex(nomor);
//    }

    private static void testUpdateBuku(int urutanBuku, Buku bukuUpdated) throws SQLException {
        (new BukuRepositoryImpl()).update(urutanBuku, bukuUpdated);
    }

    private static void testDeleteBuku(int urutanBuku) throws SQLException {
        (new BukuRepositoryImpl()).remove(urutanBuku);
    }
}
