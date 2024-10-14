package pl.factoryofthefuture.factorymanagement.mapper;

import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;

import java.util.List;
import java.util.stream.Collectors;


public class BreakdownDtoMapper {

    public static List<BreakdownDto> mapToBreakdownDtos(List<Breakdown> breakdowns) {

        return breakdowns.stream()
                .map(BreakdownDtoMapper::mapToBreakdownDto)
                .collect(Collectors.toList());
    }

    public static BreakdownDto mapToBreakdownDto (Breakdown breakdown) {
        return BreakdownDto.builder()
                .id(breakdown.getId())
                .eventDescription(breakdown.getEventDescription())
                .startDate(breakdown.getStartDate())
                .endDate(breakdown.getEndDate())
                .severity(breakdown.getSeverity())
                .cause(breakdown.getCause())
                .usedParts(breakdown.getUsedParts())
                .comments(breakdown.getComments())
                .build();
    }



}
