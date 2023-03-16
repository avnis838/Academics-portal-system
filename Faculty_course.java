
import java.util.*;
import java.sql.*;
import java.io.*;

public class Faculty_course {

    public static void course_catalog() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add_a_course(String id, Connection c) {
        Scanner scanner = new Scanner(System.in);
        try {
            // BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\tEnter course id with correct letter case -> ");
            String courseid = scanner.next();

            // String courseid = temp;
            System.out.println(courseid);

            boolean checkdept = checkincourseofferings.your_dept_course(courseid, id, c);
            System.out.println(checkdept);
            if (checkdept) {
                if ((checkincourseofferings.not_already_registered(courseid, c))) {

                    System.out.print("Enter semester -> ");
                    int sem = scanner.nextInt();

                    System.out.print("Enter cg criteria minimum -> ");
                    Double cgpa = scanner.nextDouble();

                    String query = "select * from course_catalog where course_id=?";
                    PreparedStatement pstmt = c.prepareStatement(query);
                    pstmt.setString(1, courseid);
                    ResultSet set = pstmt.executeQuery();

                    String dept_name = "";
                    String LTPSC = "";
                    Double credit = 0.0;
                    while (set.next()) {

                        dept_name = set.getString(3);
                        LTPSC = set.getString(4);
                        credit = set.getDouble(5);
                    }

                    // insert into course_offering

                    String query1 = "insert into course_offerings(Course_id,dept_name,semester,credit,Instructor_id,LTPSC,cgConstraint) values (?,?,?,?,?,?,?)";

                    PreparedStatement pstmt1 = c.prepareStatement(query1);

                    pstmt1.setString(1, courseid);
                    pstmt1.setString(2, dept_name);
                    pstmt1.setInt(3, sem);
                    pstmt1.setDouble(4, credit);
                    pstmt1.setString(5, id);
                    pstmt1.setString(6, LTPSC);
                    pstmt1.setDouble(7, cgpa);

                    pstmt1.executeUpdate();
                } else {
                    System.out.println("\t\t\tCourse " + courseid + " has already been added for offering...");
                }
            } else {
                System.out.println("\t\t\tThis course donot belong to your department");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();

    }

    public static void remove_a_course(String id, Connection c) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\tEnter course id with correct letter case ->");
            String courseid = br.readLine();

            if (checkincourseofferings.not_already_registered_by_you(courseid, id, c) == false) {
                String query1 = "delete from course_offerings where course_id=?";

                PreparedStatement pstmt1 = c.prepareStatement(query1);

                pstmt1.setString(1, courseid);
                pstmt1.executeUpdate();
            } else {
                System.out.println("This course was not registered by you!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void courses_added_you(String id, Connection c) {
        try {
            System.out.println("\t\t\t\t Courses added by you - ");
            String query = "select * from course_offerings where instructor_id=?";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet set = pstmt.executeQuery();

            while (set.next()) {
                // course_id varchar(255),title varchar(255),dept_name varchar(255),LTPSC
                // varchar(255),credit dec(10,2),Primary Key (course_id),Foreign Key (dept_name)
                // references Department(dept_name)
                String course_id = set.getString(1);
                String title = set.getString(2);
                String dept_name = set.getString(3);
                String LTPSC = set.getString(4);
                String credit = set.getString(5);

                // System.out.println(email + " " + pass + " " + rol);
                // System.out.println(this.Email + " " + this.Password + " " + this.Role);
                System.out.println("\t\t\t\t" + course_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
