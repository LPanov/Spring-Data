package exercise.cardealer.services;

import exercise.cardealer.dtos.PartDto;
import exercise.cardealer.dtos.PartInputDto;
import exercise.cardealer.dtos.SupplierDto;
import exercise.cardealer.entities.Part;
import exercise.cardealer.entities.Supplier;
import exercise.cardealer.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PartDto create(PartInputDto dto, SupplierDto supplierDto) {
        Part part = modelMapper.map(dto, Part.class);
        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
        part.setSupplier(supplier);

        partRepository.save(part);

        return modelMapper.map(part, PartDto.class);
    }

    @Override
    public Part gerReferenceById(Long id) {
        return partRepository.getReferenceById(id);
    }
}
