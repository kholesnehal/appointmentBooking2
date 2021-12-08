package com.example.hospital.service;
import com.example.hospital.entity.Patient;
import com.example.hospital.helper.MyExcelHelper;
import com.example.hospital.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo;

public void save(MultipartFile file) {
    try {
        List<Patient> patients= MyExcelHelper.convertExcelToListOfPatient(file.getInputStream());
        try {
            patients.forEach(l -> patientRepo.save(l));
//        patientRepo.saveAll(patients);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } catch ( IOException e) {
        throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
}
    public List<Patient> getAllPatient()
    {
        return patientRepo.findAll();
    }
    public Patient addPatient(Patient patient)
    {
        return patientRepo.save(patient);
    }

}
