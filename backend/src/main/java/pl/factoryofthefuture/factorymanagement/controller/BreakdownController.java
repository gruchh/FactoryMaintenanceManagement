package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDetailsDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownListItemDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownWithShortCutDto;
import pl.factoryofthefuture.factorymanagement.entity.projections.BreakdownWithShortCutProjection;
import pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/breakdowns")
@RequiredArgsConstructor
public class BreakdownController {

    private final BreakdownService breakdownService;
    private final BreakdownDtoMapper breakdownDtoMapper;

    @GetMapping
    public ResponseEntity<List<BreakdownListItemDto>> getAllBreakdowns() {
        try {
            List<Breakdown> breakdowns = breakdownService.getBreakdowns();
            List<BreakdownListItemDto> breakdownDtos = breakdownDtoMapper.mapBreakdownsToListItemDtos(breakdowns);
            return ResponseEntity.ok(breakdownDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<BreakdownListItemDto>> getPaginatedBreakdowns(
            @RequestParam(required = false, defaultValue = "1") Integer value,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        try {
            int pageNumber = (value != null && value >= 1) ? value - 1 : 0;
            sortDirection = sortDirection != null ? sortDirection : Sort.Direction.ASC;
            List<Breakdown> breakdowns = breakdownService.getPaginatedBreakdowns(pageNumber, sortDirection);
            List<BreakdownListItemDto> breakdownDtos = breakdownDtoMapper.mapBreakdownsToListItemDtos(breakdowns);
            return ResponseEntity.ok(breakdownDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreakdownDetailsDto> getBreakdown(@PathVariable long id) {
        try {
            Breakdown breakdown = breakdownService.getBreakdown(id);
            BreakdownDetailsDto breakdownDto = breakdownDtoMapper.mapBreakdownToDetailsDto(breakdown);
            return ResponseEntity.ok(breakdownDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<BreakdownDto> saveBreakdown(@RequestBody BreakdownDto breakdownDto) {
        try {
            Breakdown savedBreakdown = breakdownService.saveBreakdown(breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(breakdownDtoMapper.mapBreakdownToDto(savedBreakdown));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping()
    public ResponseEntity<BreakdownDto> updateBreakdown(@RequestBody BreakdownDto breakdownDto) {
        try {
            Breakdown editedBreakdown = breakdownService.updateBreakdown(breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto));
            return ResponseEntity.ok(breakdownDtoMapper.mapBreakdownToDto(editedBreakdown)); // Corrected mapper to singular
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreakdown(@PathVariable long id) {
        try {
            breakdownService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/withShortcut")
    public ResponseEntity<List<BreakdownWithShortCutDto>> getBreakdownsWithShortcut() {
        List<BreakdownWithShortCutProjection> breakdownProjections = breakdownService.getAllBreakdownsWitShortCut();
        return ResponseEntity.ok(breakdownDtoMapper.mapBreakdownProjectionToDtos(breakdownProjections));
    }
    
}