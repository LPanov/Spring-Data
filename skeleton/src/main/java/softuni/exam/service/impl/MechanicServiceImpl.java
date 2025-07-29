package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicInputDto;
import softuni.exam.models.dto.PartInputDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.mechanicRepository = mechanicRepository;
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        Path path = Path.of("src/main/resources/files/json/mechanics.json");
        return Files.readString(path);
    }

    @Override
    public String importMechanics() throws IOException {
        MechanicInputDto[] inputDtos = gson.fromJson(readMechanicsFromFile(), MechanicInputDto[].class);

        StringBuilder result = new StringBuilder();
        for (MechanicInputDto inputDto : inputDtos) {
            Mechanic mechanic = create(inputDto);

            if (mechanic == null) {
                result.append("Invalid mechanic\n");
            }
            else {
                result.append(String.format("Successfully imported mechanic %s %s\n", mechanic.getFirstName(), mechanic.getLastName()));
            }
        }

        return result.toString();
    }

    private Mechanic create(MechanicInputDto inputDto) {
        if (!validator.isValid(inputDto)) return null;

        try {
            Mechanic mechanic = modelMapper.map(inputDto, Mechanic.class);
            mechanicRepository.save(mechanic);

            return mechanic;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Mechanic getReferenceByFirstName(String firstName) {
        return mechanicRepository.getReferenceByFirstName(firstName);
    }
}
