package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BudgetDto {
    private long id;
    private int month;
    private int year;
    private BigDecimal budgetAmount;
}
