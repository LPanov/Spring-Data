package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DealershipInputDto;
import softuni.exam.models.entity.enums.Dealership;
import softuni.exam.repository.DealershipRepository;
import softuni.exam.service.DealershipService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DealershipServiceImpl implements DealershipService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final DealershipRepository dealershipRepository;
    private final ValidationUtil validationUtil;


    public DealershipServiceImpl(Gson gson, ModelMapper modelMapper, DealershipRepository dealershipRepository, ValidationUtil validationUtil) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.dealershipRepository = dealershipRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return dealershipRepository.count() > 0;
    }

    @Override
    public String readDealershipsFromFile() throws IOException {
        Path path = Path.of("src/main/resources/files/json/dealerships.json");
        return Files.readString(path);
    }

    @Override
    public String importDealerships() throws IOException {
        DealershipInputDto[] inputDtos = gson.fromJson(readDealershipsFromFile(), DealershipInputDto[].class);

        StringBuilder sb = new StringBuilder();
        for (DealershipInputDto inputDto : inputDtos) {
            Dealership dealership = create(inputDto);
            if (dealership == null) sb.append(String.format("Invalid dealership%n"));
            else sb.append(String.format("Successfully imported dealership %s%n", dealership.getName()));
        }

        return sb.toString();
    }

    private Dealership create(DealershipInputDto inputDto) {
        if (!validationUtil.isValid(inputDto)) return null;

        try {
            Dealership dealership = modelMapper.map(inputDto, Dealership.class);
            if (dealership.getName().length() < 3) {
                return null;
            }
            dealershipRepository.save(dealership);

            return dealership;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Dealership getByReferenceId(Long referenceId) {
        return dealershipRepository.getReferenceById(referenceId);
    }
}
