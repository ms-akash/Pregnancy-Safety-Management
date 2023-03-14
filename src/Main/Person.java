package Main;

import java.util.List;
public abstract class Person {
    private String id, name, hospitalId, gender, aadharNo, panNo, city, mobile;
    private int age;

    //SETTERS
    void setId(String id){
        this.id = id;
    }
    void setName(String name){
        this.name = name;
    }
    void setHospitalId(String hospitalId){
        this.hospitalId = hospitalId;
    }
    void setAge(int age){
        this.age = age;
    }
    void setAadharNo(String aadharNo){

        this.aadharNo = aadharNo;
    }
    void setPanNo(String panNo){

        this.panNo = panNo;
    }
    void setMobile(String mobile){
        this.mobile = mobile;
    }
    void setGender(String gender){
        this.gender = gender;
    }
    void setCity(String city){
        this.city = city;
    }

    //GETTER
    String getId(){
        return this.id;
    }
    String getHospitalId(){
        return this.hospitalId;
    }
    String getName(){
        return this.name;
    }
    String getAadharNo(){
        return this.aadharNo;
    }
    String getPanNo(){
        return this.panNo;
    }
    String getMobile(){
        return this.mobile;
    }
    String getGender(){
        return this.gender;
    }
    String getCity(){
        return city;
    }
    int getAge(){
        return this.age;
    }

    abstract void display(String id, String optional);
    abstract boolean register(Patient p, String optional);
    abstract int getNumber(String hospitalName);
    abstract boolean isValid(String id, String optional);
}
