package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;
import pl.factoryofthefuture.factorymanagement.service.BudgetService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.BudgetDtoMapper.*;

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

    @PostMapping()
    public ResponseEntity<BudgetDto> saveBudget(@RequestBody BudgetDto budgetDto) {
        Budget savedBudget = budgetService.saveBudget(mapDtoToBudget(budgetDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToBudgetDto(savedBudget));
    }
}
