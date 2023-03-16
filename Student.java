import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

class Student {
    public static void menu(String entry_no) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println("\n\t\t\t\tStudent has below choices to-");
                System.out.println("\t\t\t\t\t1. Course offered for Enrollment");
                System.out.println("\t\t\t\t\t2. Course Enrolled");
                System.out.println("\t\t\t\t\t3. Enroll a course");
                System.out.println("\t\t\t\t\t4. Drop a course");
                System.out.println("\t\t\t\t\t5. View grades");
                System.out.println("\t\t\t\t\t6. View current cgpa");
                System.out.println("\t\t\t\t\t7. Back");

                System.out.print("Enter choice -> ");
                choice = scanner.nextInt();

                if (choice == 1) {
                    Student_course.Courses_offered_for_enrollment();
                } else if (choice == 2) {
                    Student_course.Courses_enrolled();
                } else if (choice == 3) {
                    Student_course.Enroll_a_course();
                } else if (choice == 4) {
                    Student_course.Drop_a_course();
                } else if (choice == 5) {
                    Student_grades.View_grades();
                } else if (choice == 6) {
                    Student_grades.View_current_cgpa();
                }

            } while (choice != 7);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
