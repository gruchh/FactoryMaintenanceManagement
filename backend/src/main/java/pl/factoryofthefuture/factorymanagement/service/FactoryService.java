package pl.factoryofthefuture.factorymanagement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Factory;
import pl.factoryofthefuture.factorymanagement.entity.dto.FactoryDto;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.FactoryDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.FactoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FactoryService {

    private final FactoryRepository factoryRepository;
    private final FactoryDtoMapper factoryDtoMapper;

    public List<FactoryDto> getAllFactoriesDtos() {
        List<Factory> allFactories = factoryRepository.findAll();
        return factoryDtoMapper.mapFactoriesToDtos(allFactories);
    }

    public FactoryDto getFactoryById(Long id) {
        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return factoryDtoMapper.mapFactoryToDto(factory);
    }

    public FactoryDto saveFactory(FactoryDto factoryDto) {
        Factory factory = factoryDtoMapper.mapFactoryToEntity(factoryDto);
        Factory savedFactory = factoryRepository.save(factory);
        return factoryDtoMapper.mapFactoryToDto(savedFactory);
    }

    @Transactional
    public FactoryDto updateFactory(FactoryDto factoryDto) {
        Factory updatedFactory = factoryRepository.findById(factoryDto.getId())
                .orElseThrow(() -> new NotFoundException(factoryDto.getId()));
        updatedFactory.setName(updatedFactory.getName());
        updatedFactory.setDescription(updatedFactory.getDescription());
        updatedFactory.setCity(updatedFactory.getCity());
        updatedFactory.setCreationDate(updatedFactory.getCreationDate());
        updatedFactory.setStatus(updatedFactory.getStatus());
        updatedFactory.setScopeOfActivity(updatedFactory.getScopeOfActivity());
        return factoryDtoMapper.mapFactoryToDto(updatedFactory);
    }

    @Transactional
    public void deleteFactory(Long id) {
        if (!factoryRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        factoryRepository.deleteById(id);
    }
}