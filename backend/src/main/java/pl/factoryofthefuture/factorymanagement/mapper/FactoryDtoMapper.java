package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Factory;
import pl.factoryofthefuture.factorymanagement.entity.dto.FactoryDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class FactoryDtoMapper {

    public FactoryDto mapFactoryToDto(Factory factory) {
        return FactoryDto.builder()
                .id(factory.getId())
                .name(factory.getName())
                .description(factory.getDescription())
                .city(factory.getCity())
                .creationDate(factory.getCreationDate())
                .status(factory.getStatus())
                .scopeOfActivity(factory.getScopeOfActivity())
                .build();
    }

    public List<FactoryDto> mapFactoriesToDtos(List<Factory> factoryList) {
        return factoryList.stream()
                .map(this::mapFactoryToDto)
                .collect(Collectors.toList());
    }

    public Factory mapFactoryToEntity(FactoryDto factoryDto) {
        return Factory.builder()
                .id(factoryDto.getId())
                .name(factoryDto.getName())
                .description(factoryDto.getDescription())
                .city(factoryDto.getCity())
                .creationDate(factoryDto.getCreationDate())
                .status(factoryDto.getStatus())
                .scopeOfActivity(factoryDto.getScopeOfActivity())
                .build();
    }
}