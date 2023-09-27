
import java.util.*;
import java.sql.*;
import java.io.*;

public class Faculty_course {

    public static void course_catalog(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            System.out.println("Course catalog as below-> ");
            String query = "select * from course_catalog";
            PreparedStatement pstmt = c.prepareStatement(query);

            ResultSet set = pstmt.executeQuery();
            String title = "";
            String course = "";
            String dept_name = "";
            String LTPSC = "";
            Double credit = 0.0;
            System.out.println("\tCourse_id\t" + "Department" + "\t LTPSC \t\t" + "Credit");
            while (set.next()) {
                title = set.getString(2);
                course = set.getString(1);
                dept_name = set.getString(3);
                LTPSC = set.getString(4);
                credit = set.getDouble(5);

                System.out
                        .println("\t" + course + "\t\t" + dept_name + "   \t    " + LTPSC + "\t\t" + credit);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is_anyone_registered_and_graded(String courseid, Connection c) {
        try {
            String query1 = "select * from courses_enrolled_by_student where course_id=? and grading_status=?";

            PreparedStatement pstmt1 = c.prepareStatement(query1);

            pstmt1.setString(1, courseid);
            pstmt1.setString(2, "G");

            ResultSet set = pstmt1.executeQuery();
            int flag = 0;
            while (set.next()) {
                flag++;
            }
            if (flag == 0)
                return false;
            else
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void add_a_course(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            Scanner scanner = new Scanner(System.in);
            // BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\tEnter course id with correct letter case -> ");
            String courseid = scanner.next();

            // String courseid = temp;
            System.out.println(courseid);

            boolean checkdept = checkincourseofferings.your_dept_course(courseid, id, c);
            System.out.println(checkdept);
            if (checkdept) {
                if ((checkincourseofferings.not_already_registered(courseid, c))) {

                    // System.out.print("Enter semester -> ");
                    // int sem = scanner.nextInt();

                    System.out.print("Enter cg criteria minimum -> ");
                    Double cgpa = scanner.nextDouble();

                    System.out.print("Enter no of batches you want to allow -> ");
                    int number = scanner.nextInt();

                    for (int i = 0; i < number; i++) {
                        System.out.print("Enter batch to be allowed :");
                        int batch = scanner.nextInt();

                        String query1 = "insert into batch_allowed(Course_id,batch) values (?,?)";

                        PreparedStatement pstmt1 = c.prepareStatement(query1);

                        pstmt1.setString(1, courseid);
                        pstmt1.setInt(2, batch);

                        pstmt1.executeUpdate();
                    }

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

                    String query1 = "insert into course_offerings(Course_id,dept_name,credit,Instructor_id,LTPSC,cgConstraint) values (?,?,?,?,?,?)";

                    PreparedStatement pstmt1 = c.prepareStatement(query1);

                    pstmt1.setString(1, courseid);
                    pstmt1.setString(2, dept_name);
                    // pstmt1.setInt(3, sem);
                    pstmt1.setDouble(3, credit);
                    pstmt1.setString(4, id);
                    pstmt1.setString(5, LTPSC);
                    pstmt1.setDouble(6, cgpa);

                    pstmt1.executeUpdate();
                } else {
                    System.out.println("\t\t\tCourse " + courseid + " has already been added for offering...");
                }
            } else {
                System.out.println("\t\t\tThis course donot belong to your department");
            }
            // scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void remove_a_course(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\tEnter course id with correct letter case ->");
            String courseid = br.readLine();

            if (checkincourseofferings.not_already_registered_by_you(courseid, id, c) == false) {

                // if()
                if (is_anyone_registered_and_graded(courseid, c) == false) {
                    // course to be removed
                    String query1 = "delete from course_offerings where course_id=?";

                    PreparedStatement pstmt1 = c.prepareStatement(query1);

                    pstmt1.setString(1, courseid);
                    pstmt1.executeUpdate();

                    // deleting the students enrolled if they are ungraded and course to be
                    // cancelled
                    query1 = "delete from courses_enrolled_by_student where course_id=?";

                    pstmt1 = c.prepareStatement(query1);

                    pstmt1.setString(1, courseid);
                    pstmt1.executeUpdate();
                } else {
                    System.out.println("As you have graded, so you cant deregister this course!!");
                }

            } else {
                System.out.println("This course was not registered by you!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void courses_added_you(String id, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            System.out.println("\t\t\t\t Courses added by you - ");
            String query = "select * from course_offerings where instructor_id=?";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet set = pstmt.executeQuery();
            Integer Serialno = 1;
            HashMap<Integer, String> course_reg = new HashMap<>();
            System.out.println("\t\t\t\tSerial no" + "\t\t\t" + "Courseid");

            // if (!set.next()) {
            // System.out.println("No courses registered by you this sem");
            // return;
            // }
            while (set.next()) {
                String course_id = set.getString(1);
                course_reg.put(Serialno, course_id);
                System.out.println("\t\t\t\t" + Serialno + " \t\t\t" + course_id);
                Serialno++;
            }

            if (Serialno == 1) {
                System.out.println("\t\t\tNo courses for students, you registered this semester");
            } else {
                System.out.println("\t\t\tEnter below choices");
                Scanner scanner = new Scanner(System.in);
                System.out.println("\t\t\t1. View grades of course");
                System.out.println("\t\t\t2. Back");

                System.out.print("Enter choice -> ");
                Integer choice;
                choice = scanner.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1) {
                    System.out.print("Enter serial no of the course shown above to view grade -> ");
                    Integer serialnum = scanner.nextInt();
                    System.out.println(
                            "__________________________________________________________________________________________________________________");

                    String courseid = course_reg.get(serialnum);
                    String query1 = "select * from courses_enrolled_by_student where course_id=? ";

                    PreparedStatement pstmt1 = c.prepareStatement(query1);

                    pstmt1.setString(1, courseid);
                    // pstmt1.setString(2, "G");
                    set = pstmt1.executeQuery();
                    int flag = 0;
                    while (set.next()) {
                        if (flag == 0)
                            System.out.println("\t\t\tCourse: " + courseid + "\tGrades studentid vs grade");
                        String entrynum = set.getString(1);
                        String grade = set.getString(7);
                        System.out.println("\t\t\t\t\t\t" + entrynum + " " + grade);
                        flag = 1;
                    }

                    if (flag == 0) {
                        System.out.println("\t\t\tNo students registered for course");
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
