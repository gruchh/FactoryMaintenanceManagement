package pl.factoryofthefuture.factorymanagement.mapper;

import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;

import java.util.List;
import java.util.stream.Collectors;


public class BudgetDtoMapper {

    public static List<BudgetDto> mapToBudgetDtos(List<Budget> machines) {
        return machines.stream()
                .map(BudgetDtoMapper::mapToBudgetDto)
                .collect(Collectors.toList());
    }

    public static BudgetDto mapToBudgetDto(Budget budget) {
        return BudgetDto.builder()
                .month(budget.getMonth())
                .year(budget.getYear())
                .budgetAmount(budget.getBudgetAmount())
                .build();
    }

    public static Budget mapDtoToBudget(BudgetDto budgetDto) {
        return  Budget.builder()
                .id(budgetDto.getId())
                .month(budgetDto.getMonth())
                .year(budgetDto.getYear())
                .budgetAmount(budgetDto.getBudgetAmount())
                .build();
    }
}
