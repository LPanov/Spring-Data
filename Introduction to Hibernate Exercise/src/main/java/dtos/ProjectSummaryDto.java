package dtos;

import java.time.LocalDateTime;
import java.util.Date;

public class ProjectSummaryDto {
    private final String projectName;
    private final String projectDescription;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public ProjectSummaryDto(String projectName, String projectDescription, LocalDateTime startDate, LocalDateTime endDate) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
