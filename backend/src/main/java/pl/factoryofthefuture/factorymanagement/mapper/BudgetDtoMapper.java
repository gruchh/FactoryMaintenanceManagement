package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class BudgetDtoMapper {

    public List<BudgetDto> mapBudgetsToDtos(List<Budget> machines) {
        return machines.stream()
                .map(this::mapBudgetToDto)
                .collect(Collectors.toList());
    }

    public BudgetDto mapBudgetToDto(Budget budget) {
        return BudgetDto.builder()
                .month(budget.getMonth())
                .year(budget.getYear())
                .budgetAmount(budget.getBudgetAmount())
                .build();
    }

    public Budget mapBudgetDtoToEntity(BudgetDto budgetDto) {
        return Budget.builder()
                .id(budgetDto.getId())
                .month(budgetDto.getMonth())
                .year(budgetDto.getYear())
                .budgetAmount(budgetDto.getBudgetAmount())
                .build();
    }
}