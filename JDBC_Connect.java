package javaMysql;
import java.util.*;
import java.sql.*;

class JDBC_Connect{

    Connection conn;
    Statement s;

    String user, password, query;

    JDBC_Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/practice1";
        user = "";
        password = "";
        query = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connected to database...");

        String createStat = "CREATE TABLE IF NOT EXISTS Student (studentID VARCHAR(10) PRIMARY KEY, fname VARCHAR(20), "
                + "lname VARCHAR(20), class VARCHAR(10), division VARCHAR(10), rollNumber VARCHAR(10), city VARCHAR(20));";

        try {
            s = conn.createStatement();
            s.executeUpdate(createStat);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(int stud_id, String fname, String lname, int cls, int div, int roll, String city) {
        try {
            s = conn.createStatement();
        }catch(SQLException e) {
            System.out.println(e);
        }

        query = "INSERT INTO Student VALUES (" + "'" + Integer.toString(stud_id) + "'" + "," + "'" + fname + "'" + "," + "'" + lname + "'" + "," +
                "'" + Integer.toString(cls) + "'" + "," + "'" +  Integer.toString(div) + "'" + "," + "'" +  Integer.toString(roll) + "'" + "," + "'" + city + "'" +
                ");";

        try {
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Data inserted succesfully...");

    }

    public void select() {

        query = "SELECT * FROM Student;";
        try {
            ResultSet rs = s.executeQuery(query);
            System.out.print(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\nData displayed succesfully...");
    }

    public void update(String id, String prop, String new_prop) {
        query = "UPDATE Student SET " + prop + " = " + "'" + new_prop + "'" + "WHERE " + "studentID = " + "'" + id + "';";
        try {
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Data updated successfully...\n");
    }

    public void delete(String prop, String prop_name) {
        query = "DELETE FROM Student WHERE " + prop + " = " +  "'" + prop_name + "';";
        try {
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Data deleted successfully...\n");
    }
}


public class myclass {
    public static void main(String[] args) {
        char choice, ch;
        String fname, lname, city;
        int student_id = 1000, cls, division, roll_no;
        Scanner sc = new Scanner(System.in);
        JDBC_Connect object = new JDBC_Connect();
        System.out.print("Perform SQL Operations? (y / n): ");
        ch = sc.next().charAt(0);
        do {
            System.out.println("-----------MENU-----------\nSelect a choice from the following:");
            System.out.print("1. Insert\n2. Display\n3. Update\n4. Delete\n\nEnter a choice: ");
            choice = sc.next().charAt(0);
            switch(choice) {
                case '1':
                    System.out.print("Enter first name: ");
                    fname = sc.next();
                    System.out.print("\n");
                    System.out.print("Enter last name: ");
                    lname = sc.next();
                    System.out.print("\n");
                    System.out.print("Enter class: ");
                    cls = sc.nextInt();
                    System.out.print("\n");
                    System.out.print("Enter division: ");
                    division = sc.nextInt();
                    System.out.print("\n");
                    System.out.print("Enter roll number: ");
                    roll_no = sc.nextInt();
                    System.out.print("\n");
                    System.out.print("Enter city: ");
                    city = sc.next();
                    object.insert(student_id, fname, lname, cls, division, roll_no, city);
                    student_id += 1;
                    break;
                case '2':
                    object.select();
                    break;
                case '3':
                    String property, new_property, stud_id;
                    System.out.print("Enter ID of student: ");
                    stud_id = sc.next();
                    System.out.print("\n");
                    System.out.print("Enter property to update: ");
                    property = sc.next();
                    System.out.print("\n");
                    System.out.print("Enter new value of " + property + "for Student " + stud_id + ": ");
                    new_property = sc.next();
                    object.update(stud_id, property, new_property);
                    break;
                case '4':
                    String prop, prop_name;
                    System.out.print("Enter roll no of student: ");
                    prop = sc.next();
                    System.out.print("\n");
                    object.delete("rollNumber", prop);
                    break;
            }

            System.out.print("Perform SQL Operations? (y / n): ");
            ch = sc.next().charAt(0);
        }while(ch != 'n');

    }

}
