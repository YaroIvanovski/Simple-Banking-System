
package banking;

import java.sql.*;

public class Connect {

    static Connection connect() {
        Connection conn = null;
        String url = "jdbc:sqlite:card.s3db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    static void createTable() {
        String addAccount = "CREATE TABLE IF NOT EXISTS card ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	number TEXT,"
                + "	pin TEXT,"
                + " balance INTEGER DEFAULT 0"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(addAccount);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void insert() {
        String sql = "INSERT INTO card(number,pin,balance) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Bank.cardNum);
            pstmt.setString(2, Bank.pin);
            pstmt.setInt(3, Bank.value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}