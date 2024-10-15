package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
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

    @GetMapping("/")
    public List<BreakdownDto> getBreakdowns() {
        return mapToBreakdownDtos(breakdownService.getBreakdowns());
    }

    @GetMapping("/page")
    public List<Breakdown> getPaginatedBreakdown(@RequestParam(required = false) int page) {
        int pageNumber = page >= 1 ? page - 1 : 0;
        return breakdownService.findAllBreakdowns(pageNumber);
    }

    @GetMapping("/{id}")
    public BreakdownDto getBreakdownDto(@PathVariable Long id) {
        return mapToBreakdownDto(breakdownService.getBreakdown(id));
    }

    @GetMapping("/page/employees")
    public List<Breakdown> getBreakdownWithEmployees (@RequestParam(required = false) int page) {
        int pageNumber = page >= 1 ? page - 1 : 0;
        return breakdownService.getBreakdownsWithEmployees(pageNumber);
    }


}
