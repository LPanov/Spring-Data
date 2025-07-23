package exercise.cardealer.services;

import exercise.cardealer.dtos.CarExtendedDto;
import exercise.cardealer.dtos.CarInputDto;
import exercise.cardealer.dtos.CarRelationsDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CarService {
    void create(@Valid CarInputDto inputDto,@Valid CarRelationsDto relationsDto);

    List<CarExtendedDto> getExtended();
}
