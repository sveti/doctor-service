package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient implements Serializable {

    private Long id;
    private String username; // will retrieve the DB for user with such EGN as Id
    private String password;
    private String name;
    private Date healthInsuranceDate;
    private boolean isHealthInsured;
    private Date dateOfChangedGp;
    private Doctor doctorGp;
    private List<Appointment> appointments = new ArrayList<>();

    public Patient() {
    }

    public Patient(Long id, String username, String password, String name, Date healthInsuranceDate, boolean isHealthInsured, Date dateOfChangedGp, Doctor doctorGp, List<Appointment> appointments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.healthInsuranceDate = healthInsuranceDate;
        this.isHealthInsured = isHealthInsured;
        this.dateOfChangedGp = dateOfChangedGp;
        this.doctorGp = doctorGp;
        this.appointments = appointments;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHealthInsuranceDate() {
        return healthInsuranceDate;
    }

    public void setHealthInsuranceDate(Date healthInsuranceDate) {
        this.healthInsuranceDate = healthInsuranceDate;
    }

    public boolean isHealthInsured() {
        return isHealthInsured;
    }

    public void setHealthInsured(boolean healthInsured) {
        isHealthInsured = healthInsured;
    }

    public Date getDateOfChangedGp() {
        return dateOfChangedGp;
    }

    public void setDateOfChangedGp(Date dateOfChangedGp) {
        this.dateOfChangedGp = dateOfChangedGp;
    }

    public Doctor getDoctorGp() {
        return doctorGp;
    }

    public void setDoctorGp(Doctor doctorGp) {
        this.doctorGp = doctorGp;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", healthInsuranceDate=" + healthInsuranceDate +
                ", isHealthInsured=" + isHealthInsured +
                ", dateOfChangedGp=" + dateOfChangedGp +
                ", doctorGp=" + doctorGp +
                ", appointments=" + appointments +
                '}';
    }
}