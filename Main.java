import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DBConnection.getConnection();

        try {
            while (true) {
                System.out.println("1. Add Student\n2. View All Students\n3. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    String sql = "INSERT INTO students (id, name, age, course) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    stmt.setString(2, name);
                    stmt.setInt(3, age);
                    stmt.setString(4, course);
                    stmt.executeUpdate();
                    System.out.println("Student Added!");

                } else if (choice == 2) {
                    String sql = "SELECT * FROM students";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " " +
                                           rs.getString("name") + " " +
                                           rs.getInt("age") + " " +
                                           rs.getString("course"));
                    }

                } else if (choice == 3) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
