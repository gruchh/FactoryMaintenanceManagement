package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.FactoryDto;
import pl.factoryofthefuture.factorymanagement.service.FactoryService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/factories")
@RequiredArgsConstructor
public class FactoryController {

    private final FactoryService factoryService;

    @GetMapping()
    public ResponseEntity<List<FactoryDto>> getAllFactories() {
        try {
            List<FactoryDto> allFactoriesDtos = factoryService.getAllFactoriesDtos();
            return ResponseEntity.ok(allFactoriesDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactoryDto> getFactoryById(@PathVariable long id) {
        try {
            FactoryDto factoryByIdDto = factoryService.getFactoryById(id);
            return ResponseEntity.ok(factoryByIdDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<FactoryDto> saveFactory(@RequestBody FactoryDto factoryDto) {
        try {
            FactoryDto savedFactoryDto = factoryService.saveFactory(factoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFactoryDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<FactoryDto> updateFactory(@RequestBody FactoryDto factoryDto) {
        try {
            FactoryDto updatedFactoryDto = factoryService.updateFactory(factoryDto);
            return ResponseEntity.ok(updatedFactoryDto);
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