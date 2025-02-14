package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.projections.BreakdownWithShortCutProjection;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;
    private final int PAGE_SIZE = 5;

    public List<Breakdown> getBreakdowns() {
        return breakdownRepository.findAll();
    }

    public List<Breakdown> getPaginatedBreakdowns(int pageNumber, Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortDirection, "id"));
        return breakdownRepository.findAllBreakdowns(pageable);
    }

    public Breakdown getBreakdown(Long id) {
        return breakdownRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such breakdown with id: " + id));
    }

    public Set<Breakdown> findBreakdownsById(Set<Long> breakdownIds) {
        return new HashSet<>(breakdownRepository.findAllById(breakdownIds));
    }

    public Breakdown saveBreakdown(Breakdown breakdown) {
        return breakdownRepository.save(breakdown);
    }

    @Transactional
    public Breakdown updateBreakdown(Breakdown breakdown) {
        Breakdown updatedBreakdown = breakdownRepository.findById(breakdown.getId())
                .orElseThrow(() -> new NoSuchElementException("Breakdown not found with id: " + breakdown.getId()));
        updatedBreakdown.setEventDescription(breakdown.getEventDescription());
        updatedBreakdown.setStartDate(breakdown.getStartDate());
        updatedBreakdown.setEndDate(breakdown.getEndDate());
        updatedBreakdown.setSeverity(breakdown.getSeverity());
        updatedBreakdown.setCause(breakdown.getCause());
        updatedBreakdown.setUsedParts(breakdown.getUsedParts());
        updatedBreakdown.setComments(breakdown.getComments());
        updatedBreakdown.setMachine(breakdown.getMachine());
        updatedBreakdown.setEmployeeSet(breakdown.getEmployeeSet());
        return breakdownRepository.save(updatedBreakdown);
    }

    public void deleteById(long id) {
        breakdownRepository.deleteById(id);
    }

    public List<BreakdownWithShortCutProjection> getAllBreakdownsWitShortCut() {
        return breakdownRepository.findAllBreakdownsWithShortCut();
    }
}
