package vcs.lt.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String scanString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public static int scanInt(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public static int checkValidScan(int min, int max) {
        int enteredNumber;
        while (true) {
            try {
                enteredNumber = scanner.nextInt();
                if (min <= enteredNumber && enteredNumber <= max) {
                    return enteredNumber;
                } else {
                    System.out.println("Entered number should be between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter number");
                scanner.nextLine();
            }
        }
    }

}
