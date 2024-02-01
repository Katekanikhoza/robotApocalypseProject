package com.example.robotApocalypseProject.controller;

import com.example.robotApocalypseProject.model.Robots;
import com.example.robotApocalypseProject.model.Survivor;
import com.example.robotApocalypseProject.repository.SurvivorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/survivor")
public class SurvivorController {

    private final SurvivorRepository survivorRepository;

    public SurvivorController(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    //The GetMapping below allows me to view all the Survivors that are available on my database.
    //Url: http://localhost:8080/survivor.
    @GetMapping
    public ResponseEntity<List<Survivor>> getAllSurvivors(){
        return ResponseEntity.ok(this.survivorRepository.findAll());
    }

    @PostMapping        //Adding a Survivor on the database
    public ResponseEntity<Survivor> addSurvivor(@RequestBody Survivor survivor) {
        Survivor savedSurvivor = survivorRepository.save(survivor);
        return ResponseEntity.ok(savedSurvivor);
    }

    /*Updating the survivor's location using their name instead of Id.
     But because I can post survivor data and update it from DBeaver, This part is sort of not necessary for now.
     Query Stat to update the user information is:
     UPDATE dbname SET column='new info' WHERE name='username/id';
     */
    @PutMapping("/update-location/{name}")
    public ResponseEntity<Survivor> updateSurvivorLocationByName(@PathVariable String name, @RequestParam String location) {
        Optional<Survivor> optionalSurvivor = survivorRepository.findByName(name);

        if (optionalSurvivor.isPresent()) {
            Survivor survivor = optionalSurvivor.get();
            survivor.setLocation(location);
            Survivor updatedSurvivor = survivorRepository.save(survivor);
            return ResponseEntity.ok(updatedSurvivor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // http://localhost:8080/affected to find a list of all affected survivors
    @GetMapping("/affected")
    public ResponseEntity<List<Survivor>> getAffectedSurvivors() {
        List<Survivor> affectedSurvivors = survivorRepository.findByStatus("affected");
        return ResponseEntity.ok(affectedSurvivors);
    }


    @GetMapping("/notaffected")
    public ResponseEntity<List<Survivor>> getNotAffectedSurvivors() {
        List<Survivor> notAffectedSurvivors = survivorRepository.findByStatusNot("affected");
        return ResponseEntity.ok(notAffectedSurvivors);
    }

    //Get request to display the percentage of both affected and not affected.
    // url: http://localhost:8080/survivor/percentage
    @GetMapping("/percentage")
    public ResponseEntity<String> getSurvivorPercentage() {
        long totalSurvivors = survivorRepository.count();
        long affectedSurvivors = survivorRepository.countByStatus("affected");
        long notAffectedSurvivors = totalSurvivors - affectedSurvivors;

        double affectedPercentage = ((double) affectedSurvivors / totalSurvivors) * 100;
        double notAffectedPercentage = ((double) notAffectedSurvivors / totalSurvivors) * 100;

        return ResponseEntity.ok("Affected Percentage: " + affectedPercentage +  "%<br>Not Affected Percentage: " + notAffectedPercentage + "%");

    }
}
