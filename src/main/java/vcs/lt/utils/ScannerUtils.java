package vcs.lt.utils;

import java.util.Scanner;

public class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String scanString() {
        return scanner.next();
    }
}
