package service;

import entity.Buku;
import repository.BukuRepository;
import repository.BukuRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

import static utils.BookPrinter.printOneBook;
import static utils.ConsoleColors.*;
import static utils.BookPropertiesSubString.subString;
import static validation.BukuValidation.*;

public class BukuServiceImpl implements BukuService{
    private final BukuRepository bukuRepository = new BukuRepositoryImpl();

    @Override
    public void showBuku() {
        ArrayList<Buku> dataBuku = bukuRepository.getAll();
        if(dataBuku.isEmpty()) {
            printWarning("Data Buku Masih Kosong");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        printMedia(" No. \t Judul \t\t\t\t    Penulis \t\t          Penerbit \t\t      Tahun Terbit\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        int nomorBuku = 1;
        for (var buku : dataBuku) {
            String[] bookProperties = subString(buku);

            System.out.printf("  %-2s   ", nomorBuku);
            System.out.printf("%-35s", bookProperties[0]);
            System.out.printf("%-30s", bookProperties[1]);
            System.out.printf("%-34s", bookProperties[2]);
            System.out.printf("%d\n", buku.getTahunTerbit());
            nomorBuku++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    private int getNomorBukuDuplicate(Buku buku){
        ArrayList<Buku> allBooks = this.bukuRepository.getAll();
        int urutanBuku = 1;
        for(var book : allBooks){
            if(
                    book.getJudul().equalsIgnoreCase(buku.getJudul())       &&
                    book.getPenerbit().equalsIgnoreCase(buku.getPenerbit()) &&
                    book.getPenulis().equalsIgnoreCase(buku.getPenulis())   &&
                    book.getTahunTerbit() == buku.getTahunTerbit()
            ) {
                break;
            }
            urutanBuku++;
        }
        return urutanBuku;
    }

    @Override
    public void addBuku(Buku buku) {
        if(isDataBukuNotValid(buku)) {
            printError("Data Buku Tidak Valid!\n");
            return;
        } else if(isDataBukuDuplicate(buku)){
            printWarning("Buku Sudah Ada di Database dengan Nomor Buku " + this.getNomorBukuDuplicate(buku));
            return;
        }

        try {
            String[] bookProperties = subString(buku);

            this.bukuRepository.add(buku);

            System.out.println("\n\t\t\t\t\t\t  DATA BUKU BARU");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            printMedia("\tJudul \t\t\t\t    Penulis \t\t          Penerbit \t\t      Tahun Terbit\n");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.printf("\t%-36s", bookProperties[0]);
            System.out.printf("%-30s", bookProperties[1]);
            System.out.printf("%-32s", bookProperties[2]);
            System.out.printf("%d\n\n", buku.getTahunTerbit());
            printSuccess("BUKU BERHASIL DITAMBAHKAN\n");
        } catch (IOException e){
            printError("Terjadi Error : " + e.getMessage());
        }
    }

    @Override
    public void searchBooks(String keywords) {
        ArrayList<Buku> booksFound = this.bukuRepository.searchBooks(keywords);
        if(booksFound.isEmpty()) {
            printWarning("Data Buku dengan Keyword " + keywords + " Tidak Ditemukan");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        printMedia(" No. \t Judul \t\t\t\t    Penulis \t\t        Penerbit \t\t      Tahun Terbit\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        ArrayList<Integer> nomorBuku = this.bukuRepository.indexSearchBuku(keywords);
        int index = 0;
        for (Buku bookFound : booksFound) {
            String[] bookProperties = subString(bookFound);

            System.out.printf("  %-2s   ", nomorBuku.get(index));
            System.out.printf("%-35s", bookProperties[0]);
            System.out.printf("%-30s", bookProperties[1]);
            System.out.printf("%-34s", bookProperties[2]);
            System.out.printf("%d\n", bookFound.getTahunTerbit());
            index++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void removeBuku(int nomorBuku) {
        if(!isNomorBukuValid(nomorBuku)){
            printError("Nomor Buku Tidak Valid");
            printError("Gagal Memperbarui Buku!\n");
        } else {
            try {
                // Ambil buku yang dihapus
                Buku bookDeleted = this.bukuRepository.getAll().get(nomorBuku - 1);

                // Hapus buku
                this.bukuRepository.remove(nomorBuku);

                // Menampilkan buku yang dihapus
                System.out.println("\n\t\t\t\t\t    DATA BUKU YANG DIHAPUS");
                printOneBook(bookDeleted);

                // Memberikan message buku telah berhasil dihapus
                printSuccess("Buku Di Atas Berhasil Dihapus\n");
            } catch (Exception e){
                printError("Terjadi Error : " + e.getMessage());
            }
        }

    }

    @Override
    public void updateDuku(int nomorBuku, Buku buku) {
        if(isDataBukuNotValid(buku)) {
            printError("Data Buku Tidak Valid!");
            printError("Gagal Memperbarui Buku!\n");
            return;
        } else if(isDataBukuDuplicate(buku)){
            printWarning("Buku Sudah Ada di Database dengan Nomor Buku " + this.getNomorBukuDuplicate(buku));
            printError("Gagal Memperbarui Buku!\n");
            return;
        }

        try {
            // Ambil buku yang akan diupdate
            Buku bookUpdate = this.bukuRepository.getAll().get(nomorBuku - 1);

            // Update buku
            this.bukuRepository.update(nomorBuku, buku);

            // Menampilkan buku sebelum dan setelah diupdate
            System.out.println("\n\t\t\t\t\t     DATA BUKU SEBELUM DIPERBARUI");
            printOneBook(bookUpdate);
            System.out.println("\t\t\t\t\t     DATA BUKU SETELAH DIPERBARUI");
            printOneBook(buku);

            // Notifikasi jika berhasil mengupdate buku
            printSuccess("Buku di Atas Berhasil Diperbarui\n");
        } catch (Exception e){
            printError("Terjadi Error : " + e.getMessage());
        }
    }
}
