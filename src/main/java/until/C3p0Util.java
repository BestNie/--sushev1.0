package until;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Util {
    public static Connection getconnection() throws SQLException {
        DataSource ds =new ComboPooledDataSource();
        Connection connection = ds.getConnection();
        return connection;
    }
}

