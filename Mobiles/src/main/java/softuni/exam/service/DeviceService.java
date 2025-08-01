package softuni.exam.service;


import jakarta.xml.bind.JAXBException;
import softuni.exam.models.entity.Device;


import java.io.IOException;
import java.util.List;

// TODO: Implement all methods

public interface DeviceService {

    boolean areImported();

    String readDevicesFromFile() throws IOException;

	String importDevices() throws IOException, JAXBException;

    String exportDevices();

}
