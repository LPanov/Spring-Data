package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.PlanetInputDto;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;


    public PlanetServiceImpl(PlanetRepository planetRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.planetRepository = planetRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validationUtil;
    }

    @Override
    public boolean areImported() {
        return planetRepository.count() > 0;
    }

    @Override
    public String readPlanetsFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/json/planets.json");
        return Files.readString(path);
    }

    @Override
    public String importPlanets() throws IOException {
        PlanetInputDto[] planetInputDtos = gson.fromJson(readPlanetsFileContent(), PlanetInputDto[].class);

        StringBuilder result = new StringBuilder();
        for (PlanetInputDto planetInputDto : planetInputDtos) {
            Planet planet = create(planetInputDto);

            if (planet == null) {
                result.append("Invalid planet\n");
            }
            else {
                result.append(String.format("Successfully imported planet %s\n", planet.getName()));
            }
        }

        return result.toString();
    }

    private Planet create(PlanetInputDto planetInputDto) {
        if (!validator.isValid(planetInputDto)) return null;

        try {
            Planet planet = modelMapper.map(planetInputDto, Planet.class);
            planetRepository.save(planet);

            return planet;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Planet getReferenceById(Long id) {
        return planetRepository.getReferenceById(id);
    }
}
