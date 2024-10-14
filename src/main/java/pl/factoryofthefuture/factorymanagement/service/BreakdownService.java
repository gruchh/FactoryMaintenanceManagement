package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;

    public List<Breakdown> getBreakdowns() {
        return breakdownRepository.findAll();
    }

    public Breakdown getBreakdown(Long id) {
        return breakdownRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }


}
