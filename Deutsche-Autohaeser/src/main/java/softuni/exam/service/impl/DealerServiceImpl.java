package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DealerInputDto;
import softuni.exam.models.dto.DealersImportDto;
import softuni.exam.models.dto.DealershipInputDto;
import softuni.exam.models.entity.enums.Car;
import softuni.exam.models.entity.enums.Dealer;
import softuni.exam.repository.DealerRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.DealerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class DealerServiceImpl implements DealerService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final DealerRepository dealerRepository;
    private final CarService carService;


    public DealerServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, DealerRepository dealerRepository, CarService carService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.dealerRepository = dealerRepository;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return dealerRepository.count() > 0;
    }

    @Override
    public String readDealersFromFile() throws IOException {
        Path path = Path.of("src/main/resources/files/xml/dealers.xml");
        return Files.readString(path);
    }

    @Override
    public String importDealers() throws IOException, JAXBException {
        DealersImportDto importDto = xmlParser.fromXml(readDealersFromFile(), DealersImportDto.class);

        StringBuilder result = new StringBuilder();
        for (DealerInputDto inputDto : importDto.getDealers()) {
            Dealer dealer = create(inputDto);
            if (dealer == null) {
                result.append("Invalid dealer\n");
            }
            else {
                result.append(String.format("Successfully imported dealer %s %s%n", dealer.getFirstName(), dealer.getLastName()));
            }
        }

        return result.toString();
    }

    private Dealer create(DealerInputDto inputDto) {
        if (!validationUtil.isValid(inputDto)) {
            return null;
        }

        try {
            Dealer createdDealer = modelMapper.map(inputDto, Dealer.class);

            List<Dealer> dealers = dealerRepository.findAll();

            for (Dealer dealer : dealers) {
                if (createdDealer.getFirstName().equals(dealer.getFirstName()) &&
                    createdDealer.getLastName().equals(dealer.getLastName())) {
                    return null;
                }
            }

            Long carId = inputDto.getOfferingCar();
            if (carId != null) {
                Car car = carService.getReferenceById(carId);

                if (car == null) {
                    return null;
                }
                createdDealer.setOfferingCar(car);
            }

            dealerRepository.save(createdDealer);

            return createdDealer;
        } catch (Exception e) {
            return null;
        }
    }
}
