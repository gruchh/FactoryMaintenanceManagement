package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;
import pl.factoryofthefuture.factorymanagement.service.BudgetService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.BudgetDtoMapper.mapToBudgetDto;
import static pl.factoryofthefuture.factorymanagement.mapper.BudgetDtoMapper.mapToBudgetDtos;

@RestController()
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping()
    public List<BudgetDto> getBudgetList() {
        return mapToBudgetDtos(budgetService.getBudgetList());
    }

    @GetMapping("/{id}")
    public BudgetDto getBudget(@PathVariable Long id) {
        return mapToBudgetDto(budgetService.getBudget(id));
    }


}
