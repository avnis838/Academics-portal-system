import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

class elements {
    String course;
    Double credit;
    String grade;

    public elements(String name, Double cr, String gr) {
        this.course = name;
        this.credit = cr;
        this.grade = gr;
    }
}

public class Academic_grades {

    public static void edit_course_catalog(String email, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\t1. Add course");
            System.out.println("\t\t\t2. Remove course");
            // System.out.println("\t\t\t3. Edit a course");
            System.out.print("\t\t\tChoose option ->");

            Integer choice = scanner.nextInt();
            System.out.println(
                    "__________________________________________________________________________________________________________________");

            if (choice == 1) {

                System.out.println("Enter a course id :");
                String courseid = scanner.next();

                System.out.println("Enter course title :");
                String title = scanner.next();

                System.out.println("Enter department offering :");
                String department = scanner.next();

                System.out.println("Enter LTPSC in format 'L-T-P-S-C' :");
                String ltpsc = scanner.next();

                System.out.println("Enter course credit");
                Double credit = scanner.nextDouble();

                String query3 = "insert into course_catalog(course_id,title,dept_name,ltpsc,credit) values(?,?,?,?,?)";

                PreparedStatement pstmt3 = c.prepareStatement(query3);

                pstmt3.setString(1, courseid);
                pstmt3.setString(2, title);
                // pstmt1.setInt(3, sem);
                pstmt3.setString(3, department);
                pstmt3.setString(4, ltpsc);
                pstmt3.setDouble(5, credit);
                pstmt3.executeUpdate();

                System.out.println("Course added in course catalog");
                System.out.println("Is it a Programming core or Program elective or btp type Y for yes or N for No?");
                String op = scanner.next();

                if (op.equals("Y")) {
                    System.out.print("Enter 1 for Program core, 2 for program elective or 3 for btp");
                    Integer choose = scanner.nextInt();

                    if (choose == 1) {
                        query3 = "insert into core_courses(course_id) values(?)";

                        pstmt3 = c.prepareStatement(query3);

                        pstmt3.setString(1, courseid);

                        pstmt3.executeUpdate();
                    } else if (choose == 2) {
                        query3 = "insert into core_electives(course_id) values(?)";

                        pstmt3 = c.prepareStatement(query3);

                        pstmt3.setString(1, courseid);

                        pstmt3.executeUpdate();
                    } else if (choose == 3) {
                        query3 = "insert into core_btp(course_id) values(?)";

                        pstmt3 = c.prepareStatement(query3);

                        pstmt3.setString(1, courseid);

                        pstmt3.executeUpdate();
                    }
                } else if (op.equals("N")) {
                    return;
                }

            } else if (choice == 2) {

                System.out.println("Enter a course id :");
                String courseid = scanner.next();

                String query3 = "delete from course_catalog where course_id=?";

                PreparedStatement pstmt3 = c.prepareStatement(query3);

                pstmt3.setString(1, courseid);

                pstmt3.executeUpdate();
                ////////////////////////
                query3 = "delete from core_courses where course_id=?";

                pstmt3 = c.prepareStatement(query3);

                pstmt3.setString(1, courseid);

                pstmt3.executeUpdate();
                //////////////////////////////////
                query3 = "delete from core_electives where course_id=?";

                pstmt3 = c.prepareStatement(query3);

                pstmt3.setString(1, courseid);

                pstmt3.executeUpdate();
                //////////////////////////////////////
                query3 = "delete from core_btp where course_id=?";

                pstmt3 = c.prepareStatement(query3);

                pstmt3.setString(1, courseid);

                pstmt3.executeUpdate();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void check_graduate(String email, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {
            System.out.println("Choose student whose graduate condition you want->");

            System.out.println("\t\t\t\t" + "Serial no" + "\t\t" + "Student id");
            String query = "select * from student_info";
            PreparedStatement pstmt = c.prepareStatement(query);
            // pstmt.setDouble(1, 145);
            ResultSet set = pstmt.executeQuery();
            Integer Serialno = 1;
            HashMap<Integer, String> course_reg = new HashMap<>();
            HashMap<Integer, Integer> yearoad = new HashMap<>();

            while (set.next()) {
                String st_id = set.getString(1);
                Integer yod = set.getInt(3);
                course_reg.put(Serialno, st_id);
                yearoad.put(Serialno, yod);
                System.out.println("\t\t\t\t" + Serialno + " \t\t\t" + st_id);
                Serialno++;
            }

            System.out.print("\t\t\tEnter student serial no-> ");

            Scanner scanner = new Scanner(System.in);

            // System.out.print("Enter choice -> ");
            Integer choice;
            choice = scanner.nextInt();

            String student = course_reg.get(choice);

            Student_course.check_graduation(student, c, year1, year2, parity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void change_session(String email, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {
            // change in student info with cgpa and total credits earned
            // input into completed sessions
            // Integer pyear1 = 0, pyear2 = 0, pparity = 0;
            String query2 = "SELECT * FROM completed_sessions ORDER BY id DESC LIMIT 1";
            PreparedStatement pstmt2 = c.prepareStatement(query2);

            ResultSet set2 = pstmt2.executeQuery();
            Integer serialno = 0;
            while (set2.next()) {
                serialno = set2.getInt(1);
            }
            serialno++;
            String query3 = "insert into completed_sessions(id,year1,year2,parity) values(?,?,?,?)";

            PreparedStatement pstmt3 = c.prepareStatement(query3);

            pstmt3.setInt(1, serialno);
            pstmt3.setInt(2, year1);
            // pstmt1.setInt(3, sem);
            pstmt3.setInt(3, year2);
            pstmt3.setInt(4, parity);
            pstmt3.executeUpdate();
            ///////////////////////////////////// input in completed sessions
            ///// done//////////////////////////////////////////////////////////////////

            // input all the course enrolled information into course completed_by_student
            // transfer(email, c, year1, year2, parity);
            String query5 = "SELECT * FROM courses_enrolled_by_student";
            PreparedStatement pstmt5 = c.prepareStatement(query5);

            ResultSet set5 = pstmt5.executeQuery();

            while (set5.next()) {
                if (set5.getString(7).equals("U") == true) {
                    System.out.println("Some courses has not been graded, notify respective faculties");
                    return;
                }
            }

            String query = "SELECT * FROM courses_enrolled_by_student";
            PreparedStatement pstmt = c.prepareStatement(query);

            ResultSet set = pstmt.executeQuery();

            String query1 = "insert into courses_completed_by_student(entry_num,course_id,dept_name,credit,grade,year1,year2,parity) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt1 = c.prepareStatement(query1);
            while (set.next()) {
                String entryno = set.getString(1);
                String courseid = set.getString(2);
                String dept = set.getString(3);
                Double credit = set.getDouble(4);
                String grade = set.getString(7);

                System.out.println(entryno);

                pstmt1.setString(1, entryno);
                pstmt1.setString(2, courseid);
                pstmt1.setString(3, dept);
                pstmt1.setDouble(4, credit);
                pstmt1.setString(5, grade);
                pstmt1.setInt(6, year1);
                pstmt1.setInt(7, year2);
                pstmt1.setInt(8, parity);

                if (grade.equals("U") == false)
                    pstmt1.executeUpdate();
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////
            Statement stmt = c.createStatement();
            String sql = "DELETE FROM " + "courses_enrolled_by_student"; // SQL DELETE

            int rowsDeleted = stmt.executeUpdate(sql);

            System.out.println("enrolled" + rowsDeleted);

            Statement stmt1 = c.createStatement();
            String sql1 = "DELETE FROM " + "course_offerings"; // SQL DELETE

            // from
            // the table

            int rowsDeleted1 = stmt1.executeUpdate(sql1);

            System.out.println("offerings" + rowsDeleted1);
            /////////////////////////////////////////////////////////////////////////////////////////////////
            // // Execute the DELETE statement to delete all rows from the table
            // String sql = "DELETE FROM courses_enrolled_by_student";

            // sql = "DELETE FROM course_offerings";
            // stmt.executeUpdate(sql);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void Generate_transcript(String email, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {
            FileWriter f = new FileWriter("Z:\\SE_Mini_project\\trancript.txt");
            try {
                System.out.println("Choose student whose transcript you want->");

                System.out.println("\t\t\t\t" + "Serial no" + "\t\t" + "Student id");
                String query = "select * from student_info";
                PreparedStatement pstmt = c.prepareStatement(query);
                // pstmt.setDouble(1, 145);
                ResultSet set = pstmt.executeQuery();
                Integer Serialno = 1;
                HashMap<Integer, String> course_reg = new HashMap<>();
                HashMap<Integer, Integer> yearoad = new HashMap<>();

                while (set.next()) {
                    String st_id = set.getString(1);
                    Integer yod = set.getInt(3);
                    course_reg.put(Serialno, st_id);
                    yearoad.put(Serialno, yod);
                    System.out.println("\t\t\t\t" + Serialno + " \t\t\t" + st_id);
                    Serialno++;
                }

                System.out.print("\t\t\tEnter student serial no-> ");

                Scanner scanner = new Scanner(System.in);

                // System.out.print("Enter choice -> ");
                Integer choice;
                choice = scanner.nextInt();

                String student = course_reg.get(choice);

                query = "select * from courses_completed_by_student where entry_num=?";
                pstmt = c.prepareStatement(query);
                pstmt.setString(1, student);
                set = pstmt.executeQuery();

                ArrayList<elements> sem1 = new ArrayList<elements>();
                ArrayList<elements> sem2 = new ArrayList<elements>();
                ArrayList<elements> sem3 = new ArrayList<elements>();
                ArrayList<elements> sem4 = new ArrayList<elements>();
                ArrayList<elements> sem5 = new ArrayList<elements>();
                ArrayList<elements> sem6 = new ArrayList<elements>();
                ArrayList<elements> sem7 = new ArrayList<elements>();
                ArrayList<elements> sem8 = new ArrayList<elements>();

                Integer yoad = yearoad.get(choice);

                System.out.println(yoad + " " + student);

                while (set.next()) {
                    String course = set.getString(2);
                    String grade = set.getString(5);
                    Double credit = set.getDouble(4);
                    Integer y1 = 0, y2 = 0, pa = 0;
                    y1 = set.getInt(6);
                    y2 = set.getInt(7);
                    pa = set.getInt(8);

                    Integer sem = 0;
                    if (pa == 2) {
                        sem = (y1 - yoad + 1) * 2;
                    } else {
                        sem = (y1 - yoad) * 2 + 1;
                    }

                    if (sem == 1) {
                        sem1.add(new elements(course, credit, grade));
                    } else if (sem == 2) {
                        sem2.add(new elements(course, credit, grade));
                    } else if (sem == 3) {
                        sem3.add(new elements(course, credit, grade));
                    } else if (sem == 4) {
                        sem4.add(new elements(course, credit, grade));
                    } else if (sem == 5) {
                        sem5.add(new elements(course, credit, grade));
                    } else if (sem == 6) {
                        sem6.add(new elements(course, credit, grade));
                    } else if (sem == 7) {
                        sem7.add(new elements(course, credit, grade));
                    } else if (sem == 8) {
                        sem8.add(new elements(course, credit, grade));
                    }
                }

                HashMap<String, Integer> grade_score = new HashMap<>();
                grade_score.put("A", 10);
                grade_score.put("A-", 9);
                grade_score.put("B", 8);
                grade_score.put("B-", 7);
                grade_score.put("C", 6);
                grade_score.put("C-", 5);
                grade_score.put("D", 4);
                grade_score.put("E", 3);
                grade_score.put("F", 0);

                f.write("Transcript of " + student + " - \n");
                f.write("---------------------------------------------------------------------\n");

                Double sgpa1 = 0.0, sgpa2 = 0.0, sgpa3 = 0.0, sgpa4 = 0.0, sgpa5 = 0.0, sgpa6 = 0.0, sgpa7 = 0.0,
                        sgpa8 = 0.0, cgpa = 0.0, credits = 0.0, scores = 0.0;
                if (sem1.isEmpty() == false) {
                    f.write("Semester I - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem1.size(); i++) {
                        f.write(sem1.get(i).course + "\t\t" + sem1.get(i).credit + "\t\t\t" + sem1.get(i).grade + "\n");
                        score = score + (sem1.get(i).credit) * grade_score.get(sem1.get(i).grade);
                        semcredit = semcredit + sem1.get(i).credit;
                        scores = scores + (sem1.get(i).credit) * grade_score.get(sem1.get(i).grade);
                        credits = credits + sem1.get(i).credit;
                    }
                    sgpa1 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester I SGPA - " + sgpa1 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem2.isEmpty() == false) {
                    f.write("Semester II - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem2.size(); i++) {
                        f.write(sem2.get(i).course + "\t\t" + sem2.get(i).credit + "\t\t\t" + sem2.get(i).grade + "\n");
                        score = score + (sem2.get(i).credit) * grade_score.get(sem2.get(i).grade);
                        semcredit = semcredit + sem2.get(i).credit;
                        scores = scores + (sem2.get(i).credit) * grade_score.get(sem2.get(i).grade);
                        credits = credits + sem2.get(i).credit;
                    }
                    sgpa2 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester II SGPA - " + sgpa2 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem3.isEmpty() == false) {
                    f.write("Semester III - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem3.size(); i++) {
                        f.write(sem3.get(i).course + "\t\t" + sem3.get(i).credit + "\t\t\t" + sem3.get(i).grade + "\n");
                        score = score + (sem3.get(i).credit) * grade_score.get(sem3.get(i).grade);
                        semcredit = semcredit + sem3.get(i).credit;
                        scores = scores + (sem3.get(i).credit) * grade_score.get(sem3.get(i).grade);
                        credits = credits + sem3.get(i).credit;
                    }
                    sgpa3 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester III SGPA - " + sgpa3 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem4.isEmpty() == false) {
                    f.write("Semester IV - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem4.size(); i++) {
                        f.write(sem4.get(i).course + "\t\t" + sem4.get(i).credit + "\t\t\t" + sem4.get(i).grade + "\n");
                        score = score + (sem4.get(i).credit) * grade_score.get(sem4.get(i).grade);
                        semcredit = semcredit + sem4.get(i).credit;
                        scores = scores + (sem4.get(i).credit) * grade_score.get(sem4.get(i).grade);
                        credits = credits + sem4.get(i).credit;
                    }
                    sgpa4 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester IV SGPA - " + sgpa4 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem5.isEmpty() == false) {
                    f.write("Semester V - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem5.size(); i++) {
                        f.write(sem5.get(i).course + "\t\t" + sem5.get(i).credit + "\t\t\t" + sem5.get(i).grade + "\n");
                        score = score + (sem5.get(i).credit) * grade_score.get(sem5.get(i).grade);
                        semcredit = semcredit + sem5.get(i).credit;
                        scores = scores + (sem5.get(i).credit) * grade_score.get(sem5.get(i).grade);
                        credits = credits + sem5.get(i).credit;
                    }
                    sgpa5 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester V SGPA - " + sgpa5 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem6.isEmpty() == false) {
                    f.write("Semester VI - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem6.size(); i++) {
                        f.write(sem6.get(i).course + "\t\t" + sem6.get(i).credit + "\t\t\t" + sem6.get(i).grade + "\n");
                        score = score + (sem6.get(i).credit) * grade_score.get(sem6.get(i).grade);
                        semcredit = semcredit + sem6.get(i).credit;
                        scores = scores + (sem6.get(i).credit) * grade_score.get(sem6.get(i).grade);
                        credits = credits + sem6.get(i).credit;
                    }
                    sgpa6 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester VI SGPA - " + sgpa6 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem7.isEmpty() == false) {
                    f.write("Semester VII - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem7.size(); i++) {
                        f.write(sem7.get(i).course + "\t\t" + sem7.get(i).credit + "\t\t\t" + sem7.get(i).grade + "\n");
                        score = score + (sem7.get(i).credit) * grade_score.get(sem7.get(i).grade);
                        semcredit = semcredit + sem7.get(i).credit;
                        scores = scores + (sem7.get(i).credit) * grade_score.get(sem7.get(i).grade);
                        credits = credits + sem7.get(i).credit;
                    }
                    sgpa7 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester VII SGPA - " + sgpa7 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }
                if (sem8.isEmpty() == false) {
                    f.write("Semester VIII - \n");
                    f.write("---------------------------------------------------------------------\n");
                    f.write("Course\t\tCredit\t\tGrade\n");
                    Double score = 0.0;
                    Double semcredit = 0.0;
                    for (int i = 0; i < sem8.size(); i++) {
                        f.write(sem8.get(i).course + "\t\t" + sem8.get(i).credit + "\t\t\t" + sem8.get(i).grade + "\n");
                        score = score + (sem8.get(i).credit) * grade_score.get(sem8.get(i).grade);
                        semcredit = semcredit + sem8.get(i).credit;
                        scores = scores + (sem8.get(i).credit) * grade_score.get(sem8.get(i).grade);
                        credits = credits + sem8.get(i).credit;
                    }
                    sgpa8 = score / semcredit;
                    cgpa = scores / credits;
                    f.write("Semester VIII SGPA - " + sgpa8 + "\tCGPA - " + cgpa);
                    f.write("\n---------------------------------------------------------------------\n\n");
                }

            } finally {
                // TODO: handle exception
                f.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void View_grades(String email, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {
            String query = "select * from courses_enrolled_by_student";
            PreparedStatement pstmt = c.prepareStatement(query);
            // pstmt.setString(1, entryno);
            ResultSet set = pstmt.executeQuery();
            String course = "";
            String grade = "";
            String Studentid = "";
            // System.out.println("\t\t\t\t\t\t\t\t\t\t\tSession - " + year1 + "-" + year2 +
            // ": " + parity);
            System.out.println("\t\t\tStudent grades as below( Note :if grade is U means not yet graded)");
            System.out.println("\t\t\t\tStudentid" + "\tCourse" + "\t" + "Grade" + "\t " + "Session");
            while (set.next()) {
                course = set.getString(2);
                grade = set.getString(7);
                Studentid = set.getString(1);
                System.out.println("\t\t\t\t" + Studentid + "\t" + course + "\t " + grade + "\t" + year1 + "-" + year2
                        + ":" + parity);
            }

            query = "select * from courses_completed_by_student";
            pstmt = c.prepareStatement(query);
            // pstmt.setString(1, entryno);
            set = pstmt.executeQuery();

            Integer pyear1 = 0, pyear2 = 0, pparity = 0;
            while (set.next()) {
                Studentid = set.getString(1);
                course = set.getString(2);
                grade = set.getString(5);
                pyear1 = set.getInt(6);
                pyear2 = set.getInt(7);
                pparity = set.getInt(8);
                System.out.println("\t\t\t\t" + Studentid + "\t" + course + "\t " + grade + "\t" + pyear1 + "-" + pyear2
                        + ":" + pparity);
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
