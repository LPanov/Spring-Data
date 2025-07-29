package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartInputDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PartServiceImpl implements PartService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final PartRepository partRepository;
    private final Gson gson;

    public PartServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, PartRepository partRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.partRepository = partRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/json/parts.json");
        return Files.readString(path);
    }

    @Override
    public String importParts() throws IOException {
        PartInputDto[] inputDtos = gson.fromJson(readPartsFileContent(), PartInputDto[].class);

        StringBuilder result = new StringBuilder();
        for (PartInputDto inputDto : inputDtos) {
            Part part = create(inputDto);

            if (part == null) {
                result.append("Invalid part\n");
            }
            else {
                result.append(String.format("Successfully imported part %s - %.2f\n", part.getPartName(), part.getPrice()));
            }
        }

        return result.toString();
    }

    private Part create(PartInputDto inputDto) {
        if (!validator.isValid(inputDto)) return null;

        try {
            Part part = modelMapper.map(inputDto, Part.class);
            partRepository.save(part);

            return part;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Part getReferenceById(Long id) {
        return partRepository.getReferenceById(id);
    }
}
