package utils;

import java.util.Scanner;

import static utils.ConsoleColors.printError;
import static utils.ConsoleColors.printMedia;

public class UserInput {
    private static Scanner userInput = new Scanner(System.in);

    public static int userInput(String message){
        printMedia(message);

        int userOption;
        try {
            userOption = userInput.nextInt();
            return userOption;
        } catch (Exception e){
            printError("Terjadi Error : " + e.getMessage());
            return -1;
        }
    }

    public static int inputYear(String message){
        printMedia(message);
        int userOption;
        String inputUser;
        inputUser = userInput.next();
        try {
            userOption = Integer.parseInt(inputUser);
        } catch (Exception e){
            userOption = -1;
        }
        return userOption;
    }

    public static int inputNomorBuku(String message){
        return inputYear(message);
    }

    public static boolean getYesOrNo(String message) throws IllegalArgumentException {
        printMedia(message);
        String inputUser = userInput.next().trim();

        // Validasi panjang input
        if (inputUser.isEmpty()) {
            throw new IllegalArgumentException("Input tidak boleh kosong.");
        }

        // Ambil karakter pertama dan validasi
        char userOption = Character.toLowerCase(inputUser.charAt(0));

        if (userOption == 'y') {
            return true;
        } else if (userOption == 'n') {
            return false;
        } else {
            throw new IllegalArgumentException("Input tidak valid. Masukkan 'y' atau 'n'.");
        }
    }

}
