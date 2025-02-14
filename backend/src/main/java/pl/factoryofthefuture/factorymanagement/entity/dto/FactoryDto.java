package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;
import pl.factoryofthefuture.factorymanagement.enums.FactoryStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class FactoryDto {
    private Long id;
    private String name;
    private String description;
    private String city;
    private LocalDate creationDate;
    private FactoryStatus status;
    private String scopeOfActivity;
    private List<CarModelDto> models;
}