package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnUtils {

    public static Connection getDBCP() {
        Connection conn = null;
        String jndiName = "jdbc/mysql";
        try {
            Context initContext = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource ds = (DataSource) initContext.lookup(jndiName);
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs, String name) {
        if (rs != null) try {
            rs.close();
        } catch (SQLException ex) {
            System.out.println(name + " rs close error");
        }
        if (stmt != null) try {
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(name + " stmt close error");
        }
        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(name + " conn close error");
        }
    }
}
