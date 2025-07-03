package models;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEventEntity{
    @Column(length = 500, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
