package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection getConnection(String db)throws SQLException {
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "akash0211");
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
