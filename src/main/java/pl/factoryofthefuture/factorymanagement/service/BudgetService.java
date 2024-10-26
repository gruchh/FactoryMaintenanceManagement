package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.repository.BudgetRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public List<Budget> getBudgetList() {
        return budgetRepository.findAll();
    }

    public Budget getBudget(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }

    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Budget budget) {
        Budget updatedBudget = budgetRepository.findById(budget.getId())
                .orElseThrow(() -> new NoSuchElementException("Budget not found with id: " + budget.getId()));
        updatedBudget.setId(budget.getId());
        updatedBudget.setMonth( budget.getMonth());
        updatedBudget.setYear(budget.getYear());
        updatedBudget.setBudgetAmount(budget.getBudgetAmount());


        return budgetRepository.save(updatedBudget);
    }

    public void deleteById(long id) {
        budgetRepository.deleteById(id);
    }
}
