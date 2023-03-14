package Main;

import java.util.ArrayList;
import java.util.List;

import Database.PatientDBA;
import Main.Patient;

public class Patient extends Person{
    private static ArrayList<Patient> patientsList = new ArrayList<Patient>();
    private PatientDBA patientDBA;
    public String id, name, hospitalId, gender, aadharNo, panNo, city, mobile, doctorId;
    int age;
    public Patient(){
        //Default Constructor
    }
    public Patient(String id, String name, String hospitalId, int age, String gender, String city, String aadharNo, String panNo, String mobile, String doctorId){
        super.setId(id);
        this.id = super.getId();

        super.setName(name);
        this.name = super.getName();

        super.setHospitalId(hospitalId);
        this.hospitalId = super.getHospitalId();

        super.setAge(age);
        this.age = super.getAge();

        super.setGender(gender);
        this.gender = super.getGender();

        super.setCity(city);
        this.city = super.getCity();

        super.setAadharNo(aadharNo);
        this.aadharNo = aadharNo;

        super.setPanNo(panNo);
        this.panNo = panNo;

        super.setMobile(mobile);
        this.mobile = mobile;

        this.doctorId = doctorId;
    }

    public boolean isValid(String patientId, String optional){
        patientDBA = new PatientDBA();
        patientsList = patientDBA.getPatients();
        ArrayList<String> patientIds = patientDBA.getPatientIds();

        for(String p : patientIds){
            if(p.equals(patientId)){
                return true;
            }
        }
        return false;
    }
    public void display(String patientId, String optional){
        for(Patient p : patientsList){
            if(p.id.equals(patientId)){
                System.out.println("************************************************************************************");
                System.out.println("                              PATIENT DETAILS                                       ");
                System.out.println("************************************************************************************");

                System.out.println("Patient Id           : "+p.id);
                System.out.println("Patient Name         : "+p.name);
                System.out.println("Patient Gender       : "+p.gender);
                System.out.println("Patient City         : "+p.city);
                System.out.println("Patient Mobile       : "+p.mobile);
                System.out.println("Patient Aadhar       : "+p.aadharNo);
                System.out.println("Patient Pan          : "+p.panNo);
                System.out.println("Patient Hospital Id  : "+p.hospitalId);

                ArrayList<String> credentials = patientDBA.getPatientRecords(p.id);

                System.out.println("Patient's Doctor Id  : "+credentials.get(0));
                System.out.println("Number of Deliveries : "+Integer.parseInt(credentials.get(1)));
                System.out.println("Can give birth       : "+credentials.get(2));
                System.out.println("Reason               : "+credentials.get(3));

                System.out.println("************************************************************************************");
            }
        }
    }

    public boolean register(Patient p, String dbname){
        patientDBA = new PatientDBA();
        if(patientDBA.patientRegistration(p, dbname)){
            return true;
        }
        return false;
    }

    public void insertRecords(String hospitalName, String patId, String cgb, String reason, int deliveries){
        patientDBA = new PatientDBA();
        if(patientDBA.insertPatientRecords(hospitalName, patId, cgb, reason, deliveries)){
            System.out.println("Successfully Inserted.");
        }
    }

    public void updateRecords(String hospitalName, String patId, String cgb, String reason, int deliveries){
        patientDBA = new PatientDBA();
        if(patientDBA.updatePatientRecords(hospitalName, patId, cgb, reason, deliveries)) {
            System.out.println("Successfully Updated!");
        }
    }

    public String canRegister(String mobile, String aadharNo, String panNo){

        patientDBA = new PatientDBA();
        patientsList = patientDBA.getPatients();

        String currentPatientId = "";

        for(Patient p : patientsList){
            if(p.mobile.equals(mobile) || p.aadharNo.equals(aadharNo) || p.panNo.equals(panNo)){
                currentPatientId = p.id;
                break;
            }
        }
        if(currentPatientId.isEmpty()) return "";

        ArrayList<String> credentials = patientDBA.getPatientRecords(currentPatientId);
        if(credentials.get(2).equals("NO")){
            return currentPatientId;
        }
        return "";
    }

    public int getNumber(String hospitalName){
        patientDBA = new PatientDBA();
        int count = patientDBA.getCount(hospitalName);
        return count;
    }

    public ArrayList<String> getRecords(String patientId){
        ArrayList<String> patientRecords = patientDBA.getPatientRecords(patientId);
        return patientRecords;
    }

    public void patientLog(String hospitalName, String patId, String date, String reason){
        patientDBA = new PatientDBA();
        patientDBA.insertPatientLog(hospitalName, patId, date, reason);
        System.out.println("Successfully Inserted");
    }
}
