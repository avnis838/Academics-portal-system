import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

class Academic {

    public static void menu(String email) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println("\n\t\t\t\tAcademics has below choices to-");
                System.out.println("\t\t\t\t\t1. Edit course catalog");
                System.out.println("\t\t\t\t\t2. View grade of all students");
                System.out.println("\t\t\t\t\t3. Generate transcripts for students");
                System.out.println("\t\t\t\t\t4. Back");

                System.out.print("Enter choice -> ");
                choice = scanner.nextInt();

                if (choice == 1) {

                } else if (choice == 2) {

                } else if (choice == 3) {

                }

            } while (choice != 4);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
