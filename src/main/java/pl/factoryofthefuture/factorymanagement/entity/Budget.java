package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "BUDGET")
@NoArgsConstructor
@Getter
@Setter
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "BUDGET_MONTH")
    private int month;
    @Column(name = "BUDGET_YEAR")
    private int year;
    @Column(name = "BUDGET_AMOUNT_PLN", precision = 10, scale = 2)
    private BigDecimal budgetAmount;

}
