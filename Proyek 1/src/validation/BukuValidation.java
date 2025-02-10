package validation;

import entity.Buku;
import repository.BukuRepository;
import repository.BukuRepositoryImpl;

import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class BukuValidation {

    public static boolean isTahunTerbitValid(String tahunTerbit){
        try {
            Year.parse(tahunTerbit);
            return Integer.parseInt(tahunTerbit) > 0 && Integer.parseInt(tahunTerbit) <= 9999;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Pencarian buku jika judul, penulis, penerbit, atau tahun terbit mengandung keyword
    public static boolean containKeyword(Buku book, String keyword){
        return  book.getJudul().toLowerCase().contains(keyword)         ||
                book.getPenulis().toLowerCase().contains(keyword)       ||
                book.getPenerbit().toLowerCase().contains(keyword)      ||
                String.valueOf(book.getTahunTerbit()).contains(keyword);
    }

    public static boolean isDataBukuNotValid(Buku buku) {
        return  buku.getJudul().isBlank()         ||
                buku.getPenerbit().isBlank()      ||
                buku.getPenulis().isBlank()       ||
                !isTahunTerbitValid(String.valueOf(buku.getTahunTerbit()));
    }

    // Validasi untuk memastikan data buku apakah terjadi duplikat
    public static boolean isDataBukuDuplicate(Buku buku){
        BukuRepository bukuRepository = new BukuRepositoryImpl();
        ArrayList<Buku> allBooks = bukuRepository.getAll();

        boolean isDuplicate = false;
        for(var book : allBooks){
            if(
                    book.getJudul().equalsIgnoreCase(buku.getJudul())       &&
                    book.getPenerbit().equalsIgnoreCase(buku.getPenerbit()) &&
                    book.getPenulis().equalsIgnoreCase(buku.getPenulis())   &&
                    book.getTahunTerbit() == buku.getTahunTerbit()
            ) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

    public static boolean isNomorBukuValid(int nomorBuku){
        BukuRepository bukuRepository = new BukuRepositoryImpl();
        int bookCount = bukuRepository.getAll().size();
        return nomorBuku > 0 && nomorBuku <= bookCount;
    }

}
