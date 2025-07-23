package exercise.cardealer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {
    @Setter
    @Getter
    @Column(nullable = false)
    private String name;

    @Column(name = "is_importer", nullable = false)
    private Boolean isImporter;

    @Setter
    @Getter
    @OneToMany(mappedBy = "supplier")
    private Set<Part> parts;

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }

}
