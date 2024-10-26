package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;
import pl.factoryofthefuture.factorymanagement.enums.JobPositionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
public class EmployeeDto {
    private long id;
    private String name;
    private String surname;
    private JobPositionType jobPosition;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private int shift;
    private String contactNumber;
    private String email;
    private BigDecimal salary;
    private int performanceRating;
    private LocalDate assessmentDate;
    private Set<Long> breakdownIds;
}
