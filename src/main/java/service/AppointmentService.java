package service;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AppointmentService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private AppointmentService appointmentService;


    public Appointment getAppointmentByID(Long id){


        Appointment appointment = webClientBuilder.build().get().uri("http://db-producer/api/appointment/" + id).retrieve().bodyToMono(Appointment.class).block();


        Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/findByAppointment/"+appointment.getId()).retrieve().bodyToMono(Patient.class).block();
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/findByAppointment/" +appointment.getId()).retrieve().bodyToMono(Doctor.class).block();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);


        return appointment;

    }

    public List<Appointment> getAppointments(String username){

        Appointment[] appointments;
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/username/" + username).retrieve().bodyToMono(Doctor.class).block();

        appointments = webClientBuilder.build().get().uri("http://db-producer/api/appointment/appointments/doctor/" + username).retrieve().bodyToMono(Appointment[].class).block();
        List<Appointment> asList= Arrays.asList(appointments);
        for (Appointment appointment :asList) {

            appointment.setDoctor(doctor);
            Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/findByAppointment/"+appointment.getId()).retrieve().bodyToMono(Patient.class).block();
            appointment.setPatient(patient);

        }
        return asList;

    }

    public List<Appointment> getAppointmentsByPatient(String username){

        Appointment[] appointments;
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/username/" + username).retrieve().bodyToMono(Doctor.class).block();

        appointments = webClientBuilder.build().get().uri("http://db-producer/api/appointment/appointments/patient/" + username).retrieve().bodyToMono(Appointment[].class).block();
        List<Appointment> asList= Arrays.asList(appointments);
        for (Appointment app :asList) {

            app.setDoctor(doctor);
            Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/findByAppointment/"+app.getId()).retrieve().bodyToMono(Patient.class).block();
            app.setPatient(patient);

        }
        return asList;
    }

    public  List<Appointment> getUnfinishedAppointments(String username){


        List<Appointment> asList= getAppointments(username);

        List<Appointment> unfinished = new ArrayList<>();


        for (Appointment appointment :asList) {
            if(appointment.getDiagnosis().equals("NOTSET")){
                unfinished.add(appointment);
            }
        }

        return unfinished;

    }

    public void updateAppointment(@RequestBody Appointment appointment){

        Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/findByAppointment/"+appointment.getId()).retrieve().bodyToMono(Patient.class).block();
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/findByAppointment/" +appointment.getId()).retrieve().bodyToMono(Doctor.class).block();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        final String uri = "http://localhost:8082/api/appointment/update";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,appointment);
    }

    public List<Patient> getPatientsByDiagnosis(String diagnosis){

         Patient[] patients = webClientBuilder.build().get().uri("http://db-producer/api/appointment/appointments/patients/"+diagnosis).retrieve().bodyToMono(Patient[].class).block();
        List<Patient> patientList =Arrays.asList(patients);

        return patientList;

    }

    public Patient getPatientByUsername(String username){

        Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/username/" + username).retrieve().bodyToMono(Patient.class).block();
        Doctor doctor =  webClientBuilder.build().get().uri("http://db-producer/api/patient/" + username+"/doctor").retrieve().bodyToMono(Doctor.class).block();
        patient.setDoctorGp(doctor);

        List<Appointment> app = appointmentService.getAppointmentsByPatient(username);
        patient.setAppointments(app);

        return patient;
    }

}
