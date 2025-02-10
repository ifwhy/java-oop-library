package controller;

import entity.Buku;
import service.BukuService;
import service.BukuServiceImpl;
import view.BukuView;
import static utils.ClearConsole.*;
import static utils.ConsoleColors.*;
import static utils.UserInput.*;
import static validation.BukuValidation.isNomorBukuValid;

public class BukuController extends BukuView {
    private BukuService bukuService = new BukuServiceImpl();
    private int userOption;

    public BukuController(int userOption) {
        this.userOption = userOption;
    }

    public void menuController() {
        switch (this.userOption) {
            // Menampilkan data buku
            case 1: {
                clearConsole();
                super.showBuku();
                this.bukuService.showBuku();
                super.continueToMainMenu();
                break;
            }
            // Mencari data buku
            case 2: {
                clearConsole();
                String keyword = super.searchBuku();
                this.bukuService.searchBooks(keyword);
                super.continueToMainMenu();
                break;
            }
            // Menambahkan data buku
            case 3: {
                clearConsole();
                super.addBuku();
                Buku newBook = super.inputDataBuku();
                this.bukuService.addBuku(newBook);
                super.continueToMainMenu();
                break;
            }
            // Memperbarui Data Buku
            case 4: {
                clearConsole();
                super.updateBuku();
                this.bukuService.showBuku();
                int nomorBukuUpdate = inputNomorBuku("Masukkan Nomor Buku yang Akan Diperbarui   : ");
                if(!isNomorBukuValid(nomorBukuUpdate)){
                    printError("Nomor Buku Tidak Valid");
                    super.continueToMainMenu();
                    break;
                }
                Buku bukuUpdate = super.inputDataBuku();
                this.bukuService.updateDuku(nomorBukuUpdate, bukuUpdate);
                super.continueToMainMenu();
                break;
            }
            // Menghapus Data Buku
            case 5: {
                clearConsole();
                super.removeBuku();
                this.bukuService.showBuku();
                int nomorBukuDelete = inputNomorBuku("Masukkan Nomor Buku yang Akan Dihapus   : ");;
                this.bukuService.removeBuku(nomorBukuDelete);
                super.continueToMainMenu();
                break;
            }
            // Keluar Menu
            case 6: {
                printSuccess("Program Berhasil Diakhiri");
                super.isContinue = false;
                System.exit(0);
                break;
            }
            default: {
                printWarning("Pilihan Menu Tidak Diketahui");
                printWarning("Program Diakhiri");
                super.isContinue = false;
                System.exit(0);
            }

        }
    }

}
