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
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final BudgetDtoMapper budgetDtoMapper;

    @GetMapping()
    public ResponseEntity<List<BudgetDto>> getBudgetList() {
        List<BudgetDto> budgetDtos = budgetService.getBudgetList().stream()
                .map(budgetDtoMapper::mapBudgetToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(budgetDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDto> getBudget(@PathVariable long id) {
        Budget budget = budgetService.getBudget(id);
        if (budget != null) {
            return ResponseEntity.ok(budgetDtoMapper.mapBudgetToDto(budget));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<BudgetDto> saveBudget(@RequestBody BudgetDto budgetDto) {
        Budget savedBudget = budgetService.saveBudget(budgetDtoMapper.mapBudgetDtoToEntity(budgetDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDtoMapper.mapBudgetToDto(savedBudget));
    }

    @PutMapping()
    public ResponseEntity<BudgetDto> updateBudget(@RequestBody BudgetDto budgetDto) {
        Budget editedBudget = budgetService.updateBudget(budgetDtoMapper.mapBudgetDtoToEntity(budgetDto));
        return ResponseEntity.ok(budgetDtoMapper.mapBudgetToDto(editedBudget));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable long id) {
        budgetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
