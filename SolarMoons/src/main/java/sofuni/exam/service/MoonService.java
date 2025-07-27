package sofuni.exam.service;

import jakarta.xml.bind.JAXBException;
import org.springframework.validation.annotation.Validated;
import sofuni.exam.models.entity.Moon;

import java.io.IOException;
import java.util.List;

@Validated
public interface MoonService {

    boolean areImported();

    String readMoonsFileContent() throws IOException;

    String importMoons() throws IOException, JAXBException;

    String exportMoons();


}
