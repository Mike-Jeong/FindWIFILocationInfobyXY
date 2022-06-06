import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

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
}
