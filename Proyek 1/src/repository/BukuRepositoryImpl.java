package repository;

import entity.Buku;
import static validation.BukuValidation.*;

import static utils.ConsoleColors.*;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BukuRepositoryImpl implements  BukuRepository {
    private final ArrayList<Buku> books = new ArrayList<>();

    public BukuRepositoryImpl(){
        this.readAll();
    }

    private void readAll() {
        this.books.clear();
        try (
                FileReader fileInput = new FileReader("db.txt");
                BufferedReader reader = new BufferedReader(fileInput)
        ) {
            String line = reader.readLine();

            // Membaca setiap baris dalam file
            while (line != null) {
                StringTokenizer bookData = new StringTokenizer(line, "#");

                // Membuat objek Buku dari data dalam file
                Buku buku = new Buku();
                buku.setId(bookData.nextToken());
                buku.setJudul(bookData.nextToken());
                buku.setPenulis(bookData.nextToken());
                buku.setPenerbit(bookData.nextToken());
                buku.setTahunTerbit(Integer.parseInt(bookData.nextToken()));

                // Menambahkan buku ke dalam list
                this.books.add(buku);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            printError("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            printError("Terjadi kesalahan saat membaca file: " + e.getMessage());
        } catch (Exception e) {
            printError("Kesalahan tidak terduga: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Buku> getAll() {
        this.readAll();
        return this.books;
    }

    @Override
    public ArrayList<Buku> searchBooks(String keyword) {
        ArrayList<Buku> bukuFound = new ArrayList<>();
        for (var book : this.books){
            if(containKeyword(book, keyword)) bukuFound.add(book);
        }
        return bukuFound;
    }

    @Override
    public boolean isBukuFound(String keyword){
        boolean isFound = false;
        for (var book : this.books){
            if(containKeyword(book, keyword)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    @Override
    public ArrayList<Integer> indexSearchBuku(String keyword){
        int index = 1;
        ArrayList<Integer> indexBuku = new ArrayList<>();
        for (var book : this.books){
            if(containKeyword(book, keyword)) indexBuku.add(index);
            index++;
        }
        return indexBuku;
    }

    @Override
    public void add(Buku buku) throws IOException {
        FileWriter file = new FileWriter("db.txt", true);
        BufferedWriter writer = new BufferedWriter(file);

        String dataBuku =   buku.getId() + "#"
                            + buku.getJudul() + "#"
                            + buku.getPenulis() + "#"
                            + buku.getPenerbit() + "#"
                            + buku.getTahunTerbit() + "\n";
        writer.write(dataBuku);

        writer.flush();
        file.close();
        writer.close();

        this.readAll();
    }

    @Override
    public void update(int nomorBuku, Buku buku) throws IOException {
        // Ambil database awal
        File db = new File("db.txt");

        // siapkan database sementara
        File temp = new File("temp_db.txt");
        FileWriter fileWriter = new FileWriter("db.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);

        int urutanBuku = 0;
        while(urutanBuku < this.books.size()){
            if(urutanBuku == nomorBuku - 1){
                String dataBuku =   buku.getId() + "#"
                                    + buku.getJudul() + "#"
                                    + buku.getPenulis() + "#"
                                    + buku.getPenerbit() + "#"
                                    + buku.getTahunTerbit() + "\n";
                writer.write(dataBuku);
            } else {
                String dataBuku =   this.books.get(urutanBuku).getId() + "#"
                                    + this.books.get(urutanBuku).getJudul() + "#"
                                    + this.books.get(urutanBuku).getPenulis() + "#"
                                    + this.books.get(urutanBuku).getPenerbit() + "#"
                                    + this.books.get(urutanBuku).getTahunTerbit() + "\n";
                writer.write(dataBuku);
            }
            urutanBuku++;
        }

        // Tulis data ke temp_db, hapus database lama, rename temp_db ke db, tutup fileWriter
        writer.flush();
        db.delete();
        temp.renameTo(db);
        fileWriter.close();

        this.readAll();
    }

    @Override
    public void remove(int nomorBuku) throws IOException {
        // Ambil database awal
        File db = new File("db.txt");

        // siapkan database sementara
        File temp = new File("temp_db.txt");
        FileWriter fileWriter = new FileWriter("db.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);

        int urutanBuku = 0;
        while(urutanBuku < this.books.size()){
            if(urutanBuku != nomorBuku - 1){
                String dataBuku =   this.books.get(urutanBuku).getId() + "#"
                                    + this.books.get(urutanBuku).getJudul() + "#"
                                    + this.books.get(urutanBuku).getPenulis() + "#"
                                    + this.books.get(urutanBuku).getPenerbit() + "#"
                                    + this.books.get(urutanBuku).getTahunTerbit() + "\n";
                writer.write(dataBuku);
            }
            urutanBuku++;
        }

        // Tulis data ke temp_db, hapus database lama, rename temp_db ke db, tutup fileWriter
        writer.flush();
        db.delete();
        temp.renameTo(db);
        fileWriter.close();
    }
}
