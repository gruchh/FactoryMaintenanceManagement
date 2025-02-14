package pl.factoryofthefuture.factorymanagement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Factory;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.repository.CarModelRepository;
import pl.factoryofthefuture.factorymanagement.repository.FactoryRepository;

import java.util.List;

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
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Factory saveFactory(Factory factory) {
        return factoryRepository.save(factory);
    }

    @Transactional
    public Factory updateFactory(Factory updatedFactory) {
        Factory existingFactory = factoryRepository.findById(updatedFactory.getId())
                .orElseThrow(() -> new NotFoundException(updatedFactory.getId()));

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
            throw new NotFoundException(id);
        }
        factoryRepository.deleteById(id);
    }
}