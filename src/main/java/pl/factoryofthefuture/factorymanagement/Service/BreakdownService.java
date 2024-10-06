package pl.factoryofthefuture.factorymanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.Entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.Repository.BreakdownRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BreakdownService {

    @Autowired
    BreakdownRepository breakdownRepository;

    public List<Breakdown> getBreakdowns() {
        return breakdownRepository.findAll();
    }

    public Breakdown getBreakdown(Long id) {
        return breakdownRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }


}
