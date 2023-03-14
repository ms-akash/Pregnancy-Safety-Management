package Main;

//DEFAULT IMPORT
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//USER DEFINED CLASS IMPORTS
import Database.HospitalDBA;
import com.mysql.cj.conf.HostInfo;

public class Hospital {
    private HospitalDBA hospitalDBA;
    public String hospitalId, hospitalName;

    Hospital(){
        // Default Constructor
    }

    //CONSTRUCTOR TO INITIALIZE VALUES
    public Hospital(String hospitalId, String hospitalName){
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
    }

    public List<Hospital> getHospitals(){
        List<Hospital> hospitals = new ArrayList<Hospital>();

        hospitalDBA = new HospitalDBA();

        try {
            hospitals = hospitalDBA.getHospitals();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return hospitals;
    }

    public Hospital isValidHospital(List<Hospital> hospitalList, String hid){
        for(Hospital h : hospitalList){
            if(h.hospitalId.equals(hid)){
                return h;
            }
        }
        return null;
    }

}
