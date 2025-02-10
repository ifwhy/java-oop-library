package utils;

import entity.Buku;

import static utils.ConsoleColors.printMedia;
import static utils.BookPropertiesSubString.subString;

public class BookPrinter {

    public static void printOneBook(Buku buku){
        String[] bookProperties = subString(buku);

        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        printMedia("\tJudul \t\t\t\t    Penulis \t\t          Penerbit \t\t      Tahun Terbit\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\t%-36s", bookProperties[0]);
        System.out.printf("%-30s", bookProperties[1]);
        System.out.printf("%-32s", bookProperties[2]);
        System.out.printf("%d\n\n", buku.getTahunTerbit());
    }
}
