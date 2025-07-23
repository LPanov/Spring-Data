package exercise.cardealer.dtos;

import lombok.Getter;
import lombok.Setter;

public class SupplierDto {
    @Setter
    @Getter
    private Long id;
    private String name;
    private Boolean isImporter;

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
