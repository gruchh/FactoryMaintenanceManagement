package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.CarModel;
import pl.factoryofthefuture.factorymanagement.entity.dto.CarModelDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class CarModelDtoMapper {

    public CarModelDto mapCarModelToDto(CarModel carModel) {
        return CarModelDto.builder()
                .id(carModel.getId())
                .modelName(carModel.getModelName())
                .modelType(carModel.getModelType())
                .build();
    }

    public List<CarModelDto> mapCarModelsToDtos(List<CarModel> carModels) {
        return carModels.stream()
                .map(this::mapCarModelToDto)
                .collect(Collectors.toList());
    }

    public CarModel mapCarModelDtoToEntity(CarModelDto carModelDto) {
        return CarModel.builder()
                .id(carModelDto.getId())
                .modelName(carModelDto.getModelName())
                .modelType(carModelDto.getModelType())
                .build();
    }
}