package sofuni.exam.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sofuni.exam.models.entity.Planet;

import java.io.IOException;

public interface PlanetService {

    boolean areImported();

    String readPlanetsFileContent() throws IOException;

    String importPlanets() throws IOException;

    Planet getReferenceById(Long id);
}
