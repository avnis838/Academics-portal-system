import java.sql.*;
import java.io.*;
import java.sql.Connection;

class checkincourseofferings {
    public static boolean not_already_registered(String courseid, Connection c) {
        try {
            String query = "SELECT 1 FROM course_offerings WHERE course_id=?";

            PreparedStatement pstmt1 = c.prepareStatement(query);

            pstmt1.setString(1, courseid);

            ResultSet set = pstmt1.executeQuery();

            while (set.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean not_already_registered_by_you(String courseid, String id, Connection c) {
        try {
            String query = "SELECT 1 FROM course_offerings WHERE course_id=? AND Instructor_id=?";

            PreparedStatement pstmt1 = c.prepareStatement(query);

            pstmt1.setString(1, courseid);
            pstmt1.setString(2, id);

            ResultSet set = pstmt1.executeQuery();

            while (set.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean your_dept_course(String courseid, String insid, Connection c) {
        try {
            System.out.println("your try");
            String query2 = "select * from course_catalog where course_id=?";
            PreparedStatement pstmt2 = c.prepareStatement(query2);
            pstmt2.setString(1, courseid);
            ResultSet set2 = pstmt2.executeQuery();
            String course_dept = "";
            while (set2.next()) {
                course_dept = set2.getString(3);
            }
            String query1 = "select * from faculty where id=?";
            PreparedStatement pstmt1 = c.prepareStatement(query1);
            pstmt1.setString(1, insid);
            ResultSet set1 = pstmt1.executeQuery();
            String instructor_dept = "";
            System.out.println(courseid + "check");
            while (set1.next()) {
                instructor_dept = set1.getString(3);
            }

            System.out.println(course_dept + " ******* " + instructor_dept);
            if (course_dept.equals(instructor_dept))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
