package com.example.demo.model.repo;


import com.example.demo.model.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findDoctorByEmail(String email);

    Doctor getDoctorByEmail(String email);
    Doctor findDoctorById(long id);
    Doctor findDoctorByFamilyName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Doctor d SET d.isEnabled = true  WHERE d.email = ?1")
    int enableDoctor(String email);
}
