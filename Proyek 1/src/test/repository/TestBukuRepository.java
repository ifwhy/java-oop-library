package test.repository;

import entity.Buku;
import repository.BukuRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class TestBukuRepository {
    public static void main(String[] args) {
//        try {
//            testAddBuku();
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
        testSearchBuku("ma");
    }

    private static void testAddBuku() throws IOException {
        BukuRepositoryImpl bukuRepo = new BukuRepositoryImpl();
        Buku buku = new Buku(String.valueOf(UUID.randomUUID()), "Salah Asuhan","Abdoel Moeis","Balai Pustaka",1928);
        bukuRepo.add(buku);
    }

    private static void testSearchBuku(String keyword){
        BukuRepositoryImpl bukuRepo = new BukuRepositoryImpl();
        ArrayList<Buku> bukuFound = bukuRepo.searchBooks(keyword);

        int index = 0;
        for (Buku buku : bukuFound) {
            System.out.println("Nomor Buku      : " + bukuRepo.indexSearchBuku(keyword).get(index));
            System.out.println("Judul Buku      : " + buku.getJudul());
            System.out.println("Penulis Buku    : " + buku.getPenulis());
            System.out.println("Penerbit Buku   : " + buku.getPenerbit());
            System.out.println("Tahun Terbit    : " + buku.getTahunTerbit() + "\n");
            index++;
        }
    }

    private static void testDeleteBuku() throws IOException {
        BukuRepositoryImpl bukuRepo = new BukuRepositoryImpl();
        bukuRepo.remove(3);
    }

    private static void testUpdateBuku() throws IOException {
        BukuRepositoryImpl bukuRepo = new BukuRepositoryImpl();
        bukuRepo.update(1, new Buku("ab2f7436-1d2e-4ba9-8d30-5f71d0f5d7c3","Arus Balik","Pramudya Ananta Toer","Gramedia",1995));
    }
}
