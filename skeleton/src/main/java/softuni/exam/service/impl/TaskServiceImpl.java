package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarInputDto;
import softuni.exam.models.dto.TaskInputDto;
import softuni.exam.models.dto.TasksImportDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.models.enums.Type;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.MechanicService;
import softuni.exam.service.PartService;
import softuni.exam.service.TaskService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XmlParser xmlParser;
    private final CarService carService;
    private final MechanicService mechanicService;
    private final PartService partService;


    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, CarService carService, MechanicService mechanicService, PartService partService) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.mechanicService = mechanicService;
        this.partService = partService;
    }

    @Override
    public boolean areImported() {
        return taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/xml/tasks.xml");
        return Files.readString(path);
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        TasksImportDto importDto = xmlParser.fromXml(readTasksFileContent(), TasksImportDto.class);

        StringBuilder result = new StringBuilder();
        for (TaskInputDto inputDto : importDto.getTasks()) {
            Task task = create(inputDto);
            if (task == null) {
                result.append("Invalid task\n");
            }
            else {
                result.append(String.format("Successfully imported task %.2f\n", task.getPrice()));
            }
        }

        return result.toString();
    }

    private Task create(TaskInputDto inputDto) {
        if (!validator.isValid(inputDto)) {
            return null;
        }

        try {
            Task task = modelMapper.map(inputDto, Task.class);

            if (inputDto.getCar() != null) {
                Car car = carService.getReferenceById(inputDto.getCar().getId());
                task.setCar(car);
            }

            if (inputDto.getPart() != null) {
                Part part = partService.getReferenceById(inputDto.getPart().getId());
                task.setPart(part);
            }

            if (inputDto.getMechanic() != null) {
                Mechanic mechanic = mechanicService.getReferenceByFirstName(inputDto.getMechanic().getFirstName());
                task.setMechanic(mechanic);
            } else {
                return null;
            }

            taskRepository.save(task);

            return task;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        List<Task> tasks = getAllCoupeCars();

        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(String.format("Car %s %s with %dkm%n", task.getCar().getCarMake(), task.getCar().getCarModel(), task.getCar().getKilometers()));
            result.append(String.format("-Mechanic: %s %s - task №%d:¬¬%n", task.getMechanic().getFirstName(), task.getMechanic().getFirstName(), task.getId()));
            result.append(String.format("--Engine: %f%n", task.getCar().getEngine()));
            result.append(String.format("---Price: %.2f$%n", task.getPrice()));
        }

        return result.toString();
    }

    private List<Task> getAllCoupeCars() {
        return taskRepository.getAllCarsByType(Type.coupe);
    }
}
