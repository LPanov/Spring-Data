package exercise.cardealer;

import exercise.cardealer.dtos.*;
import exercise.cardealer.entities.Supplier;
import exercise.cardealer.services.CarService;
import exercise.cardealer.services.PartService;
import exercise.cardealer.services.SupplierService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Runner implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;


    public Runner(SupplierService supplierService, PartService partService, CarService carService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(
                                SuppliersReportsExportDto.class,
                                SuppliersImportDto.class,
                                PartsImportDto.class,
                                CarsImportDto.class,
                                CarsExtendedExportDto.class
                                );

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //List<SupplierDto> suppliers = seedSuppliers(unmarshaller);
        //List<PartDto> parts = seedParts(suppliers, unmarshaller);

        //seedCars(unmarshaller, parts);

        System.out.println("Seeding finished");

        //Query 3
        //localSuppliers(marshaller);

        //Query 4
        //carsWithParts(marshaller);
    }

    private void carsWithParts(Marshaller marshaller) throws JAXBException {
        List<CarExtendedDto> cars = carService.getExtended();
        CarsExtendedExportDto carsExtendedExportDto = new CarsExtendedExportDto(cars);
        marshaller.marshal(carsExtendedExportDto, System.out);
    }

    private void seedCars(Unmarshaller unmarshaller, List<PartDto> parts) throws IOException, JAXBException {
        CarsImportDto importDto;
        try (InputStream inputStream = readResourcedFileAsStream("xmls/cars.xml")) {
            importDto  = (CarsImportDto) unmarshaller.unmarshal(inputStream);
        }

        for (CarInputDto carDto : importDto.getCars()) {
            Set<Long> partIds = new HashSet<>();
            int randomPartCount = ThreadLocalRandom.current().nextInt(10, 21);

            for (int i = 0; i < randomPartCount; i++) {
                int randomPartIndex = ThreadLocalRandom.current().nextInt(parts.size());
                PartDto randomPart = parts.get(randomPartIndex);
                partIds.add(randomPart.getId());
            }
            CarRelationsDto relationsDto = new CarRelationsDto(partIds);

            carService.create(carDto, relationsDto);
        }
    }

    private void localSuppliers(Marshaller marshaller) throws JAXBException {
        List<SupplierReportDto> supplierReportDtos = supplierService.generateReport(false);
        SuppliersReportsExportDto exportDto = new SuppliersReportsExportDto();
        exportDto.setSuppliers(supplierReportDtos);

        marshaller.marshal(exportDto, System.out);
    }

    private List<SupplierDto> seedSuppliers(Unmarshaller unmarshaller) throws JAXBException, IOException {

        SuppliersImportDto importDto;
        try (InputStream inputStream = readResourcedFileAsStream("xmls/suppliers.xml")) {
            importDto  = (SuppliersImportDto) unmarshaller.unmarshal(inputStream);
        }

        List<SupplierDto> result = new ArrayList<>();
        for (SupplierInputDto input : importDto.getSuppliers()) {
            SupplierDto createdSupplier = supplierService.create(input);
            result.add(createdSupplier);
        }

        return result;
    }

    private List<PartDto> seedParts(List<SupplierDto> suppliers, Unmarshaller unmarshaller) throws JAXBException, IOException {

        PartsImportDto importDto;
        try (InputStream inputStream = readResourcedFileAsStream("xmls/parts.xml")) {
            importDto  = (PartsImportDto) unmarshaller.unmarshal(inputStream);
        }

        List<PartDto> result = new ArrayList<>();
        for (PartInputDto input : importDto.getParts()) {
            int randomSupplierIndex = ThreadLocalRandom.current().nextInt(suppliers.size());
            SupplierDto randomSupplier = suppliers.get(randomSupplierIndex);

            PartDto createdPart = partService.create(input, randomSupplier);
            result.add(createdPart);
        }

        return result;
    }

    private InputStream readResourcedFileAsStream(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getInputStream();
    }
}
