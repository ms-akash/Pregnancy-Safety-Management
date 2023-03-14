package Database;

import Main.Doctor;
import Main.Patient;

import java.sql.*;
import java.util.ArrayList;
public class DoctorDBA {
    private DBConnection dbConnection;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ArrayList<String> getDoctors(String dbName){
        ArrayList<String> doctorsList = new ArrayList<>();

        dbConnection = new DBConnection();
        try {
            connection = dbConnection.getConnection(dbName);
            statement = connection.createStatement();

            String query = "SELECT * FROM doctor";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String doctorId = resultSet.getString(1);
                String doctorName = resultSet.getString(2);
                String designation = resultSet.getString(3);

                doctorsList.add(doctorId+","+doctorName+","+designation);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return doctorsList;
    }
}
