package test.repository;

import entity.Buku;
import repository.BukuRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TestBukuRepository {
    public static void main(String[] args) throws SQLException {
//        System.out.println(isBukuFound("Bumi") ? "Buku Ditemukan" : "Buku Tidak Ditemukan");
        ArrayList<Integer> indexes = indexSearchBook("ma");
        Buku book = null;
        for(var i : indexes){
            book = getBookByOrder(i);
            System.out.println("Nomor Buku      : " + i);
            System.out.println("Judul Buku      : " + book.getJudul());
            System.out.println("Penulis Buku    : " + book.getPenulis());
            System.out.println("Penerbit Buku   : " + book.getPenerbit());
            System.out.println("Tahun Terbit    : " + book.getTahunTerbit() + "\n");
        }
    }

    private static void testAddBuku() throws  SQLException {
        BukuRepositoryImpl bukuRepo = new BukuRepositoryImpl();
        Buku buku = new Buku("Aku yang terluka","Abdul Dudul","Balai Pustaka",1928);
        bukuRepo.add(buku);
    }

    private static boolean isBukuFound(String keyword){
        return (new BukuRepositoryImpl()).isBukuFound(keyword);
    }

    private static ArrayList<Integer> indexSearchBook(String keyword){
        return (new BukuRepositoryImpl()).indexSearchBuku(keyword);
    }

    private static Buku getBookByOrder(int order) throws SQLException {
        return (new BukuRepositoryImpl()).getBukuByOrder(order);
    }

}
