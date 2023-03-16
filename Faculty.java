import java.util.*;
import java.sql.*;
import java.io.*;

public class Faculty {

    public static void menu(String id, Connection c) {
        try {
            Scanner scanner1 = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n\t\t\t\tFaculty has below choices to-");
                System.out.println("\t\t\t\t\t1. Courses added by you");
                System.out.println("\t\t\t\t\t2. Add Course");
                System.out.println("\t\t\t\t\t3. Remove Course");
                System.out.println("\t\t\t\t\t5. View grades of students");
                System.out.println("\t\t\t\t\t5. Update Grades");
                System.out.println("\t\t\t\t\t6. Show course catalog");
                System.out.println("\t\t\t\t\t7. Logout");

                System.out.print("Enter choice -> ");
                choice = scanner1.nextInt();

                if (choice == 1) {
                    Faculty_course.courses_added_you(id, c);
                } else if (choice == 2) {
                    Faculty_course.add_a_course(id, c);
                } else if (choice == 3) {
                    Faculty_course.remove_a_course(id, c);
                } else if (choice == 4) {

                } else if (choice == 5) {

                } else if (choice == 6) {

                } else if (choice == 7) {
                    System.out.println("Logged-out");
                    break;
                } else {
                    System.out.println("Wrong choice");
                }
            } while (choice != 7);
            scanner1.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
