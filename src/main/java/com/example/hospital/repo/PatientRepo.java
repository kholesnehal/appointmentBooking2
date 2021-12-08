package com.example.hospital.repo;


import com.example.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {

}
