package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.repository.BudgetRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public List<Budget> getBudgetList() {
        return budgetRepository.findAll();
    }

    public Budget getBudget(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Budget budget) {
        Budget updatedBudget = budgetRepository.findById(budget.getId())
                .orElseThrow(() -> new NotFoundException(budget.getId()));
        updatedBudget.setId(budget.getId());
        updatedBudget.setMonth(budget.getMonth());
        updatedBudget.setYear(budget.getYear());
        updatedBudget.setBudgetAmount(budget.getBudgetAmount());
        return budgetRepository.save(updatedBudget);
    }

    @Transactional
    public void deleteById(long id) {
        if (!budgetRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        budgetRepository.deleteById(id);
    }
}