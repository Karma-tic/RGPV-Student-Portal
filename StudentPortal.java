import java.sql.*;
import java.util.Scanner;

public class StudentPortal {
    private static final String URL = "jdbc:sqlite:rgpv_data.db";

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(URL);
            createTable(conn);
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- RGPV STUDENT PORTAL ---");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update CGPA");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addStudent(conn, scanner);
                        break;
                    case 2:
                        viewStudents(conn);
                        break;
                    case 3:
                        updateStudent(conn, scanner);
                        break;
                    case 4:
                        deleteStudent(conn, scanner);
                        break;
                    case 5:
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "name TEXT NOT NULL, " +
                     "branch TEXT NOT NULL, " +
                     "cgpa REAL)";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    private static void addStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Branch: ");
        String branch = scanner.next();
        System.out.print("CGPA: ");
        double cgpa = scanner.nextDouble();

        String sql = "INSERT INTO students(name, branch, cgpa) VALUES(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, branch);
        pstmt.setDouble(3, cgpa);
        pstmt.executeUpdate();
        System.out.println("Student added.");
    }

    private static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("\nID\tName\tBranch\tCGPA");
        System.out.println("--------------------------------");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + 
                               rs.getString("name") + "\t" + 
                               rs.getString("branch") + "\t" + 
                               rs.getDouble("cgpa"));
        }
    }

    private static void updateStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        System.out.print("New CGPA: ");
        double cgpa = scanner.nextDouble();

        String sql = "UPDATE students SET cgpa = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, cgpa);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        System.out.println("Updated.");
    }

    private static void deleteStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM students WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Deleted.");
    }
}
