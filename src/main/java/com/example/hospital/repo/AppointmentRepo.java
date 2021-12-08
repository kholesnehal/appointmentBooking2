package com.example.hospital.repo;

import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,String> {

    Appointment findByAppointmentId(String appointment_id);
}
