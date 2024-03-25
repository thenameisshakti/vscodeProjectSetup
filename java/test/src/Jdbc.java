import java.sql.*;

public class Jdbc {

    static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    static final String USER = "root";
    static final String PASS = "";
    static final String QUERY = "SELECT id, first, last, age FROM students";

    public static void main(String[] args) {
        try {
            // Establish connection and execute query
            executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Log the exception
        }
    }

    public static void executeQuery() throws ClassNotFoundException, SQLException {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Open a connection using try-with-resources
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {

            // Process the ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String firstName = rs.getString("first");
                String lastName = rs.getString("last");

                // Output the retrieved data
                System.out.println("ID: " + id + ", Age: " + age + ", First: " + firstName + ", Last: " + lastName);
            }
        }
    }
}