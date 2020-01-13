package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Doctor implements Serializable {

    private Long id;

    private String username; // will retrieve the DB for user with such EGN as Id

    private String name; // User's full name

    private String password;

    private String medicalSpeciality;

    private boolean isGp;

    private String roles;

    private int active;

    @JsonIgnore
    private List<Appointment> appointments;
    @JsonIgnore
    public List<Patient> patients;


    public Doctor(Long id, String username, String name, String password, String medicalSpeciality, boolean isGp, String roles, int active, List<Appointment> appointments, List<Patient> patients) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.medicalSpeciality = medicalSpeciality;
        this.isGp = isGp;
        this.roles = roles;
        this.active = active;
        this.appointments = appointments;
        this.patients = patients;
    }

    public Doctor() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

    public boolean isGp() {
        return isGp;
    }

    public void setGp(boolean gp) {
        isGp = gp;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        this.roles = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", medicalSpeciality='" + medicalSpeciality + '\'' +
                ", isGp=" + isGp +
                ", roles='" + roles + '\'' +
                ", active=" + active +
                ", appointments=" + appointments +
                ", patients=" + patients +
                '}';
    }
}


