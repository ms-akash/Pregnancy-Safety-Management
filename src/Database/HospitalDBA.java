package Database;

//DEFAULT IMPORT
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

//USER DEFINED CLASS IMPORTS
import Main.Hospital;

public class HospitalDBA {
    private DBConnection dbConnection;
    private Connection connection;
    public List<Hospital> getHospitals() throws SQLException {
        List<Hospital> hospitalsList = new ArrayList<Hospital>();

        dbConnection = new DBConnection();
        connection = dbConnection.getConnection("0_central");

        Statement smt = connection.createStatement();
        ResultSet resultSet = smt.executeQuery("SELECT id, name FROM hospitals");

        while(resultSet.next()){
            String hospitalId = resultSet.getString(1);
            String hospitalName = resultSet.getString(2);

            hospitalsList.add(new Hospital(hospitalId, hospitalName));
        }

        return hospitalsList;
    }
}
