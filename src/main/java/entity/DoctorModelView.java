package entity;

import java.io.Serializable;

public class DoctorModelView implements Serializable {

    String name;
    String medicalSpeciality;

    public DoctorModelView(String name, String medicalSpeciality) {
        this.name = name;
        this.medicalSpeciality = medicalSpeciality;
    }

    public DoctorModelView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

    @Override
    public String toString() {
        return "DoctorModelView{" +
                "name='" + name + '\'' +
                ", medicalSpeciality='" + medicalSpeciality + '\'' +
                '}';
    }
}
