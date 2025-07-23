package exercise.cardealer.services;

import exercise.cardealer.dtos.SupplierDto;
import exercise.cardealer.dtos.SupplierInputDto;
import exercise.cardealer.dtos.SupplierReportDto;
import exercise.cardealer.entities.Supplier;
import exercise.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;
    private final ModelMapper modelMapper;


    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.repository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierDto create(SupplierInputDto inputDto) {
        Supplier supplier = modelMapper.map(inputDto, Supplier.class);
        repository.save(supplier);

        return modelMapper.map(supplier, SupplierDto.class);
    }

    @Override
    public List<SupplierReportDto> generateReport(boolean isImporter) {
        return repository.generateReport(isImporter);
    }
}
