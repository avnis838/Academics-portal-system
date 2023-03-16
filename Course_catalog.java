import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Course_catalog {
    public static void menu() {
        try {
            Connection c = ConnectionProvider.getConnection();
            String query = "select * from course_catalog";

            Statement stmt = c.createStatement();
            ResultSet set = stmt.executeQuery(query);

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

                System.out.println("\tcourse_id\t");

            }

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
