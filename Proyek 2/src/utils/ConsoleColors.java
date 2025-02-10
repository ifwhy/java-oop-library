package utils;

public class ConsoleColors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    public static void printError(String errorMessage){
        System.out.println(RED + errorMessage + RESET);
    }

    public static void printSuccess(String successMessage){
        System.out.println(GREEN  + successMessage + RESET);
    }

    public static void printWarning(String warningMessage){
        System.out.println(YELLOW + warningMessage + RESET);
    }

    public static void printMedia(String mediaMessage){
        System.out.print(CYAN + mediaMessage + RESET);
    }
}
