package com.example.robotApocalypseProject.repository;

import com.example.robotApocalypseProject.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurvivorRepository extends JpaRepository<Survivor,Integer> {

        Optional<Survivor> findByName(String name);
        List<Survivor> findByStatus(String status);     //Finding survivors who are affected by status

        List<Survivor> findByStatusNot(String status);      ////Finding survivors who are affected by status

    long countByStatus(String status); //To calculate the percentage

}
