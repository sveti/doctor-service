package controller;


import entity.Doctor;
import entity.DoctorModelView;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.DoctorService;

import java.util.HashMap;
import java.util.List;

@RestController
public class DoctorController {



    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/{username}")
    public ModelAndView index(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("doctor", doctorService.getDoctor(username));
        return mav;

    }

//    public ModelAndView login(){
//
//    }

    @GetMapping("/edit/{username}")
    public ModelAndView edit(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("edit");
        Doctor dbDoctor = doctorService.getDoctor(username);
        DoctorModelView doctor = new DoctorModelView(dbDoctor.getName(), dbDoctor.getMedicalSpeciality());
        mav.addObject("doctor", doctor);
        mav.addObject("docUsername",username);
        return mav;

    }

    @RequestMapping(value = "/update/{username}", method = RequestMethod.POST)
    public String update(@PathVariable("username") String username,@ModelAttribute DoctorModelView doctor) {


        Doctor newDoc= doctorService.getDoctor(username);

        System.out.println("=====NewDoc=====");
        System.out.println(newDoc);

        if(!doctor.getName().equals(newDoc.getName())){
            newDoc.setName(doctor.getName());
        }
        if(!doctor.getMedicalSpeciality().equals(newDoc.getMedicalSpeciality())){
            newDoc.setMedicalSpeciality(doctor.getMedicalSpeciality());
        }


        doctorService.updateDoctor(newDoc);


        return "redirect:/{username}";

    }





//    public Doctor getDoctor(@PathVariable("username") String username) {
//        return doctorService.getDoctor(username);
//    }

    @RequestMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }


}
