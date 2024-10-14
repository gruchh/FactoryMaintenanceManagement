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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "EVENT_DESCRIPTIOJ")
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
    @OneToMany
    private Set<Employee> employeeSet;
}
