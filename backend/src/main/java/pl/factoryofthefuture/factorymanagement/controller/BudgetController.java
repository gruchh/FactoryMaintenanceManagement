package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;
import pl.factoryofthefuture.factorymanagement.service.BudgetService;

import java.util.List;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping()
    public ResponseEntity<List<BudgetDto>> getAllBudget() {
        List<BudgetDto> budgetDtos = budgetService.getAllBudgetsDtos();
        return ResponseEntity.ok(budgetDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDto> getBudget(@PathVariable long id) {
        BudgetDto budgetDto = budgetService.getBudgetDtoById(id);
        if (budgetDto != null) {
            return ResponseEntity.ok(budgetDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<BudgetDto> saveBudget(@RequestBody BudgetDto budgetDto) {
        BudgetDto savedBudgetDto = budgetService.saveBudget(budgetDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBudgetDto);
    }

    @PutMapping()
    public ResponseEntity<BudgetDto> updateBudget(@RequestBody BudgetDto budgetDto) {
        BudgetDto updatedBudgetDto = budgetService.updateBudget(budgetDto);
        return ResponseEntity.ok(updatedBudgetDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable long id) {
        budgetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}