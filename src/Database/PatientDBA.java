package Database;

import Database.DBConnection;
import Main.Patient;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDBA {
    private DBConnection dbConnection;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ArrayList<Patient> getPatients(){
        ArrayList<Patient> patientList = new ArrayList<>();

        dbConnection = new DBConnection();
        try {
            connection = dbConnection.getConnection("0_central");
            statement = connection.createStatement();

            String query = "SELECT p.pid, pname, hid , gender, mobile, city, adhaar, pan, did FROM patient p CROSS JOIN patient_record pr ON p.pid = pr.pid CROSS JOIN patient_details pd ON p.pid = pd.pid;";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String patientId = resultSet.getString(1);
                String patientName = resultSet.getString(2);
                String hospitalId = resultSet.getString(3);
                String patientGender = resultSet.getString(4);
                String patientMobile = resultSet.getString(5);
                String patientCity = resultSet.getString(6);
                String patientAadhar = resultSet.getString(7);
                String patientPan = resultSet.getString(8);
                String patientDoctorId = resultSet.getString(9);


                Patient patient = new Patient(patientId, patientName, hospitalId, 25, patientGender, patientCity, patientAadhar, patientPan, patientMobile, patientDoctorId);
                patientList.add(patient);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return patientList;
    }

    public ArrayList<String> getPatientIds(){
        ArrayList<String> patientIds = new ArrayList<>();

        dbConnection = new DBConnection();
        try {
            connection = dbConnection.getConnection("0_central");
            statement = connection.createStatement();

            String query = "SELECT pid FROM patient";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                patientIds.add(resultSet.getString(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return patientIds;
    }

    public ArrayList<String> getPatientRecords(String patientId){
        ArrayList<String> patientRecords = new ArrayList<>();

        dbConnection = new DBConnection();
        try {
            connection = dbConnection.getConnection("0_central");
            statement = connection.createStatement();

            String query = "SELECT did, no_of_deliveries, can_give_birth, reason, pname FROM patient p LEFT JOIN patient_record pr ON p.pid = pr.pid RIGHT JOIN patient_details pd ON p.pid = pd.pid WHERE p.pid = '"+patientId+"';";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                patientRecords.add(resultSet.getString(1));
                patientRecords.add(String.valueOf(resultSet.getString(2)));
                patientRecords.add(resultSet.getString(3));
                patientRecords.add(resultSet.getString(4));
                patientRecords.add(resultSet.getString(5));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return patientRecords;
    }
    public int getCount(String hospitalName){
        int count = 0;

        try {
            dbConnection = new DBConnection();
            connection = dbConnection.getConnection(hospitalName);
            statement = connection.createStatement();

            String query = "SELECT COUNT(pid) FROM patient";

            resultSet = statement.executeQuery(query);
            resultSet.next();

            count = resultSet.getInt(1);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return count;
    }
    public boolean patientRegistration(Patient p, String dbName){
        DBConnection db1 = new DBConnection();
        DBConnection db2 = new DBConnection();
        try {
            Connection con1 = db1.getConnection("0_central");
            Connection con2 = db2.getConnection(dbName);

            Statement smt1 = con1.createStatement();
            Statement smt2 = con2.createStatement();

            String insertPatient1 = "INSERT INTO patient(pid, pname, did, hid) VALUES('"+p.id+"', '"+p.name+"', '"+p.doctorId+"', '"+p.hospitalId+"');";
            String insertPatient2 = "INSERT INTO patient(pid, pname, did) VALUES('"+p.id+"', '"+p.name+"', '"+p.doctorId+"');";

            String insertDetails = "INSERT INTO patient_details(pid, adhaar, pan, mobile, gender, city) VALUES('"+p.id+"', '"+p.aadharNo+"', '"+p.panNo+"', '"+p.mobile+"', '"+p.gender+"', '"+p.city+"');";

            smt1.executeUpdate(insertPatient1);
            smt2.executeUpdate(insertPatient2);

            smt1.executeUpdate(insertDetails);
            smt2.executeUpdate(insertDetails);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean insertPatientRecords(String hospitalName, String patiendId, String cgb, String reason, int deliveries){
        DBConnection db1 = new DBConnection();
        DBConnection db2 = new DBConnection();
        try {
            Connection con1 = db1.getConnection("0_central");
            Connection con2 = db2.getConnection(hospitalName);

            Statement smt1 = con1.createStatement();
            Statement smt2 = con2.createStatement();

            String patientRecord = "INSERT INTO patient_record(pid, can_give_birth, reason, no_of_deliveries) values('"+patiendId+"', '"+cgb+"', '"+reason+"', '"+deliveries+"');";

            smt1.executeUpdate(patientRecord);
            smt2.executeUpdate(patientRecord);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean updatePatientRecords(String hospitalName, String patiendId, String cgb, String reason, int deliveries){
        DBConnection db1 = new DBConnection();
        DBConnection db2 = new DBConnection();
        try {
            Connection con1 = db1.getConnection("0_central");
            Connection con2 = db2.getConnection(hospitalName);

            Statement smt1 = con1.createStatement();
            Statement smt2 = con2.createStatement();

            String patientRecord = "UPDATE patient_record SET can_give_birth = '"+cgb+"', reason = '"+reason+"', no_of_deliveries = "+deliveries+" WHERE pid = '"+patiendId+"';";

            smt1.executeUpdate(patientRecord);
            smt2.executeUpdate(patientRecord);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public void insertPatientLog( String hname, String pid, String date, String reason){
        DBConnection dbc = new DBConnection();
        try {
            Connection con1 = dbc.getConnection(hname);
            Connection con2 = dbc.getConnection("0_central");

            String query = "INSERT INTO patient_log(pid, pdate, reason) VALUES('" + pid + "', '" + date + "', '" + reason + "');";

            Statement smt1 = con1.createStatement();
            Statement smt2 = con2.createStatement();

            smt1.executeUpdate(query);
            smt2.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
