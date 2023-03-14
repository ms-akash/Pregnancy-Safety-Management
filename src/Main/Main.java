package Main;

// DEFAULT IMPORTS
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


//MAIN CLASS
public class Main {
    private static Scanner s;
    private static Patient patient;
    private static Doctor doctor;
    private static Hospital hospital;
    //DRIVER CODE
    public static void main(String[] args)throws Exception {

        //APPLICATION HOME

        System.out.println("************************************************************************************");
        System.out.println("*                                WELCOME                                           *");
        System.out.println("************************************************************************************");
        System.out.println("*                             SELECT HOSPITAL                                      *");
        System.out.println("************************************************************************************");

        hospital = new Hospital();
        List<Hospital> hospitalList = hospital.getHospitals();

        //DIPLAYING HOSPITALS
        for(Hospital h : hospitalList){
            System.out.println(h.hospitalId +" "+h.hospitalName);
        }

        String hospitalId = "";
        s = new Scanner(System.in);

        Hospital currentHospital;

        //SELECTING HOSPITAL AND VALIDATING
        while(true){
            System.out.println("ENTER HOSPITAL ID : ");
            hospitalId = s.nextLine();

            Hospital tempHospital = hospital.isValidHospital(hospitalList, hospitalId);

            if(tempHospital == null){
                System.out.println("Error!, Invalid Hospital Id");
            }else{
                currentHospital = tempHospital;
                break;
            }
        }

        String dbName = currentHospital.hospitalId +"_"+currentHospital.hospitalName.toLowerCase();
        dbName = dbName.substring(1);

        System.out.println(dbName);

        //MENU
        System.out.println("************************************************************************************");
        System.out.println("                           WELCOME TO "+currentHospital.hospitalName+"                                          ");
        System.out.println("************************************************************************************");


        int option;
        boolean exit = false;

        while(true){
            patient = new Patient();
            doctor = new Doctor();

            System.out.println("SELECT YOUR OPTION");
            System.out.println("1. Patient Details\n2. Patient Registration\n3. Insert/Update Patient Record\n4. Patient Checkup\n5. Exit");

            System.out.println("ENTER YOUR OPTION : ");
            option = s.nextInt();
            s.nextLine();
            switch (option){
                case 1:
                    while(true) {
                        System.out.println("ENTER PATIENT ID (P(HospitalId)_PatientNumber) : ");
                        String patientId = s.nextLine();
                        if(patient.isValid(patientId, "")) {
                            patient.display(patientId, "");
                            break;
                        }else{
                            System.out.println("Invalid Patient ID!");
                        }
                    }
                    break;
                case 2:
                    String patientId = "P"+currentHospital.hospitalId+"_"+patient.getNumber(dbName);
                    System.out.println(patientId);

                    System.out.println("Enter patient name : ");
                    String patientName = s.nextLine();

                    System.out.println("Enter patient age : ");
                    int patientAge = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter patient gender : ");
                    String patientGender = s.nextLine();

                    System.out.println("Enter patient city : ");
                    String patientCity = s.nextLine();

                    System.out.println("Enter patient Aadhar Number : ");
                    String patientAadhar = s.nextLine();

                    System.out.println("Enter patient PAN Number : ");
                    String patientPan = s.nextLine();

                    System.out.println("Enter patient mobile number");
                    String mobileNumber = s.nextLine();

                    String doctorId = "";
                    while (true){
                        System.out.println("************************************************************************************");
                        System.out.println("*                               SELECT DOCTOR                                      *");
                        System.out.println("************************************************************************************");

                        doctor.displayAll(dbName);

                        System.out.println("************************************************************************************");
                        System.out.println();

                        System.out.println("SELECT A DOCTOR : ");
                        doctorId = s.nextLine();

                        if(doctor.isValid(doctorId, dbName)){
                            break;
                        }else{
                            System.out.println("Invalid Doctor Id");
                        }
                    }
                    Patient newPatient = new Patient(patientId, patientName, currentHospital.hospitalId, patientAge, patientGender, patientCity, patientAadhar, patientPan, mobileNumber, doctorId);
                    String newPid = patient.canRegister(mobileNumber, patientAadhar, patientPan);
                    if(newPid.isEmpty()){
                        if(patient.register(newPatient, dbName)){
                            System.out.println("Registration Successfull!");
                            System.out.println("PATIENT ID : "+patientId);
                        }
                    }else{
                        ArrayList<String> patientRecord = patient.getRecords(newPid);
                        System.out.println("PATIENT"+patientRecord.get(4)+" CANNOT BE ABLE TO GIVE BIRTH DUE TO "+patientRecord.get(3));
                        System.out.println("BELOW ARE THE PATIENT DETAILS");
                        patient.display(newPid, "");
                    }
                    break;
                case 3:
                    while(true) {
                        System.out.println("ENTER PATIENT ID (P(HospitalId)_PatientNumber) : ");
                        String pId = s.nextLine();
                        if(patient.isValid(pId, "")) {
                            while(true) {
                                System.out.println("1. Insert Patient Record\n2. Update Patient Record");
                                System.out.println("SELECT OPTION : ");
                                int select = s.nextInt();
                                s.nextLine();

                                if(select == 1){
                                    System.out.println("ENTER WHETHER PATIENT CAN GIVE BIRTH OR NOT : ");
                                    String cgb = s.nextLine();

                                    System.out.println("ENTER REASON (IF NOT enter 'null') : ");
                                    String reason = s.nextLine();

                                    System.out.println("ENTER NUMBER OF DELIVERIES : ");
                                    int deliveries = s.nextInt();

                                    patient.insertRecords(dbName, pId, cgb, reason, deliveries);
                                    break;
                                }else if(select == 2){
                                    System.out.println("ENTER WHETHER PATIENT CAN GIVE BIRTH OR NOT : ");
                                    String cgb = s.nextLine();

                                    System.out.println("ENTER REASON (IF NOT enter 'null') : ");
                                    String reason = s.nextLine();

                                    System.out.println("ENTER NUMBER OF DELIVERIES : ");
                                    int deliveries = s.nextInt();

                                    patient.updateRecords(dbName, pId, cgb, reason, deliveries);
                                    break;
                                }else{
                                    System.out.println("Invalid option!");
                                }
                            }
                            break;
                        }else{
                            System.out.println("Invalid Patient ID!");
                        }
                    }
                    break;
                case 4:
                    while(true) {
                        System.out.println("ENTER PATIENT ID (P(HospitalId)_PatientNumber) : ");
                        String patId = s.nextLine();
                        if(patient.isValid(patId, "")) {
                            System.out.println("ENTER REASON : ");
                            String reason = s.nextLine();

                            String date = String.valueOf(java.time.LocalDate.now());
                            patient.patientLog(dbName, patId, date, reason);
                            break;
                        }else{
                            System.out.println("Invalid Patient ID!");
                        }
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("INVALID OPTION!");
                    break;
            }

            while(true) {
                System.out.println("DO YOU WANT TO CONTINUE (Y/N) : ");
                char decision = s.next().charAt(0);

                if(decision == 'Y'){
                    break;
                } else if (decision == 'N') {
                    exit = true;
                    break;
                }else{
                    System.out.println("Enter a Valid Option!");
                }
            }

            if(exit){
                System.exit(0);
                break;
            }
        }

        System.out.println("Completed");
    }
}