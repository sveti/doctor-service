package controller;


import entity.Doctor;
import entity.DoctorModelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.DoctorService;

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
    public ModelAndView update(@PathVariable("username") String username, @ModelAttribute DoctorModelView doctor) {


        Doctor newDoc= doctorService.getDoctor(username);

        if(!doctor.getName().equals(newDoc.getName())){
            newDoc.setName(doctor.getName());
        }
        if(!doctor.getMedicalSpeciality().equals(newDoc.getMedicalSpeciality())){
            newDoc.setMedicalSpeciality(doctor.getMedicalSpeciality());
        }


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
        mav.addObject("username",username);
        return mav;

    }

    @RequestMapping("/getReplacement/{username}")
    public ModelAndView getReplacement(@PathVariable("username") String username, @RequestParam("newUsername") String newUsername) {

        doctorService.updateGP(username,newUsername);
        doctorService.deleteDoctor(username);

        ModelAndView mav = new ModelAndView("successfullyDeleted");

        return mav;

    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("username") String username) {

        Doctor doctor = doctorService.getDoctor(username);
        //if the doctor isn't a GP delete immediately
        if(doctor.isGp()==false){
            doctorService.deleteDoctor(username);
            return new ModelAndView("redirect:/deleted");
        }
        //else ask for a replacement doctor
        else{
            return new ModelAndView("redirect:/setReplacement/" + username);
        }

    }



}


