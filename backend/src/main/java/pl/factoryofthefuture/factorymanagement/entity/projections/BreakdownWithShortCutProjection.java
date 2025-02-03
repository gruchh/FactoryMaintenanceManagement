package pl.factoryofthefuture.factorymanagement.entity.projections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.factoryofthefuture.factorymanagement.enums.SeverityType;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class BreakdownWithShortCutProjection {
    private long id;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private SeverityType severity;
    private String cause;
    private String usedParts;
    private String comments;
    private String shortCut;
}