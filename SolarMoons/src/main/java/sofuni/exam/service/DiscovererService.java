package sofuni.exam.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sofuni.exam.models.entity.Discoverer;

import java.io.IOException;

@Validated
public interface DiscovererService {

    boolean areImported();

    String readDiscovererFileContent() throws IOException;

    String importDiscoverers() throws IOException;

    Discoverer getReferenceById(Long id);
}
