package utils;

import entity.Buku;

public class BookPropertiesSubString {

    public static String[] subString(Buku buku){
        String judulBuku = buku.getJudul().length() > 25 ? buku.getJudul().substring(0, 25) + "..." : buku.getJudul();
        String penulisBuku = buku.getPenulis().length() > 25 ? buku.getPenulis().substring(0, 25) + "..." : buku.getPenulis();
        String penerbitBuku = buku.getPenerbit().length() > 25 ? buku.getPenerbit().substring(0, 25) + "..." : buku.getPenerbit();

        return new String[]{judulBuku, penulisBuku, penerbitBuku};
    }
}
