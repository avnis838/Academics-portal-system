import java.sql.*;
import java.util.*;
import java.io.*;

class Applicant {
    String Id;
    String Password;
    String Role;

    public boolean authenticate(Connection c) {
        boolean flag = false;
        try {

            String query = "select * from stakeholder";

            Statement stmt = c.createStatement();
            ResultSet set = stmt.executeQuery(query);

            while (set.next()) {
                String id = set.getString(3);
                String pass = set.getString(4);
                String rol = set.getString(1);

                // System.out.println(email + " " + pass + " " + rol);
                // System.out.println(this.Email + " " + this.Password + " " + this.Role);
                String s1 = this.Id, s2 = this.Password, s3 = this.Role;
                if (s1.equals(id) && s2.equals(pass) && s3.equals(rol)) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}

public class Login {

    public static void main(String args[])

    {
        try {
            Connection c = ConnectionProvider.getConnection();
            int choice;
            Scanner scanner = new Scanner(System.in);
            do {
                // Menu main page

                // current_session.session(c);

                Integer pyear1 = 0, pyear2 = 0, pparity = 0;
                String query = "SELECT * FROM completed_sessions ORDER BY id DESC LIMIT 1";
                PreparedStatement pstmt = c.prepareStatement(query);

                ResultSet set = pstmt.executeQuery();

                while (set.next()) {
                    pyear1 = set.getInt(2);
                    pyear2 = set.getInt(3);
                    pparity = set.getInt(4);
                }
                Integer year1 = 0, year2 = 0, parity = 0;
                if (pparity == 2) {
                    parity = 1;
                    year1 = pyear1 + 1;
                    year2 = pyear2 + 1;
                } else {
                    year1 = pyear1;
                    year2 = pyear2;
                    parity = 2;
                }
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                System.out.println("\t\t\t\t\t\t\t\t\t\t\tSession - " + year1 + "-" + year2 + ": " + parity);

                choice = 0;
                System.out.println("\t\t\t\tLOGIN as -");
                System.out.println("\t\t\t\t\t1. Student");
                System.out.println("\t\t\t\t\t2. Faculty");
                System.out.println("\t\t\t\t\t3. Academic office");
                System.out.println("\t\t\t\t\t4. Exit");

                // object for applicant
                Applicant person = new Applicant();
                System.out.print("Enter choice->");
                choice = scanner.nextInt();
                System.out.println(
                        "__________________________________________________________________________________________________________________");

                if (choice == 1 || choice == 2 || choice == 3) {
                    if (choice == 1) {
                        person.Role = "St";
                    } else if (choice == 2) {
                        person.Role = "Fc";
                    } else if (choice == 3) {
                        person.Role = "Ao";
                    }
                    // promt to enter email

                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                    System.out.print("Enter id-> ");
                    String str1 = br.readLine();
                    person.Id = str1;

                    // prompt to enter password
                    System.out.print("Enter password-> ");
                    String str2 = br.readLine();

                    person.Password = str2;
                    System.out.println(str1 + " " + str2);
                    boolean isValid = person.authenticate(c);
                    // System.out.println(isValid);
                    // System.out.println((str1.length()) + " " + (str2.length()));
                    if (isValid) {
                        System.out.println("Login successfully....");
                        if (person.Role == "St") {
                            Student.menu(person.Id, c, year1, year2, parity);
                        }
                        if (person.Role == "Fc") {
                            Faculty.menu(person.Id, c, year1, year2, parity);
                        }
                        if (person.Role == "Ao") {
                            Academic.menu(person.Id, c, year1, year2, parity);
                        }
                    } else {
                        System.out.println("Wrong credentials!!!");
                    }

                } else if (choice == 4) {
                    System.out.println("Exited");
                    break;
                } else {
                    System.out.println("Wrong choice");
                }

            } while (choice != 4);
            scanner.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}