package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.factoryofthefuture.factorymanagement.enums.SeverityType;

import java.time.LocalDate;
import java.util.Set;

@Entity (name="BREAKDOWNS")
@NoArgsConstructor
@Getter
@Setter
public class Breakdown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "EVENT_DESCRIPTION")
    private String eventDescription;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "SEVERITY")
    private SeverityType severity;
    @Column(name = "CAUSE")
    private String cause;
    @Column(name = "USED_PARTS")
    private String usedParts;
    @Column(name = "COMMENTS")
    private String comments;
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "breakdown_employee",
            joinColumns = @JoinColumn(name = "breakdown_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employeeSet;
}
