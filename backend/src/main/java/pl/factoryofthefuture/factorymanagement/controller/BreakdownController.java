package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper.*;

@RestController
@RequestMapping("/breakdowns")
@RequiredArgsConstructor
public class BreakdownController {

    private final BreakdownService breakdownService;

    @GetMapping
    public List<BreakdownDto> getBreakdowns() {
        return mapToBreakdownDtos(breakdownService.getBreakdowns());
    }

    @GetMapping("/page")
    public List<BreakdownDto> getPaginatedBreakdowns(@RequestParam(required = false, defaultValue = "1") Integer value,
                                                     @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        int pageNumber = (value != null && value >= 1) ? value - 1 : 0;
        sortDirection = sortDirection != null ? sortDirection : Sort.Direction.ASC;
        return mapToBreakdownDtos(breakdownService.getPaginatedBreakdowns(pageNumber, sortDirection));
    }

    @GetMapping("/{id}")
    public BreakdownDto getBreakdownDto(@PathVariable long id) {
        return mapToBreakdownDto(breakdownService.getBreakdown(id));
    }

    @PostMapping()
    public ResponseEntity<BreakdownDto> saveBreakdown(@RequestBody BreakdownDto breakdownDto) {
        Breakdown savedBreakdown = breakdownService.saveBreakdown(mapDtoToBreakdown(breakdownDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToBreakdownDto(savedBreakdown));
    }

    @PutMapping()
    public ResponseEntity<BreakdownDto> updateBreakdown(@RequestBody BreakdownDto breakdownDto) {
        Breakdown editedBreakdown = breakdownService.updateBreakdown(mapDtoToBreakdown(breakdownDto));
        return ResponseEntity.status(HttpStatus.OK).body(mapToBreakdownDto(editedBreakdown));
    }

    @DeleteMapping("/{id}")
    public void deleteBreakdown(@PathVariable long id) {
        breakdownService.deleteById(id);
    }

}