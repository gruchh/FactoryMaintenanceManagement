package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.entity.dto.BudgetDto;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.BudgetDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.BudgetRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetDtoMapper budgetDtoMapper;

    public List<BudgetDto> getAllBudgetsDtos() {
        List<Budget> allBudgets = budgetRepository.findAll();
        return budgetDtoMapper.mapBudgetsToDtos(allBudgets);
    }

    public BudgetDto getBudgetDtoById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return budgetDtoMapper.mapBudgetToDto(budget);
    }

    public BudgetDto saveBudget(BudgetDto budgetDto) {
        Budget budget = budgetDtoMapper.mapBudgetDtoToEntity(budgetDto);
        Budget savedBudget = budgetRepository.save(budget);
        return budgetDtoMapper.mapBudgetToDto(savedBudget);
    }

    @Transactional
    public BudgetDto updateBudget(BudgetDto budgetDto) {
        Budget updatedBudget = budgetRepository.findById(budgetDto.getId())
                .orElseThrow(() -> new NotFoundException(budgetDto.getId()));
        updatedBudget.setMonth(budgetDto.getMonth());
        updatedBudget.setYear(budgetDto.getYear());
        updatedBudget.setBudgetAmount(budgetDto.getBudgetAmount());
        Budget savedBudget = budgetRepository.save(updatedBudget);
        return budgetDtoMapper.mapBudgetToDto(savedBudget);
    }

    @Transactional
    public void deleteById(long id) {
        if (!budgetRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        budgetRepository.deleteById(id);
    }
}