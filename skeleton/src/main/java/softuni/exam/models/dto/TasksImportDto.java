package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TasksImportDto {
    @XmlElement(name = "task")
    List<TaskInputDto> tasks;

    public List<TaskInputDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskInputDto> tasks) {
        this.tasks = tasks;
    }
}
