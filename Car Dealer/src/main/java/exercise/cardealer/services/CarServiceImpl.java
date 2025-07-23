package exercise.cardealer.services;

import exercise.cardealer.dtos.CarExtendedDto;
import exercise.cardealer.dtos.CarInputDto;
import exercise.cardealer.dtos.CarRelationsDto;
import exercise.cardealer.entities.Car;
import exercise.cardealer.entities.Part;
import exercise.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository repository;
    private final ModelMapper modelMapper;
    private final PartService partService;


    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, PartService partService) {
        this.repository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    public void create(CarInputDto inputDto, CarRelationsDto relationsDto) {
        Car car = modelMapper.map(inputDto, Car.class);

        Set<Part> parts = new HashSet<>();
        for (Long partId : relationsDto.getPartIds()) {
            Part part = partService.gerReferenceById(partId);

            parts.add(part);
        }
        car.setParts(parts);

        repository.save(car);
    }

    @Override
    public List<CarExtendedDto> getExtended() {
        List<Car> cars = repository.findAllWithParts();

        List<CarExtendedDto> result = new ArrayList<>();
        for (Car car : cars) {
            CarExtendedDto carExtendedDto = modelMapper.map(car, CarExtendedDto.class);
            result.add(carExtendedDto);
        }

        return result;
    }
}
