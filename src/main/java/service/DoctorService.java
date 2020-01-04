package service;

import entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DoctorService {


    @Autowired
    private WebClient.Builder webClientBuilder;


    public Doctor getDoctor(String username) {
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/username/" + username).retrieve().bodyToMono(Doctor.class).block();
        return doctor;
    }


    public List<Doctor> getDoctors() {
        Doctor[] doctorsArray;
        doctorsArray = webClientBuilder.build().get().uri("http://db-producer/api/doctor/doctors").retrieve().bodyToMono(Doctor[].class).block();
        List<Doctor> doctorsList= Arrays.asList(doctorsArray);
        return doctorsList;
    }

    public void updateDoctor(@RequestBody Doctor doctor){

        final String uri = "http://localhost:8082/api/doctor/update";
        System.out.println("doc info");
        System.out.println(doctor);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,doctor);


       // Flux<Doctor> messages = webClientBuilder.build().get().uri("http://db-producer/api/doctor/doctors/update/" + doctor.getUsername()).retrieve().bodyToFlux(Doctor.class);



    }



}
