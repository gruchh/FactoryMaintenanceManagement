package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;
import pl.factoryofthefuture.factorymanagement.repository.EmployeeRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;
    private final EmployeeRepository employeeRepository;
    private final int PAGE_SIZE = 5;

    public List<Breakdown> getBreakdowns() {
        return breakdownRepository.findAll();
    }

    public List<Breakdown> findAllBreakdowns(int pageNumber) {
        return breakdownRepository.findAllBreakdowns(PageRequest.of(pageNumber, PAGE_SIZE));
    }

    public Breakdown getBreakdown(Long id) {
        return breakdownRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }

    public List<Breakdown> getBreakdownsWithEmployees(int pageNumber) {
        List<Breakdown> allBreakdowns = breakdownRepository.findAllBreakdowns(PageRequest.of(pageNumber, PAGE_SIZE));
        List<Long> ids = allBreakdowns.stream().map(Breakdown::getId).toList();
        List<Employee> employees = employeeRepository.findAllByIdIn(ids);
        allBreakdowns.forEach(breakdown -> breakdown.setEmployeeSet(extractExployees(employees, breakdown.getId())));

        return allBreakdowns;
    }

    private Set<Employee> extractExployees(List<Employee> employees, long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toSet());
    }

}
