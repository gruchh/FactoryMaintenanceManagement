package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;
import pl.factoryofthefuture.factorymanagement.enums.SeverityType;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
public class BreakdownDto {
    private long id;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private SeverityType severity;
    private String cause;
    private String usedParts;
    private String comments;
    private long machineId;
    private Set<Long> employeeIds;
}
