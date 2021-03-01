package model;


// takes in passenger information
public class User {
    private String name;
    private String passportNo;
    private String dateOfBirth;
    private String address;
    private String contact;
    private String emergencyContact;
    private String gender;
    private String email;

    //Constructor

    public User(String name, String passportNo, String dateOfBirth, String address, String contact,
                String emergencyContact, String gender, String email) {
        this.name = name;
        this.passportNo = passportNo;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contact = contact;
        this.emergencyContact = emergencyContact;
        this.gender = gender;
        this.email = email;
    }

    // Empty Constructor

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}