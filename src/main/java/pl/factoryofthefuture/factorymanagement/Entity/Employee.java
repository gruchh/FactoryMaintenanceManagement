package pl.factoryofthefuture.factorymanagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.factoryofthefuture.factorymanagement.Enums.JobPositionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity (name= "EMPLOYEES")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    JobPositionType jobPosition;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private int shift;
    private String contactNumber;
    private String email;
    private BigDecimal salary;
    private int performanceRating;
    private LocalDate assesmentDate;

}
