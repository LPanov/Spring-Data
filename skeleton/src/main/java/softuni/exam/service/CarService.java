package softuni.exam.service;


import jakarta.xml.bind.JAXBException;
import org.springframework.validation.annotation.Validated;
import softuni.exam.models.entity.Car;

import java.io.IOException;
import java.util.Optional;

@Validated
public interface CarService {

    boolean areImported();

    String readCarsFromFile() throws IOException;

    String importCars() throws IOException, JAXBException;

    Car getReferenceById(Long id);
}
