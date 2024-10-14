package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/breakdowns")
    public List<BreakdownDto> getBreakdowns() {
        return mapToBreakdownDtos(breakdownService.getBreakdowns());
    }

    @GetMapping("/{id}")
    public BreakdownDto getBreakdownDto(@PathVariable Long id) {
        return mapToBreakdownDto(breakdownService.getBreakdown(id));
    }


}
