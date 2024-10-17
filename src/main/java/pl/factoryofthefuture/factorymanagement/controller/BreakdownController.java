package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper.mapToBreakdownDto;
import static pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper.mapToBreakdownDtos;

@RestController
@RequestMapping("/breakdowns")
@RequiredArgsConstructor
public class BreakdownController {

    private final BreakdownService breakdownService;

    @GetMapping()
    public List<BreakdownDto> getBreakdowns() {
        return mapToBreakdownDtos(breakdownService.getBreakdowns());
    }

    @GetMapping("/page")
    public List<BreakdownDto> getPaginatedBreakdowns(@RequestParam(required = false) int page) {
        int pageNumber = page >= 1 ? page - 1 : 0;
        return mapToBreakdownDtos(breakdownService.getPaginatedBreakdowns(pageNumber));
    }

    @GetMapping("/{id}")
    public BreakdownDto getBreakdownDto(@PathVariable Long id) {
        return mapToBreakdownDto(breakdownService.getBreakdown(id));
    }
}
