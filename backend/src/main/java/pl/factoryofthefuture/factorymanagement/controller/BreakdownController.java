package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper.*;

@RestController
@RequestMapping("/breakdowns")
@RequiredArgsConstructor
public class BreakdownController {

    private final BreakdownService breakdownService;
    private final BreakdownDtoMapper breakdownDtoMapper;

    @GetMapping
    public List<BreakdownDto> getBreakdowns() {
        return breakdownDtoMapper.mapBreakdownsToDtos(breakdownService.getBreakdowns());
    }

    @GetMapping("/page")
    public List<BreakdownDto> getPaginatedBreakdowns(@RequestParam(required = false, defaultValue = "1") Integer value,
                                                     @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        int pageNumber = (value != null && value >= 1) ? value - 1 : 0;
        sortDirection = sortDirection != null ? sortDirection : Sort.Direction.ASC;
        return breakdownDtoMapper.mapBreakdownsToDtos(breakdownService.getPaginatedBreakdowns(pageNumber, sortDirection));
    }

    @GetMapping("/{id}")
    public BreakdownDto getBreakdownDto(@PathVariable long id) {
        return breakdownDtoMapper.mapBreakdownToDtos(breakdownService.getBreakdown(id));
    }

    @PostMapping()
    public ResponseEntity<BreakdownDto> saveBreakdown(@RequestBody BreakdownDto breakdownDto) {
        Breakdown savedBreakdown = breakdownService.saveBreakdown(breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(breakdownDtoMapper.mapBreakdownToDtos(savedBreakdown));
    }

    @PutMapping()
    public ResponseEntity<BreakdownDto> updateBreakdown(@RequestBody BreakdownDto breakdownDto) {
        Breakdown editedBreakdown = breakdownService.updateBreakdown(breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto));
        return ResponseEntity.status(HttpStatus.OK).body(breakdownDtoMapper.mapBreakdownToDtos(editedBreakdown));
    }

    @DeleteMapping("/{id}")
    public void deleteBreakdown(@PathVariable long id) {
        breakdownService.deleteById(id);
    }

}