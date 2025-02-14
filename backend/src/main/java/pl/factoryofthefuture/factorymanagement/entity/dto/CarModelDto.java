package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarModelDto {
    private Long id;
    private String modelName;
    private String modelType;
}