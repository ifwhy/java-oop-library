package test.validation;

import entity.Buku;

import static validation.BukuValidation.*;

public class testValidation {
    public static void main(String[] args) {
        System.out.println(isTahunTerbitValid("abcd"));
    }

    private static boolean isBukuValid(Buku buku){
        return isDataBukuNotValid(buku);
    }
}
