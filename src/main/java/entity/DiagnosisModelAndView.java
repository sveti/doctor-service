package entity;

public class DiagnosisModelAndView {
    private String diagnosis;

    public DiagnosisModelAndView() {
    }

    public DiagnosisModelAndView(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
