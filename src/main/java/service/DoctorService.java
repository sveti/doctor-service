package service;

import entity.Appointment;
import entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

//        webClientBuilder.build()
//                .method(HttpMethod.PUT)
//                .uri("http://db-producer/api/doctor/update",doctor)
//                .retrieve()
//                .bodyToMono(Map.class)
//                .block();

        final String uri = "http://localhost:8082/api/doctor/update";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,doctor);


    }

    public void updateGP(String username, String newDocUsername){

      //  webClientBuilder.build().put().uri("http://db-producer/api/doctor/updateGP/" + username + "/"+ newDocUsername,newDocUsername);
        final String uri = "http://localhost:8082/api/doctor/updateGP/" + username + "/"+ newDocUsername;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,username,newDocUsername);
    }


    public void deleteDoctor(String username){
//webClientBuilder.build().delete().uri("http://db-producer/api/doctor/delete/" + username,username);

        final String uri = "http://localhost:8082/api/doctor/delete/" + username;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri,username);

    }


}
