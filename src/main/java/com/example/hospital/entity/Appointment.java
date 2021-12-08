package com.example.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private String appointment_id;
    private Date appointment_date;
    private String appointment_time;
    private String payment_id;
    private String appointment_status;
    private String doctor_id;
    private String patient_id;
    private String hospital_id;



}
