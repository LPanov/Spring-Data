package exercise.cardealer.services;

import exercise.cardealer.dtos.PartDto;
import exercise.cardealer.dtos.PartInputDto;
import exercise.cardealer.dtos.SupplierDto;
import exercise.cardealer.entities.Part;
import exercise.cardealer.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface PartService {
    PartDto create(@Valid PartInputDto dto, @Valid SupplierDto supplierDto);

    Part gerReferenceById(Long id);
}
