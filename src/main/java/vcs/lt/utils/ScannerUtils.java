package vcs.lt.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
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

    public static boolean checkValidDate(String enteredDate) {
        DateTimeFormatter[] formatters = {
                new DateTimeFormatterBuilder().appendPattern("yyyy")
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .toFormatter(),
                new DateTimeFormatterBuilder().appendPattern("yyyy-MM")
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .toFormatter(),
                new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                        .parseStrict().toFormatter()};
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate.parse(enteredDate, formatter);
                return true;
            } catch (DateTimeParseException e) {
            }
        }
        return false;
    }
}
