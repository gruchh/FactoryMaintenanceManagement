package pl.factoryofthefuture.factorymanagement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Factory;
import pl.factoryofthefuture.factorymanagement.repository.CarModelRepository;
import pl.factoryofthefuture.factorymanagement.repository.FactoryRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class FactoryService {

    private final FactoryRepository factoryRepository;
    private final CarModelRepository carModelRepository;

    public List<Factory> getAllFactories() {
        return factoryRepository.findAll();
    }

    public Factory getFactory(Long id) {
        return factoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Factory with ID " + id + " not found."));
    }

    public Factory saveFactory(Factory factory) {
        return factoryRepository.save(factory);
    }

    @Transactional
    public Factory updateFactory(Factory updatedFactory) {
        Factory existingFactory = factoryRepository.findById(updatedFactory.getId())
                .orElseThrow(() -> new NoSuchElementException("Factory ID " + updatedFactory.getId() + " hasn't been found."));

        existingFactory.setName(updatedFactory.getName());
        existingFactory.setDescription(updatedFactory.getDescription());
        existingFactory.setCity(updatedFactory.getCity());
        existingFactory.setCreationDate(updatedFactory.getCreationDate());
        existingFactory.setStatus(updatedFactory.getStatus());
        existingFactory.setScopeOfActivity(updatedFactory.getScopeOfActivity());
        return factoryRepository.save(existingFactory);
    }

    @Transactional
    public void deleteFactory(Long id) {
        if (!factoryRepository.existsById(id)) {
            throw new NoSuchElementException("Factory with ID " + id + " not found.");
        }
        factoryRepository.deleteById(id);
    }
}