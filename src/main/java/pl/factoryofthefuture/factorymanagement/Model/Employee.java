package pl.factoryofthefuture.factorymanagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    EmployeeType employeeType;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private BigDecimal salary;

}
