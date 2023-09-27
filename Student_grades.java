import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

public class Student_grades {
    // all grades
    public static void View_grades(String entryno, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {
            String query = "select * from courses_enrolled_by_student where entry_num=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);
            ResultSet set = pstmt.executeQuery();
            String course = "";
            String grade = "";
            // System.out.println("\t\t\t\t\t\t\t\t\t\t\tSession - " + year1 + "-" + year2 +
            // ": " + parity);
            System.out.println("\t\t\tYour grades as below( Note :if grade is U means not yet graded)");
            System.out.println("\t\t\t\tCourse" + "\t" + "Grade");
            while (set.next()) {
                course = set.getString(2);
                grade = set.getString(7);
                System.out.println("\t\t\t\t" + course + "\t " + grade);
            }

            query = "select * from courses_completed_by_student where entry_num=?";
            pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);
            set = pstmt.executeQuery();
            while (set.next()) {
                course = set.getString(2);
                grade = set.getString(5);
                System.out.println("\t\t\t\t" + course + "\t " + grade);
            }

            // System.out.print("\t\t\tEnter choice->");
            // Scanner scanner = new Scanner(System.in);
            // Integer choice;
            // choice = scanner.nextInt();
            // System.out.println("1. To see previous semester grades");
            // System.out.println("2. Back");

            // if(choice==1)
            // {
            // System.out.println("Choose any of sessions");

            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
