package pl.factoryofthefuture.factorymanagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @OneToMany
    private Set<Employee> employeeSet;
}
