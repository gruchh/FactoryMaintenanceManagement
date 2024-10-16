package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;
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
}
