package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.CarModelDto;
import pl.factoryofthefuture.factorymanagement.service.CarModelService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/car-models")
@RequiredArgsConstructor
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping
    public ResponseEntity<List<CarModelDto>> getAllCarModels() {
        try {
            List<CarModelDto> carModelDtos = carModelService.getAllCarModelsDtos();
            return ResponseEntity.ok(carModelDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModelDto> getCarModel(@PathVariable long id) {
        try {
            CarModelDto carModelDto = carModelService.getCarModelDtoById(id);
            return ResponseEntity.ok(carModelDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<CarModelDto> saveCarModel(@RequestBody CarModelDto carModelDto) {
        try {
            CarModelDto savedCarModelDto = carModelService.saveCarModel(carModelDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCarModelDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<CarModelDto> updateCarModel(@RequestBody CarModelDto carModelDto) {
        try {
            CarModelDto editedCarModelDto = carModelService.updateCarModel(carModelDto);
            return ResponseEntity.ok(editedCarModelDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable long id) {
        try {
            carModelService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}