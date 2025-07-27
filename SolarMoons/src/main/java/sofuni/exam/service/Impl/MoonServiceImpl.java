package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.MoonInputDto;
import sofuni.exam.models.dto.MoonsImportDto;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.models.enums.Type;
import sofuni.exam.repository.MoonRepository;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.service.MoonService;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;
import sofuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class MoonServiceImpl implements MoonService {

    private final MoonRepository moonRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XmlParser xmlParser;
    private final Gson gson;
    private final DiscovererService discovererService;
    private final PlanetService planetService;


    public MoonServiceImpl(MoonRepository moonRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, Gson gson, DiscovererService discovererService, PlanetService planetService) {
        this.moonRepository = moonRepository;
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.xmlParser = xmlParser;
        this.gson = gson;
        this.discovererService = discovererService;
        this.planetService = planetService;
    }

    @Override
    public boolean areImported() {
        return moonRepository.count() > 0;
    }

    @Override
    public String readMoonsFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/xml/moons.xml");
        return Files.readString(path);
    }

    @Override
    public String importMoons() throws IOException, JAXBException {
        MoonsImportDto importDto = xmlParser.fromXml(readMoonsFileContent(), MoonsImportDto.class);

        StringBuilder result = new StringBuilder();
        for (MoonInputDto inputDto : importDto.getMoons()) {
            Moon moon = create(inputDto);
            if (moon == null) {
                result.append("Invalid moon\n");
            }
            else {
                result.append(String.format("Successfully imported moon %s\n", moon.getName()));
            }
        }

        return result.toString();
    }

    @Override
    public String exportMoons() {
        List<Moon> moons = moonRepository.getMoonsByPlanetAndByRadius(Type.GAS_GIANT, 700.0, 2000.0);

        StringBuilder result = new StringBuilder();
        for (Moon moon : moons) {
            result.append(String.format("***Moon %s is a natural satellite of %s and has a radius of %.2f km.\n", moon.getName(), moon.getPlanet().getName(), moon.getRadius()));
            result.append(String.format("****Discovered by %s %s\n", moon.getDiscoverer().getFirstName(), moon.getDiscoverer().getLastName()));
        }

        return result.toString();
    }


    private Moon create(MoonInputDto inputDto) {
        if (!validator.isValid(inputDto)) {
            return null;
        }

        try {
            Moon moon = modelMapper.map(inputDto, Moon.class);

            Long discovererId = inputDto.getDiscoverer();
            if (discovererId != null) {
                Discoverer discoverer = discovererService.getReferenceById(discovererId);

                moon.setDiscoverer(discoverer);
            }

            Long planetId = inputDto.getPlanet();
            if (planetId != null) {
                Planet planet = planetService.getReferenceById(discovererId);

                moon.setPlanet(planet);
            }

            moonRepository.save(moon);

            return moon;
        } catch (Exception e) {
            return null;
        }
    }
}
