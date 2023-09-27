import java.util.Scanner;

public class SingletonScanner {
    private static Scanner scanner;

    private SingletonScanner() {
    }

    public static synchronized Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
