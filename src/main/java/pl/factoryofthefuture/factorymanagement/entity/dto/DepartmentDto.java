package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DepartmentDto {

    private long id;
    private String fullName;
    private String shortCut;
    private LocalDate creationDate;
}
