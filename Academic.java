import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

class Academic {

    public static void menu(String email, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println(
                        "__________________________________________________________________________________________________________________");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tSession - " + year1 + "-" + year2 + ": " + parity);
                System.out.println("\n\t\t\t\tAcademics has below choices to-");
                System.out.println("\t\t\t\t\t1. Edit course catalog");
                System.out.println("\t\t\t\t\t2. View grade of all students");
                System.out.println("\t\t\t\t\t3. Generate transcripts for students");

                System.out.println("\t\t\t\t\t4. Change session");
                System.out.println("\t\t\t\t\t5. Check a student gradutate condition");

                System.out.println("\t\t\t\t\t6. Log-out");

                System.out.print("Enter choice -> ");
                choice = scanner.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1) {
                    Academic_grades.edit_course_catalog(email, c, year1, year2, parity);
                } else if (choice == 2) {
                    Academic_grades.View_grades(email, c, year1, year2, parity);
                } else if (choice == 3) {
                    Academic_grades.Generate_transcript(email, c, year1, year1, parity);
                } else if (choice == 4) {
                    Academic_grades.change_session(email, c, year1, year2, parity);
                } else if (choice == 5) {
                    Academic_grades.check_graduate(email, c, year1, year2, parity);
                }

            } while (choice != 6);
            // scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
