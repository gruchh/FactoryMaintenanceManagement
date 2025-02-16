package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDetailsDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownListItemDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownWithShortCutDto;
import pl.factoryofthefuture.factorymanagement.entity.projections.BreakdownWithShortCutProjection;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.BreakdownDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;
import pl.factoryofthefuture.factorymanagement.repository.EmployeeRepository;
import pl.factoryofthefuture.factorymanagement.repository.MachineRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BreakdownService {

    private final BreakdownRepository breakdownRepository;
    private final MachineRepository machineRepository;
    private final EmployeeRepository employeeRepository;
    private final BreakdownDtoMapper breakdownDtoMapper;
    private final int PAGE_SIZE = 5;

    public List<BreakdownDto> getAllBreakdownsDtos() {
        List<Breakdown> allBreakdowns = breakdownRepository.findAll();
        return breakdownDtoMapper.mapBreakdownsToDtos(allBreakdowns);
    }

    public List<BreakdownListItemDto> getPaginatedBreakdownsByIdDto(int pageNumber, Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortDirection, "id"));
        List<Breakdown> allBreakdowns = breakdownRepository.findAllBreakdowns(pageable);
        return breakdownDtoMapper.mapBreakdownsToListItemDtos(allBreakdowns);
    }

    public BreakdownDetailsDto getBreakdownDetailByIdDto(Long id) {
        Breakdown breakdown = breakdownRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return breakdownDtoMapper.mapBreakdownToDetailsDto(breakdown);
    }

    public Set<Breakdown> getBreakdownsById(Set<Long> breakdownIds) {
        return new HashSet<>(breakdownRepository.findAllById(breakdownIds));
    }

    public BreakdownDto saveBreakdown(BreakdownDto breakdownDto) {
        Machine machine = null;
        if (breakdownDto.getMachineId() != 0) {
            machine = machineRepository.findById(breakdownDto.getMachineId())
                    .orElseThrow(() -> new NotFoundException(breakdownDto.getMachineId()));
        }
        Set<Employee> employeeSet = null;
        if (breakdownDto.getEmployeeIds() != null && !breakdownDto.getEmployeeIds().isEmpty()) {
            employeeSet = new HashSet<>(employeeRepository.findAllById(breakdownDto.getEmployeeIds()));
        }
        Breakdown breakdown = breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto, machine, employeeSet);
        Breakdown savedBreakdown = breakdownRepository.save(breakdown);
        return breakdownDtoMapper.mapBreakdownToDto(savedBreakdown);
    }

    @Transactional
    public BreakdownDto updateBreakdown(BreakdownDto breakdownDto) {
        Breakdown updatedBreakdown = breakdownRepository.findById(breakdownDto.getId())
                .orElseThrow(() -> new NotFoundException(breakdownDto.getId()));
        updatedBreakdown.setEventDescription(breakdownDto.getEventDescription());
        updatedBreakdown.setStartDate(breakdownDto.getStartDate());
        updatedBreakdown.setEndDate(breakdownDto.getEndDate());
        updatedBreakdown.setSeverity(breakdownDto.getSeverity());
        updatedBreakdown.setCause(breakdownDto.getCause());
        updatedBreakdown.setUsedParts(breakdownDto.getUsedParts());
        updatedBreakdown.setComments(breakdownDto.getComments());
        Machine machine = null;
        if (breakdownDto.getMachineId() != 0) {
            machine = machineRepository.findById(breakdownDto.getMachineId())
                    .orElseThrow(() -> new NotFoundException(breakdownDto.getMachineId()));
        }
        updatedBreakdown.setMachine(machine);
        Set<Employee> employeeSet = null;
        if (breakdownDto.getEmployeeIds() != null && !breakdownDto.getEmployeeIds().isEmpty()) {
            employeeSet = new HashSet<>(employeeRepository.findAllById(breakdownDto.getEmployeeIds()));
        }
        updatedBreakdown.setEmployeeSet(employeeSet);
        Breakdown savedBreakdown = breakdownRepository.save(updatedBreakdown);
        return breakdownDtoMapper.mapBreakdownToDto(savedBreakdown);
    }

    @Transactional
    public void deleteById(long id) {
        if (!breakdownRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        breakdownRepository.deleteById(id);
    }

    public List<BreakdownWithShortCutDto> getAllBreakdownsWitShortCut() {
        List<BreakdownWithShortCutProjection> allBreakdownsWithShortCut = breakdownRepository.findAllBreakdownsWithShortCut();
        return breakdownDtoMapper.mapBreakdownProjectionToDtos(allBreakdownsWithShortCut);
    }
}