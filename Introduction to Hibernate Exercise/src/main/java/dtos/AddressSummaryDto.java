package dtos;

public class AddressSummaryDto {
    private final String text;
    private final String town;
    private final Integer employees;

    public AddressSummaryDto(String text, String town, Integer employees) {
        this.text = text;
        this.town = town;
        this.employees = employees;
    }

    public String getText() {
        return text;
    }

    public String getTown() {
        return town;
    }

    public Integer getEmployees() {
        return employees;
    }
}
