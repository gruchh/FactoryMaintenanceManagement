package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Factory;
import pl.factoryofthefuture.factorymanagement.entity.dto.FactoryDto;
import pl.factoryofthefuture.factorymanagement.mapper.FactoryDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.FactoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/factories")
@RequiredArgsConstructor
public class FactoryController {

    private final FactoryService factoryService;
    private final FactoryDtoMapper factoryMapper;

    @GetMapping()
    public ResponseEntity<List<FactoryDto>> getAllFactories() {
        try {
            List<Factory> factories = factoryService.getAllFactories();
            List<FactoryDto> factoryDtos = factories.stream()
                    .map(factoryMapper::mapFactoryToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(factoryDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactoryDto> getFactoryById(@PathVariable long id) {
        try {
            Factory factory = factoryService.getFactory(id);
            return ResponseEntity.ok(factoryMapper.mapFactoryToDto(factory));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<FactoryDto> createFactory(@RequestBody FactoryDto factoryDto) {
        try {
            Factory factory = factoryMapper.mapFactoryToEntity(factoryDto);
            Factory createdFactory = factoryService.saveFactory(factory);
            FactoryDto createdFactoryDto = factoryMapper.mapFactoryToDto(createdFactory);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFactoryDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<FactoryDto> saveFactory(@RequestBody FactoryDto factoryDto) {
        try {
            Factory updatedFactory = factoryService.updateFactory(factoryMapper.mapFactoryToEntity(factoryDto));
            return ResponseEntity.ok(factoryMapper.mapFactoryToDto(updatedFactory));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactory(@PathVariable long id) {
        try {
            factoryService.deleteFactory(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}