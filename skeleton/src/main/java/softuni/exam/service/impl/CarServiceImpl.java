package softuni.exam.service.impl;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarInputDto;
import softuni.exam.models.dto.CarsImportDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;
    private final XmlParser xmlParser;


    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        Path path = Path.of("src/main/resources/files/xml/cars.xml");
        return Files.readString(path);
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        CarsImportDto importDto = xmlParser.fromXml(readCarsFromFile(), CarsImportDto.class);

        StringBuilder result = new StringBuilder();
        for (CarInputDto inputDto : importDto.getCars()) {
            Car car = create(inputDto);
            if (car == null) {
                result.append("Invalid car\n");
            }
            else {
                result.append(String.format("Successfully imported car %s - %s\n", car.getCarMake(), car.getCarModel()));
            }
        }

        return result.toString();
    }

    private Car create(CarInputDto inputDto) {
        if (!validator.isValid(inputDto)) {
            return null;
        }

        try {
            Car car = modelMapper.map(inputDto, Car.class);
            carRepository.save(car);

            return car;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Car getReferenceById(Long id) {
        return carRepository.getReferenceById(id);
    }
}
