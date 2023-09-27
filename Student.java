import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

class Student {
    public static void menu(String entry_no, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println(
                        "__________________________________________________________________________________________________________________");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tSession - " + year1 + "-" + year2 + ": " + parity);
                System.out.println("\n\t\t\t\tStudent has below choices to-");
                System.out.println("\t\t\t\t\t1. Course offered for Enrollment");
                System.out.println("\t\t\t\t\t2. Course Enrolled");
                System.out.println("\t\t\t\t\t3. Courses completed");
                // System.out.println("\t\t\t\t\t4. Drop a course");
                System.out.println("\t\t\t\t\t4. View grades");
                System.out.println("\t\t\t\t\t5. View current cgpa");
                System.out.println("\t\t\t\t\t6. Update Phoneno");
                System.out.println("\t\t\t\t\t7. Check graduating condition");
                System.out.println("\t\t\t\t\t8. Back");

                System.out.print("Enter choice -> ");
                choice = scanner.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1) {
                    Student_course.Courses_offered_for_enrollment(entry_no, c, year1, year2, parity);
                } else if (choice == 2) {
                    Student_course.Courses_enrolled(entry_no, c, year1, year2, parity);
                } else if (choice == 3) {
                    Student_course.Courses_completed(entry_no, c, year1, year2, parity);
                } else if (choice == 4) {
                    Student_grades.View_grades(entry_no, c, year1, year2, parity);
                } else if (choice == 5) {
                    Double cgpa = Student_course.calculate_cgpa(entry_no, c, year1, year2, parity);
                    System.out.println("----------------Your CGPA right now till previous sem is ** " + cgpa
                            + " ** -------------");
                } else if (choice == 6) {

                    String q = "select * from student_info where entry_num=?";

                    PreparedStatement pstmt = c.prepareStatement(q);

                    pstmt.setString(1, entry_no);

                    ResultSet set = pstmt.executeQuery();

                    while (set.next()) {
                        System.out.println("\t\t\tYour current phone no -> " + set.getString(7));
                    }

                    q = "update student_info set contactno=? where entry_num=?";
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    Boolean flag = false;
                    String newphone;

                    System.out.println("Press 1 to change phone no");
                    System.out.println("Else Press 2");
                    System.out.print("Enter choice -> ");
                    choice = scanner.nextInt();
                    System.out.println(
                            "__________________________________________________________________________________________________________________");
                    if (choice == 1) {
                        do {
                            System.out.print("Enter new phone no-> ");
                            newphone = br.readLine();

                            if (newphone.length() != 10) {
                                System.out.println("Invalid phone no");
                                flag = true;
                            } else {
                                flag = false;
                            }
                        } while (flag);

                        pstmt = c.prepareStatement(q);

                        pstmt.setString(1, newphone);
                        pstmt.setString(2, entry_no);

                        pstmt.executeUpdate();
                    }
                } else if (choice == 7) {
                    Student_course.check_graduation(entry_no, c, year1, year2, parity);
                }

            } while (choice != 8);
            // scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
