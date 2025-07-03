package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {
    @Column(name = "name", length = 500, unique = true, nullable = false)
    private String medicName;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Prescription> prescriptions;

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }
}
