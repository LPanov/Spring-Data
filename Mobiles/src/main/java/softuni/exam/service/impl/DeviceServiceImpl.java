package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceInputDto;
import softuni.exam.models.dto.DevicesImportDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.DeviceType;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XmlParser xmlParser;
    private final DeviceRepository deviceRepository;
    private final SaleService saleService;


    public DeviceServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, DeviceRepository deviceRepository, SaleService saleService) {
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.xmlParser = xmlParser;
        this.deviceRepository = deviceRepository;
        this.saleService = saleService;
    }

    @Override
    public boolean areImported() {
        return deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        Path path = Path.of("src/main/resources/files/xml/devices.xml");
        return Files.readString(path);
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        DevicesImportDto importDto = xmlParser.fromXml(readDevicesFromFile(), DevicesImportDto.class);

        StringBuilder result = new StringBuilder();
        for (DeviceInputDto inputDto : importDto.getDevices()) {
            Device device = create(inputDto);
            if (device == null) {
                result.append("Invalid device\n");
            }
            else {
                result.append(String.format("Successfully imported device of type %s with brand %s%n", device.getDeviceType(), device.getBrand()));
            }
        }

        return result.toString();
    }

    @Override
    public String exportDevices() {
        List<Device> devices = findAllCheapSmartphones();
        StringBuilder result = new StringBuilder();

        for (Device device : devices) {
            result.append(String.format("Device brand: %s%n", device.getBrand()));
            result.append(String.format("   *Model: %s%n", device.getModel()));
            result.append(String.format("   **Storage: %d%n", device.getStorage()));
            result.append(String.format("   ***Price: %.2f%n", device.getPrice()).replace(",", "."));
        }

        return result.toString();
    }

    private Device create(DeviceInputDto inputDto) {
        if (!validator.isValid(inputDto)) {
            return null;
        }

        try {
            Device device = modelMapper.map(inputDto, Device.class);

            Long saleId = inputDto.getSale();
            if (saleId != null) {
                Sale sale = saleService.getReferencedById(saleId);

                device.setSale(sale);
            }

            deviceRepository.save(device);

            return device;
        } catch (Exception e) {
            return null;
        }
    }

    private List<Device> findAllCheapSmartphones() {
        return deviceRepository.findByTypePriceAndStorage(DeviceType.SMART_PHONE, 1000.0, 128);
    }
}
