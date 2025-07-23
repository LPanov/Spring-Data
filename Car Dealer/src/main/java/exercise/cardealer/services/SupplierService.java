package exercise.cardealer.services;

import exercise.cardealer.dtos.SupplierDto;
import exercise.cardealer.dtos.SupplierInputDto;
import exercise.cardealer.dtos.SupplierReportDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface SupplierService {
    SupplierDto create(@Valid SupplierInputDto inputDto);

    List<SupplierReportDto> generateReport(boolean isImporter);
}
