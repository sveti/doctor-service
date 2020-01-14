package controller;


import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.AppointmentService;
import service.DoctorService;

import java.util.List;

@RestController
public class DoctorController {


    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping("/{username}")
    public ModelAndView index(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("username", username);
        mav.addObject("doctor", doctorService.getDoctor(username));
        return mav;

    }

    @GetMapping("/edit/{username}")
    public ModelAndView edit(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("edit");
        Doctor dbDoctor = doctorService.getDoctor(username);
        DoctorModelView doctor = new DoctorModelView(dbDoctor.getName(), dbDoctor.getMedicalSpeciality());
        mav.addObject("username", username);
        mav.addObject("doctor", dbDoctor);
        mav.addObject("docUsername", username);
        return mav;

    }

    @RequestMapping(value = "/update/{username}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("username") String username, @ModelAttribute Doctor doctor) {


        System.out.println("====Dcotor from view========");
        System.out.println(doctor);

        Doctor newDoc = doctorService.getDoctor(username);

        System.out.println("====Dcotor from db========");
        System.out.println(newDoc);


        if (!doctor.getName().equals(newDoc.getName())) {
            newDoc.setName(doctor.getName());
        }
        if (!doctor.getMedicalSpeciality().equals(newDoc.getMedicalSpeciality())) {
            newDoc.setMedicalSpeciality(doctor.getMedicalSpeciality());
        }

        System.out.println("====Pass to service====");
        System.out.println(newDoc);


        doctorService.updateDoctor(newDoc);


        return new ModelAndView("redirect:/" + username);

    }

    @RequestMapping("/deleted")
    public ModelAndView deletesuccess() {

        ModelAndView mav = new ModelAndView("successfullyDeleted");
        return mav;

    }

    @RequestMapping("/setReplacement/{username}")
    public ModelAndView setReplacement(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("setReplacement");
        mav.addObject("username", username);
        return mav;

    }

    @RequestMapping("/getReplacement/{username}")
    public ModelAndView getReplacement(@PathVariable("username") String username, @RequestParam("newUsername") String newUsername) {

        doctorService.updateGP(username, newUsername);
        doctorService.deleteDoctor(username);

        ModelAndView mav = new ModelAndView("successfullyDeleted");

        return mav;

    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("username") String username) {

        Doctor doctor = doctorService.getDoctor(username);
        //if the doctor isn't a GP delete immediately
        if (doctor.isGp() == false) {
            doctorService.deleteDoctor(username);
            return new ModelAndView("redirect:/deleted");
        }
        //else ask for a replacement doctor
        else {
            return new ModelAndView("redirect:/setReplacement/" + username);
        }

    }

    @RequestMapping("/appointments/{username}")
    public ModelAndView appointments(@PathVariable("username") String username) {

        List<Appointment> appointmentList = appointmentService.getUnfinishedAppointments(username);

        boolean hasAppointments = !appointmentList.isEmpty();

        ModelAndView mav = new ModelAndView("appointments");
        mav.addObject("hasAppointments", hasAppointments);
        mav.addObject("appointments", appointmentList);
        mav.addObject("username", username);

        return mav;

    }

    @RequestMapping("/examination/{username}/{id}")
    public ModelAndView examination(@PathVariable("username") String username, @PathVariable("id") Long id) {

        Appointment appointment = appointmentService.getAppointmentByID(id);
        AppointmentModelAndView app = new AppointmentModelAndView();

        ModelAndView mav = new ModelAndView("examination");
        mav.addObject("username", username);
        mav.addObject("appointment", appointment);
        mav.addObject("app", app);

        return mav;

    }

    @RequestMapping(value = "/examinationDone/{username}/{id}", method = RequestMethod.POST)
    public ModelAndView examinationDone(@PathVariable("username") String username,
                                        @PathVariable("id") Long id,
                                        @ModelAttribute("app") AppointmentModelAndView app) {


        Appointment appointment = appointmentService.getAppointmentByID(id);


        appointment.setDiagnosis(app.getDiagnosis());
        appointment.setSickLeaveStartDate(app.getSickLeaveStartDate());
        appointment.setSickLeaveDays(app.getSickLeaveDays());
        appointment.setMedication(app.getMedication());


        appointmentService.updateAppointment(appointment);

        return new ModelAndView("redirect:/appointments/" + username);

    }

    @RequestMapping("/patientsWithDiagnosis/{username}")
    public ModelAndView patientsWithDiagnosis(@PathVariable("username") String username) {

        DiagnosisModelAndView diagnosisModelAndView = new DiagnosisModelAndView();

        ModelAndView mav = new ModelAndView("patientsWithDiagnosis");

        String diagnosis = "";
        mav.addObject("diagnosis",diagnosisModelAndView);
        mav.addObject("username", username);
        return mav;

    }

    @RequestMapping("/getPatientsWithDiagnosis/{username}")
    public ModelAndView getPatientsWithDiagnosis(@PathVariable("username") String username,
                                                 @ModelAttribute("diagnosis") DiagnosisModelAndView diagnosis) {
        DiagnosisModelAndView diagnosisModelAndView = new DiagnosisModelAndView();

        List<Patient> patients = appointmentService.getPatientsByDiagnosis(diagnosis.getDiagnosis());

        ModelAndView mav = new ModelAndView("getPatientsWithDiagnosis");
        mav.addObject("patients",patients);
        mav.addObject("username", username);
        mav.addObject("diagnosis",diagnosisModelAndView);
        return mav;

    }

    @RequestMapping("/gpOfPatients/{username}")
    public ModelAndView gpOfPatients(@PathVariable("username") String username){

        List<Patient> patients = doctorService.getPatientsGP(username);

        ModelAndView mav = new ModelAndView("gpOfPatients");

        mav.addObject("username",username);
        mav.addObject("patients",patients);


        return mav;

    }

    @RequestMapping("/totalAppointments/{username}")
    public ModelAndView totalAppointments(@PathVariable("username") String username){


        List<Appointment> appointments = appointmentService.getAppointments(username);

        ModelAndView mav = new ModelAndView("totalAppointments");

        mav.addObject("username",username);
        mav.addObject("appointments",appointments);


        return mav;
    }

    @RequestMapping("/seeappointment/{username}/{id}")
    public ModelAndView seeappointment(@PathVariable("username")String username,
                                       @PathVariable("id") Long id) {

        Appointment appointment = appointmentService.getAppointmentByID(id);

        ModelAndView mav = new ModelAndView("seeAppointment");
        mav.addObject("appointment",appointment);
        mav.addObject("username",username);


        return mav;
    }

}


