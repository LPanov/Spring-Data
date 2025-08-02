package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarInputDto;
import softuni.exam.models.dto.DealershipInputDto;
import softuni.exam.models.entity.CarType;
import softuni.exam.models.entity.enums.Car;
import softuni.exam.models.entity.enums.Dealership;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.DealershipService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final DealershipService dealershipService;


    public CarServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository, DealershipService dealershipService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.dealershipService = dealershipService;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/json/cars.json");
        return Files.readString(path);
    }

    @Override
    public String importCars() throws IOException {
        CarInputDto[] inputDtos = gson.fromJson(readCarsFileContent(), CarInputDto[].class);

        StringBuilder sb = new StringBuilder();
        for (CarInputDto inputDto : inputDtos) {
            Car car = create(inputDto);
            if (car == null) sb.append(String.format("Invalid car%n"));
            else sb.append(String.format("Successfully imported car %s - %d km.%n", car.getBrand(), car.getMileage()));
        }

        return sb.toString();
    }

    private Car create(CarInputDto inputDto) {
        if (!validationUtil.isValid(inputDto)) return null;

        try {
            Car car = modelMapper.map(inputDto, Car.class);

            Long dealershipId = inputDto.getDealership();
            if (dealershipId != null) {
                Dealership dealership = dealershipService.getByReferenceId(dealershipId);
                car.setDealership(dealership);
            }
            carRepository.save(car);

            return car;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Car getReferenceById(Long carId) {
        return carRepository.getReferenceById(carId);
    }

    @Override
    public String exportCars() {
        StringBuilder result = new StringBuilder();
        List<Car> cars = getLessDrivenCombiCars();

        for (Car car : cars) {
            result.append(String.format("Brand: %s%n", car.getBrand()));
            result.append(String.format("   *Mileage: %d km.%n", car.getMileage()));
            result.append(String.format("   **Model: %s%n", car.getModel()));
            result.append(String.format("   ***Dealership: %s%n", car.getDealership().getName()));
        }

        return result.toString();
    }

    private List<Car> getLessDrivenCombiCars() {
        return carRepository.getCarsByTypeAndMileage(CarType.COMBI, 100000);
    }
}
