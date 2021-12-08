package com.example.hospital.service;

import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Patient;
import com.example.hospital.helper.AppointmentHelper;
import com.example.hospital.helper.MyExcelHelper;
import com.example.hospital.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;

    public void save(MultipartFile file) {
        try {
            List<Appointment> appointments= AppointmentHelper.convertExcelToListOfAppointment(file.getInputStream());
            appointmentRepo.saveAll(appointments);
        } catch ( IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Appointment> getAllAppointment()
    {
        return appointmentRepo.findAll();
    }
    public Appointment addAppointment(Appointment appointment)
    {
        return appointmentRepo.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        Appointment update = appointmentRepo.findByAppointmentId(appointment.getAppointment_id());
        update.setAppointment_status(appointment.getAppointment_status());
        update.setAppointment_date(appointment.getAppointment_date());

        return appointmentRepo.save(update);
    }

}
