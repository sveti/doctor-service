package entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class AppointmentModelAndView implements Serializable {

    private String diagnosis;
    private String medication;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sickLeaveStartDate;
    private int sickLeaveDays;

    public AppointmentModelAndView() {
    }

    public AppointmentModelAndView(String diagnosis, String medication, Date sickLeaveStartDate, int sickLeaveDays) {

        this.diagnosis = diagnosis;
        this.medication = medication;
        this.sickLeaveStartDate = sickLeaveStartDate;
        this.sickLeaveDays = sickLeaveDays;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Date getSickLeaveStartDate() {
        return sickLeaveStartDate;
    }

    public void setSickLeaveStartDate(Date sickLeaveStartDate) {
        this.sickLeaveStartDate = sickLeaveStartDate;
    }

    public int getSickLeaveDays() {
        return sickLeaveDays;
    }

    public void setSickLeaveDays(int sickLeaveDays) {
        this.sickLeaveDays = sickLeaveDays;
    }

    @Override
    public String toString() {
        return "AppointmentModelAndView{" +
                "diagnosis='" + diagnosis + '\'' +
                ", medication='" + medication + '\'' +
                ", sickLeaveStartDate='" + sickLeaveStartDate + '\'' +
                ", sickLeaveDays=" + sickLeaveDays +
                '}';
    }
}
