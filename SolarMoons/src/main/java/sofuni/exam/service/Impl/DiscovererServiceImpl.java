package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.DiscovererInputDto;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DiscovererServiceImpl implements DiscovererService {

    private final DiscovererRepository discovererRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;


    public DiscovererServiceImpl(DiscovererRepository discovererRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.discovererRepository = discovererRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validationUtil;
    }

    @Override
    public boolean areImported() {
        return discovererRepository.count() > 0;
    }

    @Override
    public String readDiscovererFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/json/discoverers.json");
        return Files.readString(path);
    }

    @Override
    public String importDiscoverers() throws IOException {
        DiscovererInputDto[] discovererInputDtos = gson.fromJson(readDiscovererFileContent(), DiscovererInputDto[].class);

        StringBuilder result = new StringBuilder();
        for (DiscovererInputDto discovererInputDto : discovererInputDtos) {
            Discoverer discoverer = create(discovererInputDto);

            if (discoverer == null) {
                result.append("Invalid discoverer\n");
            }
            else {
                result.append(String.format("Successfully imported discoverer %s %s\n", discoverer.getFirstName(), discoverer.getLastName()));
            }
        }

        return result.toString();
    }

    private Discoverer create(DiscovererInputDto discovererInputDto) {
        if (!validator.isValid(discovererInputDto)) return null;

        for (Discoverer discoverer : discovererRepository.findAll()) {
            if (discoverer.getFirstName().equals(discovererInputDto.getFirstName()) &&
                discoverer.getLastName().equals(discovererInputDto.getLastName())) {
                return null;
            }
        }

        try {
            Discoverer discoverer = modelMapper.map(discovererInputDto, Discoverer.class);
            discovererRepository.save(discoverer);

            return discoverer;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Discoverer getReferenceById(Long id) {
        return discovererRepository.getReferenceById(id);
    }
}
