package Main;

import Database.DoctorDBA;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person{
    private static ArrayList<String> doctorsList = new ArrayList<>();
    private DoctorDBA doctorDBA;
    String id, name, hospitalId, gender, city, mobile;
    String designation;
    public Doctor (){
        //Default Constructor
    }
    public Doctor(String id, String name, String hospitalId, String gender, String city,String mobile, String designation){
        super.setId(id);
        this.id = super.getId();

        super.setName(name);
        this.name = super.getName();

        super.setHospitalId(hospitalId);
        this.hospitalId = super.getHospitalId();

        super.setGender(gender);
        this.gender = super.getGender();

        super.setCity(city);
        this.city = super.getCity();

        super.setMobile(mobile);
        this.mobile = mobile;

        this.designation = designation;
    }

    public void displayAll(String dbName){
        doctorDBA = new DoctorDBA();
        doctorsList = doctorDBA.getDoctors(dbName);

        for(String string : doctorsList){
            System.out.println(string.replaceAll(",", " "));
        }
    }
    public boolean isValid(String doctorId, String dbName){
        for(String string : doctorsList){
            String doct[] = string.split(",");

            if(doct[0].equals(doctorId)){
                return true;
            }
        }

        return false;
    }
    public void display(String id, String optional){
    }
    public boolean register(Patient doctor, String designation){
        return false;
    }
    public int getNumber(String hospitalName){
        return  0;
    }
}
