package test.service;

import entity.Buku;
import service.BukuServiceImpl;

import java.util.Scanner;
import java.util.UUID;

import static utils.ConsoleColors.printError;
import static utils.ConsoleColors.printSuccess;
import static utils.UserInput.*;

public class TestBukuService {
    public static void main(String[] args) {
//        testAddBuku();
//        testUpdateBuku(14, new Buku("Aku Menangis", "Wahyu Nugroho", "Intan Pariwara", 2024));
//        testDeleteBuku(14);
        testShowBuku();
    }

    private static void testUpdateBuku(int nomorBuku, Buku buku){
        BukuServiceImpl bukuService = new BukuServiceImpl();
        bukuService.updateDuku(nomorBuku, buku);
    }

    private static void testDeleteBuku(int nomorBuku){
        BukuServiceImpl bukuService = new BukuServiceImpl();
        bukuService.removeBuku(nomorBuku);
    }

    private static void testShowBuku(){
        (new BukuServiceImpl()).showBuku();
    }

    private static void testAddBuku(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("MENAMBAHKAN DATA BUKU");
        System.out.println("===================================================================================================================");
        System.out.print("Masukkan Judul Buku           : ");
        String judulBuku = userInput.nextLine();
        System.out.print("Masukkan Penulis Buku         : ");
        String penulisBuku = userInput.nextLine();
        System.out.print("Masukkan Penerbit Buku        : ");
        String penerbitBuku = userInput.nextLine();
        int tahunTerbitBuku = inputYear("Masukkan Tahun Terbit Buku    : ");

        BukuServiceImpl bukuService = new BukuServiceImpl();
        bukuService.addBuku(new Buku(judulBuku, penulisBuku, penerbitBuku, tahunTerbitBuku));
    }

    private static void continueToMainMenu() {
        try {
            boolean isContinueToMainMenu = getYesOrNo("Kembali ke Menu Utama? (y/n) : ");
            if (isContinueToMainMenu) {
                System.out.println("Kembali ke menu utama");
            } else {
                printSuccess("Program Diakhiri");
                System.exit(0);
            }
        } catch (Exception e) {
            printError("Terjadi Error : " + e.getMessage());
            System.exit(1);
        }
    }
}
