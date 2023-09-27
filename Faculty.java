import java.util.*;
import java.sql.*;
import java.io.*;

public class Faculty {

    public static void menu(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            Scanner scanner1 = new Scanner(System.in);
            int choice;

            do {
                System.out.println(
                        "__________________________________________________________________________________________________________________");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tSession - " + year1 + "-" + year2 + ": " + parity);
                System.out.println("\n\t\t\t\tFaculty has below choices to-");
                System.out.println("\t\t\t\t\t1. Courses added by you");
                System.out.println("\t\t\t\t\t2. Add Course");
                System.out.println("\t\t\t\t\t3. Remove Course");
                // System.out.println("\t\t\t\t\t5. View grades of students");
                System.out.println("\t\t\t\t\t4. Update Grades");
                System.out.println("\t\t\t\t\t5. Show course catalog");
                System.out.println("\t\t\t\t\t6. Logout");

                System.out.print("Enter choice -> ");
                choice = scanner1.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1) {
                    Faculty_course.courses_added_you(id, c, year1, year2, parity);
                } else if (choice == 2) {
                    Faculty_course.add_a_course(id, c, year1, year2, parity);
                } else if (choice == 3) {
                    // remove course such that before any student has registered if removed after
                    // som student has registered then it will need to remove those from
                    // courses_enrolled_by_student table also
                    Faculty_course.remove_a_course(id, c, year1, year2, parity);
                } else if (choice == 4) {
                    Faculty_grades.Update_grades(id, c, year1, year2, parity);
                } else if (choice == 5) {
                    Faculty_course.course_catalog(id, c, year1, year2, parity);
                } else if (choice == 6) {
                    System.out.println("Logged-out");
                    break;
                } else {
                    System.out.println("Wrong choice");
                }
            } while (choice != 7);

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
