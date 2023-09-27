import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

class Student_course {
    //

    public static boolean year_allowed(String course_id, Connection c, Integer yoam) {
        try {
            String query = "select * from batch_allowed where course_id=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, course_id);
            ResultSet set = pstmt.executeQuery();

            while (set.next()) {
                if (set.getInt(2) == yoam) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // cgpa calculation-----------------------------------------------------
    public static Double calculate_cgpa(String entryno, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            // it select the courses that has been completed previously by student of
            // entryno as well grades assigned
            String query = "select * from courses_completed_by_student where entry_num=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);
            ResultSet set = pstmt.executeQuery();
            Double score_earned = 0.0;
            Double credit_earned = 0.0;
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
            while (set.next()) {
                String grade = set.getString(5);
                Double cr = set.getDouble(4);

                score_earned += (grade_score.get(grade)) * (cr);
                if (grade.equals("F")) {

                } else {
                    credit_earned += cr;
                }
            }

            Double newcgpa = (score_earned) / (credit_earned);

            return newcgpa;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static boolean is_cg_and_prereq_completed(String entryno, String courseid, Connection c,
            Double cgparequired, Double cg, Integer year1, Integer year2, Integer parity) {
        try {
            Double cgpa = calculate_cgpa(entryno, c, year1, year2, parity);

            if (cgpa < cgparequired) {
                System.out.println("Your cgpa less than required, so not allowed to get enrolled...");
                return false;
            }

            List<String> myList = new ArrayList<String>();

            // Adding elements to the List
            String query = "select * from pre_requisite where course_id=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, courseid);
            ResultSet set = pstmt.executeQuery();

            while (set.next()) {
                String prereq = set.getString(2);
                myList.add(prereq);
            }
            // checking if prereq are done before by student or not
            boolean flag = true;
            for (String str : myList) {
                query = "select * from courses_completed_by_student where course_id=? and entry_num=?";
                pstmt = c.prepareStatement(query);
                pstmt.setString(1, str);
                pstmt.setString(2, entryno);
                set = pstmt.executeQuery();
                int present = 0;
                while (set.next()) {
                    System.out.println(set.getString(2));
                    present++;
                }
                if (present == 0) {
                    flag = false;
                    System.out.println("You have not completed " + str + " which is a prereq for the " + courseid);
                    return false;
                }

            }
            System.out.println("Your cgpa and prereq conditions are satisfied...");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void Courses_offered_for_enrollment(String entryno, Connection c, Integer year1, Integer year2,
            Integer parity) {
        try {

            String query = "select * from student_info where entry_num=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);
            ResultSet set = pstmt.executeQuery();

            String student_dept = "";
            Integer year_of_addmission = 0;
            Double cgpa = 0.0;
            while (set.next()) {

                student_dept = set.getString(4);
                cgpa = set.getDouble(5);
                year_of_addmission = set.getInt(3);
            }

            // storing core courses in map
            String checkquery = "";
            PreparedStatement checkpstmt;
            ResultSet checkset;
            HashMap<String, Integer> core_course = new HashMap<>();
            checkquery = "select * from core_courses";
            checkpstmt = c.prepareStatement(checkquery);
            checkset = checkpstmt.executeQuery();
            while (checkset.next()) {
                String course = checkset.getString(1);
                core_course.put(course, 1);
            }

            // showing courses to students
            boolean nocourse = false;
            query = "select * from course_offerings where dept_name=?";

            pstmt = c.prepareStatement(query);

            pstmt.setString(1, student_dept);

            set = pstmt.executeQuery();

            while (set.next()) {
                nocourse = true;
                break;
            }
            if (nocourse == false) {
                System.out.println("\t\t\t\tNo courses for you");
            } else {
                System.out.println(student_dept + " **");
                System.out.println("\t\t\t\t Courses offered to you - \n");
                System.out.println(
                        "\t\t\t" + "Serial_no" + "  " + "Course_id" + "    " + "Department" + " \t  " + "LTPSC "
                                + "  " + "Credit" + "   ");
                query = "select * from course_offerings";

                pstmt = c.prepareStatement(query);

                set = pstmt.executeQuery();

                HashMap<Integer, String> course_choosen = new HashMap<>();

                Integer course_serialno = 1;

                while (set.next()) {
                    String course_id = set.getString(1);
                    String dept_name = set.getString(2);
                    String LTPSC = set.getString(5);

                    Double credit = set.getDouble(3);

                    // is your batch allowed for the course or not-----------------

                    if (year_allowed(course_id, c, year_of_addmission)) {

                        // check if the that course of offering is present in core_course table if not
                        // present then it has to be shown to the else if present then we have to check
                        // if it is in student department or not
                        // System.out.println(dept_name + "&&&" + student_dept);
                        if (dept_name.equals(student_dept) == false) {
                            if (dept_name.equals("SC") || dept_name.equals("GE") || dept_name
                                    .equals("HS")) {

                                course_choosen.put(course_serialno, course_id);
                                System.out.println(
                                        "\t\t\t " + course_serialno + "\t    " + course_id + "\t" + dept_name + "\t\t"
                                                + LTPSC
                                                + "  " + credit + "   ");
                                course_serialno++;
                            }
                        } else {
                            boolean notpresent_in_core_courses = true;

                            // System.out.println("try " + course_id);
                            if (core_course.containsKey(course_id))
                                notpresent_in_core_courses = false;
                            // System.out.println(notpresent_in_core_courses);

                            if (notpresent_in_core_courses == true) {
                                course_choosen.put(course_serialno, course_id);

                                System.out
                                        .println(
                                                "\t\t\t " + course_serialno + "\t    " + course_id + "\t" + dept_name
                                                        + "\t\t"
                                                        + LTPSC + "  " + credit + "   ");
                                course_serialno++;
                            } else {
                                // if the course is present in core then we have to check whether that course
                                // belong to the department of the student or not
                                if (dept_name.equals(student_dept)) {
                                    course_choosen.put(course_serialno, course_id);
                                    System.out.println(
                                            "\t\t\t " + course_serialno + "\t    " + course_id + "\t" + dept_name
                                                    + "\t\t"
                                                    + LTPSC
                                                    + "  " + credit + "   ");
                                    course_serialno++;
                                }
                            }
                        }

                    }
                }

                System.out.println("\t\t\tDo you want to enroll a course or go back. Choose below options --\n");
                Scanner scanner = new Scanner(System.in);
                System.out.println("\t\t\t1. Enroll a course");
                System.out.println("\t\t\t2. Back");

                System.out.print("\t\t\t\tEnter choice -> ");
                Integer choice;
                choice = scanner.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1) {
                    System.out.print("Enter serial no of the course shown above -> ");
                    Integer serialno = scanner.nextInt();
                    System.out.println(
                            "__________________________________________________________________________________________________________________");

                    String courseid = course_choosen.get(serialno);
                    query = "select * from course_offerings where course_id=?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, courseid);
                    set = pstmt.executeQuery();
                    String LTPSC = "";
                    Double this_course_credit = 0.0;
                    // Integer sem = 0;
                    Double cgrequired = 0.0;
                    while (set.next()) {

                        LTPSC = set.getString(5);
                        this_course_credit = set.getDouble(3);
                        // sem = set.getInt(3);
                        cgrequired = set.getDouble(6);
                    }

                    // check whether you have completed this course or not
                    query = "select * from courses_completed_by_student where course_id=? and entry_num=?";
                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, courseid);

                    pstmt.setString(2, entryno);
                    set = pstmt.executeQuery();
                    int flag1 = 0;
                    while (set.next()) {

                        flag1++;
                    }

                    // check whether he/she has already enrolled or not
                    query = "select * from courses_enrolled_by_student where course_id=? and entry_num=?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, courseid);
                    pstmt.setString(2, entryno);
                    set = pstmt.executeQuery();
                    int flag = 0;
                    while (set.next()) {

                        flag++;
                    }

                    // credit limit calculation whther this sem his remaining credit will allow him
                    // to take this course or not
                    query = "select * from courses_enrolled_by_student where  entry_num=?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, entryno);
                    set = pstmt.executeQuery();

                    Double credit_limit_checker = 0.0;

                    while (set.next()) {
                        Double this_sem_credit = set.getDouble(4);
                        credit_limit_checker = credit_limit_checker + this_sem_credit;
                    }
                    System.out.println(credit_limit_checker);

                    // calculate total credit completed in last semester by student till now by
                    // student
                    query = "select * from courses_completed_by_student where  entry_num=? and year1=? and year2=? and parity=?";
                    Integer pyear1 = 0, pyear2 = 0, pparity = 0;
                    if (parity == 2) {
                        pparity = parity - 1;
                        pyear1 = year1;
                        pyear2 = year2;
                    } else {
                        pyear1 = year1 - 1;
                        pyear2 = year2 - 1;
                        pparity = 2;
                    }
                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, entryno);
                    pstmt.setInt(2, pyear1);
                    pstmt.setInt(3, pyear2);
                    pstmt.setInt(4, pparity);
                    set = pstmt.executeQuery();

                    Double total_credit = 0.0;

                    while (set.next()) {
                        Double this_sem_credit = set.getDouble(4);
                        total_credit = total_credit + this_sem_credit;
                    }
                    // System.out.println(credit_limit_checker);

                    // calculate semester no of the student
                    Integer semester = 0;
                    Integer yoad = year_of_addmission;
                    if (parity == 2) {
                        semester = (year2 - yoad + 1) * 2;
                    } else {
                        semester = (year2 - yoad) * 2 + 1;
                    }

                    // whether you have already enrolled in a course or not
                    if (flag1 == 0) {
                        if (flag >= 1) {
                            System.out.println(courseid + " has been already enrolled by you...");
                        } else {
                            // whther your cg and prereq are satisfing or not
                            if (is_cg_and_prereq_completed(entryno, courseid, c, cgrequired, cgpa, year1, year2,
                                    parity) == true) {
                                // whther your credit limit remained for this course or not
                                if (((semester == 1 || semester == 2)
                                        && (credit_limit_checker + this_course_credit <= 18))
                                        || ((semester != 1
                                                && semester != 2)
                                                && (credit_limit_checker + this_course_credit <= 1.25
                                                        * (total_credit)))) {
                                    String query1 = "insert into courses_enrolled_by_student(entry_num,Course_id,dept_name,credit,LTPSC) values (?,?,?,?,?)";

                                    PreparedStatement pstmt1 = c.prepareStatement(query1);

                                    pstmt1.setString(1, entryno);
                                    pstmt1.setString(2, courseid);
                                    pstmt1.setString(3, student_dept);
                                    // pstmt1.setInt(4, sem);
                                    pstmt1.setDouble(4, this_course_credit);
                                    pstmt1.setString(5, LTPSC);

                                    pstmt1.executeUpdate();

                                    System.out
                                            .println("Now, Course " + courseid
                                                    + " has been added this semester to you...");
                                } else {
                                    System.out.println("\t\t\tOut of your credit allowed this semester");
                                }
                            } else {
                                System.out.println(
                                        "You are not eligible for this course either due to cgpa critera or prereq condition");
                            }
                        }
                    } else {
                        System.out.println("You have already completed this course previously");
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Courses_enrolled(String entryno, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            String query = "select * from courses_enrolled_by_student where entry_num=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);
            ResultSet set = pstmt.executeQuery();

            String courses = "";
            Double cgpa = 0.0;
            Integer serialno = 1;
            HashMap<Integer, String> course_en = new HashMap<>();
            while (set.next()) {

                courses = set.getString(2);
                course_en.put(serialno, courses);
                System.out.println(serialno + " " + courses);
                serialno++;

            }

            if (serialno == 1) {
                System.out.println("\t\t\tNo courses ,you enrolled this semester");
            } else {
                System.out.println("\t\t\tEnter below choices");
                Scanner scanner = new Scanner(System.in);
                System.out.println("\t\t\t1. Drop a course");
                System.out.println("\t\t\t2. Back");

                System.out.print("Enter choice -> ");
                Integer choice;
                choice = scanner.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1) {
                    System.out.print("Enter serial no of the course shown above to drop -> ");
                    Integer serialnum = scanner.nextInt();
                    String courseid = course_en.get(serialnum);
                    String query1 = "delete from courses_enrolled_by_student where course_id=? and grading_status=?";

                    PreparedStatement pstmt1 = c.prepareStatement(query1);

                    pstmt1.setString(1, courseid);
                    pstmt1.setString(2, "U");
                    pstmt1.executeUpdate();

                    System.out.println("\t\t\t" + courseid + "has been dropped by you..");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void Courses_completed(String entryno, Connection c, Integer year1, Integer year2, Integer parity) {
        try {
            String query = "select * from courses_completed_by_student where entry_num=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);
            ResultSet set = pstmt.executeQuery();

            String courses = "";
            Integer serialno = 1;
            // Integer sem = 0;
            Double credit = 0.0;
            String grade = "";
            Integer pyear1 = 0;
            Integer pyear2 = 0;
            Integer pparity = 0;
            HashMap<Integer, String> course_en = new HashMap<>();

            System.out.println("\t\t\t\tCourses completed by " + entryno + " -> ");
            System.out.println("\t\t\t\tSerial no \t Courses \t credit \t grade \t session");
            while (set.next()) {

                courses = set.getString(2);
                // sem = set.getInt(4);
                credit = set.getDouble(4);
                grade = set.getString(5);
                course_en.put(serialno, courses);
                pyear1 = set.getInt(6);
                pyear2 = set.getInt(7);
                pparity = set.getInt(8);
                System.out.println(
                        "\t\t\t\t" + serialno + "\t\t " + courses + "\t\t " + credit + "\t\t " + grade + "\t" + pyear1
                                + "-" + pyear2 + ":" + pparity);
                serialno++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void check_graduation(String entryno, Connection c, Integer year1, Integer year2, Integer parity) {
        try {

            String query = "select * from courses_completed_by_student where entry_num=?";

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, entryno);

            ResultSet set = pstmt.executeQuery();

            Double total_credit = 0.0;

            while (set.next()) {
                total_credit = total_credit + set.getDouble(4);
            }

            if (total_credit < 20) {
                System.out.println("\t\t\t\tNot graduation condition ful-filled");
                return;
            }

            HashMap<String, Integer> pctobepassed = new HashMap<>();

            String dept = "";

            query = "select * from student_info where entry_num=?";
            String student = entryno;
            pstmt = c.prepareStatement(query);
            pstmt.setString(1, student);
            set = pstmt.executeQuery();

            while (set.next()) {

                dept = set.getString(4);
            }

            query = "select * from course_catalog where dept_name=?";
            HashMap<String, Double> coursecredit = new HashMap<>();
            pstmt = c.prepareStatement(query);
            pstmt.setString(1, dept);
            set = pstmt.executeQuery();

            while (set.next()) {

                String course = set.getString(1);
                pctobepassed.put(course, -1);
                Double cr = set.getDouble(5);
                coursecredit.put(course, cr);
            }

            for (Map.Entry<String, Integer> entry : pctobepassed.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                query = "select * from core_courses where course_id=?";

                pstmt = c.prepareStatement(query);
                pstmt.setString(1, key);
                set = pstmt.executeQuery();

                boolean isPresent = false;
                while (set.next()) {

                    isPresent = true;
                }

                if (isPresent) {
                    pctobepassed.put(key, 0);
                }
            }
            boolean pc_passed = true, pe_passed = true, btp_passed = true;
            for (Map.Entry<String, Integer> entry : pctobepassed.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value == 0) {

                    query = "select * from courses_completed_by_student where entry_num=? and course_id = ?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, student);
                    pstmt.setString(2, key);
                    set = pstmt.executeQuery();

                    boolean isPresent = false;

                    while (set.next()) {
                        isPresent = true;
                    }

                    if (isPresent == false) {
                        pc_passed = false;
                        break;
                    }
                }
            }
            if (pc_passed == false) {
                System.out.println("NOT GRADUATING CONDITION PASSED DUE TO ALL PROGRAM CORES NOT PASSED");
                return;
            }
            // pe_check

            // let minimumno of program elective for graduating ==2
            for (Map.Entry<String, Integer> entry : pctobepassed.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value == -1) {

                    query = "select * from core_electives where course_id=?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, key);
                    set = pstmt.executeQuery();

                    boolean isPresent = false;
                    while (set.next()) {

                        isPresent = true;
                    }

                    if (isPresent) {
                        pctobepassed.put(key, 1);
                    }
                }
            }

            double pecredit = 0.0;
            Integer noofpe = 0;
            for (Map.Entry<String, Integer> entry : pctobepassed.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value == 1) {

                    // Retrieving boolean value from result set
                    query = "select * from courses_completed_by_student where entry_num=? and course_id = ?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, student);
                    pstmt.setString(2, key);
                    set = pstmt.executeQuery();

                    while (set.next()) {

                        pecredit = pecredit + coursecredit.get(key);
                        noofpe = noofpe + 1;
                    }

                }
            }

            // let minimum no of pe courses to be done =5
            if (noofpe < 2) {
                System.out.println("NOT GRADUATING CONDITION PASSED DUE TO LESS PROGRAM ELECTIVES PASSED");
                return;
            }

            //

            for (Map.Entry<String, Integer> entry : pctobepassed.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value == -1) {
                    query = "select * from core_btp where course_id=?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, key);
                    set = pstmt.executeQuery();

                    boolean isPresent = false;
                    while (set.next()) {

                        isPresent = true;
                    }

                    if (isPresent) {
                        pctobepassed.put(key, 2);
                    }
                }
            }

            pecredit = 0.0;

            for (Map.Entry<String, Integer> entry : pctobepassed.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value == 2) {

                    // Retrieving boolean value from result set
                    query = "select * from courses_completed_by_student where entry_num=? and course_id = ?";

                    pstmt = c.prepareStatement(query);
                    pstmt.setString(1, student);
                    pstmt.setString(2, key);
                    set = pstmt.executeQuery();

                    while (set.next()) {

                        pecredit = pecredit + coursecredit.get(key);
                        noofpe = noofpe + 1;
                    }

                }
            }

            // let minimum credit of btp courses to be done =20
            if (pecredit < 20) {
                System.out.println("NOT GRADUATING CONDITION PASSED DUE TO BTP NOT PASSED");
                return;
            }

            System.out.println("Student is passing the condition for graduating");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
