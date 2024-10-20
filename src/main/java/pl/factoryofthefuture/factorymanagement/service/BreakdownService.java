package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;

import java.util.HashSet;
import java.util.List;
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
        return breakdownRepository.findById(id).orElse(null);
    }

    public Breakdown saveBreakdown(Breakdown breakdown) {
        return breakdownRepository.save(breakdown);
    }

    public Set<Breakdown> findBreakdownsById(Set<Long> breakdownIds) {
        return new HashSet<>(breakdownRepository.findAllById(breakdownIds));
    }
}
