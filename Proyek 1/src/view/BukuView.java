package view;

import controller.BukuController;
import entity.Buku;

import java.util.Scanner;
import java.util.UUID;

import static utils.ClearConsole.*;
import static utils.ConsoleColors.*;
import static utils.UserInput.*;

public class BukuView {
    protected boolean isContinue = true;
    private final Scanner userInput = new Scanner(System.in);

    public void mainMenu(){
        clearConsole();
        BukuController controller;
        do {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                CRUD MANAJEMEN BUKU PERPUSTAKAAN");
            System.out.println("------------------------------------------------------------------");
            System.out.println("      1. Menampilkan Daftar Buku    4. Memperbarui Data Buku");
            System.out.println("      2. Mencari Data Buku          5. Menghapus Data Buku");
            System.out.println("      3. Menambahkan Buku Buku      6. Keluar");
            System.out.println("------------------------------------------------------------------");
            int userOption = userInput("Pilihan Anda : ");
            controller = new BukuController(userOption);
            this.isContinue = userOption > 0 && userOption <= 6;
            controller.menuController();

        } while (this.isContinue);
    }

    public void showBuku(){
        System.out.println("\t\t\t\t\t    MENAMPILKAN BUKU PERPUSTAKAAN");
    }

    public void addBuku(){
        System.out.println("\t\t\t\t\t    MENAMBAHKAN DATA BUKU");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    public String searchBuku(){
        System.out.println("\t\t\t\t\t    MENCARI BUKU PERPUSTAKAAN");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.print("Masukkan Keyword Pencarian : ");
        return userInput.nextLine();
    }

    public void updateBuku(){
        System.out.println("\t\t\t\t\t    MEMPERBARUI BUKU PERPUSTAKAAN");
    }

    public void removeBuku(){
        System.out.println("\t\t\t\t\t    MENGHAPUS BUKU PERPUSTAKAAN");
    }

    public Buku inputDataBuku(){
        printMedia("Masukkan Judul Buku           \t\t   : ");
        String judulBuku = userInput.nextLine();
        printMedia("Masukkan Penulis Buku         \t\t   : ");
        String penulisBuku = userInput.nextLine();
        printMedia("Masukkan Penerbit Buku        \t\t   : ");
        String penerbitBuku = userInput.nextLine();
        int tahunTerbitBuku = inputYear("Masukkan Tahun Terbit Buku    \t\t   : ");

        return new Buku(String.valueOf(UUID.randomUUID()), judulBuku.trim(), penulisBuku.trim(), penerbitBuku.trim(), tahunTerbitBuku);
    }

    protected void continueToMainMenu(){
        try {
            if(getYesOrNo("Kembali ke Menu Utama? (y/n) : ")) {
                this.mainMenu();
            } else {
                this.isContinue = false;
                printSuccess("Program Diakhiri");
                System.exit(0);
            }
        } catch (Exception e){
            printError("\nTerjadi Error : " + e.getMessage());
            System.exit(1);
        }
    }
}
