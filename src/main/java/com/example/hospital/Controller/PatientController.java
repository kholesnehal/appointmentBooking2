package com.example.hospital.Controller;
import com.example.hospital.entity.Patient;
import com.example.hospital.helper.MyExcelHelper;
import com.example.hospital.responsemessage.ResponseMessage;
import com.example.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@RestController
public class PatientController {
    @Autowired
     PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }
@PostMapping("/upload")
@ResponseBody
public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (MyExcelHelper.checkExcelFormat(file))
    {
        try {
            patientService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
}
@PostMapping("/add")
@ResponseBody
public Patient addPatient(@RequestBody Patient patient)
{
    return patientService.addPatient(patient);
}
    @GetMapping("/patient")
    public List<Patient> getAllPatient() {
        return this.patientService.getAllPatient();
    }
}























