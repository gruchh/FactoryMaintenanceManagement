package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;
import pl.factoryofthefuture.factorymanagement.mapper.BudgetDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.BudgetService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.BudgetDtoMapper.*;

@RestController()
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final BudgetDtoMapper budgetDtoMapper;

    @GetMapping()
    public List<BudgetDto> getBudgetList() {
        return budgetDtoMapper.mapBudgetsToDtos(budgetService.getBudgetList());
    }

    @GetMapping("/{id}")
    public BudgetDto getBudget(@PathVariable long id) {
        return budgetDtoMapper.mapBudgetToDto(budgetService.getBudget(id));
    }

    @PostMapping()
    public ResponseEntity<BudgetDto> saveBudget(@RequestBody BudgetDto budgetDto) {
        Budget savedBudget = budgetService.saveBudget(budgetDtoMapper.mapBudgetDtoToEntity(budgetDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDtoMapper.mapBudgetToDto(savedBudget));
    }

    @PutMapping()
    public ResponseEntity<BudgetDto> updateBudget(@RequestBody BudgetDto budgetDto) {
        Budget editedBudget = budgetService.updateBudget(budgetDtoMapper.mapBudgetDtoToEntity(budgetDto));
        return ResponseEntity.status(HttpStatus.OK).body(budgetDtoMapper.mapBudgetToDto(editedBudget));
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable long id) {
        budgetService.deleteById(id);
    }
}
