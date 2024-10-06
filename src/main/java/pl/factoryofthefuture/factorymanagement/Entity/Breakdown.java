package pl.factoryofthefuture.factorymanagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.factoryofthefuture.factorymanagement.Enums.SeverityType;

import java.time.LocalDate;
import java.util.Set;

@Entity (name="BREAKDOWNS")
@NoArgsConstructor
@Getter
@Setter
public class Breakdown {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private SeverityType severity;
    private String cause;
    private String usedParts;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @OneToMany
    private Set<Employee> employeeSet;
}
