package exercise09;

import dtos.ProjectSummaryDto;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class FindTheLatest10Projects {
    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<ProjectSummaryDto> query = em.createQuery("select p.name, p.description, p.startDate, p.endDate from Project p order by p.startDate desc", ProjectSummaryDto.class)
                .setMaxResults(10);
        List<ProjectSummaryDto> projects = query.getResultList().stream().sorted(Comparator.comparing(ProjectSummaryDto::getProjectName)).collect(Collectors.toList());

        StringJoiner joiner = new StringJoiner("\n");
        for (ProjectSummaryDto project : projects) {
            joiner.add(String.format("Project name: %s", project.getProjectName()));
            joiner.add(String.format("\tProject Description: %s", project.getProjectDescription()));
            joiner.add(String.format("\tProject Start Date: %s", project.getStartDate()));
            joiner.add(String.format("\tProject End Date: %s", project.getEndDate()));
        }

        System.out.println(joiner.toString());

    }
}
