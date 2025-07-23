package exercise.cardealer.dtos;

import lombok.Getter;

import java.util.List;
import java.util.Set;

public class CarRelationsDto {
    @Getter
    private final Set<Long> partIds;

    public CarRelationsDto(Set<Long> partIds) {
        this.partIds = partIds;
    }
}
