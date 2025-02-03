package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;
import pl.factoryofthefuture.factorymanagement.enums.SeverityType;

import java.time.LocalDate;

@Getter
@Builder
public class BreakdownWithShortCutDto {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private SeverityType severity;
    private String shortCut;
}