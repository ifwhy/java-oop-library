package test.entity;

import entity.Buku;

import java.io.IOException;

public class testBuku {
    public static void main(String[] args) throws IOException {
        Buku buku1 = testConstructor();
        System.out.println(buku1.getId());
        System.out.println(buku1.getJudul());
        System.out.println(buku1.getPenerbit());
        System.out.println(buku1.getPenulis());
        System.out.println(buku1.getTahunTerbit());
    }

    private static Buku testConstructor() throws IOException {
        return new Buku();
    }
}
