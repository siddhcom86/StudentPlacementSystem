
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Student {
    int id;
    String name;
    double cgpa;
    String course;

    Student(int id, String name, double cgpa, String course) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
        this.course = course;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("CGPA: " + cgpa);
        System.out.println("Course: " + course);
        System.out.println("----------------------");
    }

    // 1. Add student to MySQL
    static boolean saveStudentToDatabase(Student s) {
        String sql = "INSERT INTO students (id, name, cgpa, course) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.id);
            ps.setString(2, s.name);
            ps.setDouble(3, s.cgpa);
            ps.setString(4, s.course);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("This Student ID already exists!");
            } else {
                System.out.println("Could not save student: " + e.getMessage());
            }
            return false;
        }
    }

    // 2. View all students
    static void viewStudentsFromDatabase() {
        String sql = "SELECT id, name, cgpa, course FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("cgpa"),
                    rs.getString("course")
                );

                s.display();
            }

            if (!found) {
                System.out.println("No students found in database.");
            }

        } catch (SQLException e) {
            System.out.println("Could not view students: " + e.getMessage());
        }
    }

    // 3. Search student by ID
    static void searchStudentInDatabase(int searchId) {
        String sql = "SELECT id, name, cgpa, course FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, searchId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("cgpa"),
                        rs.getString("course")
                    );

                    System.out.println("Student found!");
                    s.display();
                } else {
                    System.out.println("Student not found!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    // 4. Update student CGPA
    static void updateStudentCGPA(int id, double newCgpa) {
        if (newCgpa < 0 || newCgpa > 10) {
            System.out.println("Invalid CGPA! Enter a value from 0 to 10.");
            return;
        }

        String sql = "UPDATE students SET cgpa = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newCgpa);
            ps.setInt(2, id);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("CGPA updated successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // 5. Delete student
    static void deleteStudentFromDatabase(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    // 6. Check placement eligibility
    static void checkPlacementEligibility() {
        String sql = "SELECT id, name, cgpa, course FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double cgpa = rs.getDouble("cgpa");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("CGPA: " + cgpa);

                if (cgpa >= 7.0) {
                    System.out.println("Eligible for Placement");
                } else {
                    System.out.println("Not Eligible for Placement");
                }

                System.out.println("----------------------");
            }

            if (!found) {
                System.out.println("No students found in database.");
            }

        } catch (SQLException e) {
            System.out.println("Eligibility check failed: " + e.getMessage());
        }
    }

    // 7. Sort students by CGPA
    static void sortStudentsByCGPA() {
        String sql = "SELECT id, name, cgpa, course FROM students ORDER BY cgpa DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("Students sorted by highest CGPA:");

            while (rs.next()) {
                found = true;

                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("cgpa"),
                    rs.getString("course")
                );

                s.display();
            }

            if (!found) {
                System.out.println("No students available in database!");
            }

        } catch (SQLException e) {
            System.out.println("Sorting failed: " + e.getMessage());
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Placement System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student CGPA");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("7. Check Placement Eligibility");
            System.out.println("8. Sort Students by CGPA");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Clear input newline

            switch (choice) {
                case 1: {
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student CGPA: ");
                    double cgpa = sc.nextDouble();
                    sc.nextLine();

                    if (cgpa < 0 || cgpa > 10) {
                        System.out.println("Invalid CGPA! Enter 0 to 10.");
                        break;
                    }

                    System.out.print("Enter Student Course: ");
                    String course = sc.nextLine();

                    Student s = new Student(id, name, cgpa, course);

                    if (saveStudentToDatabase(s)) {
                        System.out.println("Student saved to MySQL successfully!");
                    }
                    break;
                }

                case 2:
                    viewStudentsFromDatabase();
                    break;

                case 3: {
                    System.out.print("Enter Student ID to search: ");
                    int searchId = sc.nextInt();
                    searchStudentInDatabase(searchId);
                    break;
                }

                case 4: {
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter new CGPA: ");
                    double newCgpa = sc.nextDouble();

                    updateStudentCGPA(updateId, newCgpa);
                    break;
                }

                case 5: {
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();

                    deleteStudentFromDatabase(deleteId);
                    break;
                }

                case 6:
                    System.out.println("Goodbye! Come again.");
                    break;

                case 7:
                    checkPlacementEligibility();
                    break;

                case 8:
                    sortStudentsByCGPA();
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1 to 8.");
            }

        } while (choice != 6);

        sc.close();
    }
}
