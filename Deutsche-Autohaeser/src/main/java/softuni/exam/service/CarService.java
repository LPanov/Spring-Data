package softuni.exam.service;


import softuni.exam.models.entity.enums.Car;

import java.io.IOException;

public interface CarService {

    boolean areImported();

    String readCarsFileContent() throws IOException;
	
	String importCars() throws IOException;

    String exportCars();

    Car getReferenceById(Long carId);
}
