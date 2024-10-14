package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.factoryofthefuture.factorymanagement.enums.JobPositionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity (name= "EMPLOYEES")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Enumerated(EnumType.STRING)
    JobPositionType jobPosition;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;
    @Column(name = "SHIFT")
    private int shift;
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "PERFORMANCE_RATING")
    private int performanceRating;
    @Column(name = "ASSESMENT_DATE")
    private LocalDate assesmentDate;

}
