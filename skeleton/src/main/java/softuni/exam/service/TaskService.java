package softuni.exam.service;

import jakarta.xml.bind.JAXBException;
import softuni.exam.models.entity.Task;

import java.io.IOException;
import java.util.List;

// TODO: Implement all methods
public interface TaskService {

    boolean areImported();

    String readTasksFileContent() throws IOException;

    String importTasks() throws IOException, JAXBException;

    String getCoupeCarTasksOrderByPrice();

}
