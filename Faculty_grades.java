import java.io.BufferedReader;
import java.io.*;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Faculty_grades {
    public static void View_grades(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            // System.out.println("Grades for your courses by students -> ");
            // System.out.println("Entry no" + "\t\t\t" + "Course_id" + "\t\t\t" + "Grade");

            // String query1 = "select * from courses_enrolled_by_student course_id=?";

            // PreparedStatement pstmt1 = c.prepareStatement(query1);

            // pstmt1.setString(1, courseid);
            // // pstmt1.setString(2, "G");
            // Resutset set = pstmt1.executeQuery();
            // System.out.println("\t\t\tCourse: " + courseid + "\tGrades studentid vs
            // grade");
            // while (set.next()) {

            // String entrynum = set.getString(1);
            // String grade = set.getString(7);
            // System.out.println("\t\t\t\t" + entrynum + " " + grade);
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Update_grades(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        // String filepath = "Z:/SE_Mini_project/sample.csv";
        File csvfile = new File("Z:/SE_Mini_project/sample.csv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvfile));

            // File csvgrade = new File(filepath);
            String LineText;

            int count = 0;
            int flag = 0;
            String query = "Update courses_enrolled_by_student set grading_status=?,grade=? where entry_num=? and course_id=?";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet set;
            while ((LineText = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                String[] data = LineText.split(",");

                String entryno = data[0];
                String courseid = data[1];
                String grade = data[2];
                String status = "G";
                // System.out.println("good" + " " + entryno + " " + courseid + " " + grade + "
                // " + status);

                statement.setString(3, entryno);
                statement.setString(4, courseid);
                statement.setString(2, grade);
                statement.setString(1, status);

                String query1 = "Select * from course_offerings where instructor_id=? and course_id=?";
                PreparedStatement pstmt = c.prepareStatement(query1);
                pstmt.setString(1, id);
                pstmt.setString(2, courseid);
                set = pstmt.executeQuery();

                Integer flag1 = 0, flag2 = 0;

                while (set.next()) {
                    flag1++;
                }
                String query2 = "Select * from courses_enrolled_by_student where entry_num=? and course_id=?";
                PreparedStatement pstmt1 = c.prepareStatement(query2);
                pstmt1.setString(1, entryno);
                pstmt1.setString(2, courseid);
                set = pstmt.executeQuery();

                // set.close();

                while (set.next()) {
                    flag2++;
                }
                // set.close();

                if (flag1 == 1) {
                    if (flag2 == 1)
                        statement.executeUpdate();
                    else {
                        flag = 1;
                        System.out.println(
                                "Student with id " + entryno + " had not registered for " + courseid + " course");
                    }
                } else {
                    flag = 1;
                    System.out.println(courseid + " course was not your registered...");

                }

            }
            if (flag == 0)
                System.out.println("Grades has been added sucessfully");
            else {
                System.out.println("Grades updated with some wrong entries in csv file which were ignored..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
