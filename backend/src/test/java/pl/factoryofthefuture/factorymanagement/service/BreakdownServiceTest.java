package pl.factoryofthefuture.factorymanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BreakdownServiceTest {

    @Mock
    private BreakdownRepository breakdownRepository;
    @Mock
    private MachineRepository machineRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private BreakdownDtoMapper breakdownDtoMapper;
    @InjectMocks
    private BreakdownService breakdownService;

    @Test
    void getAllBreakdownsDtos_shouldReturnListOfBreakdownDto() {
        List<Breakdown> breakdowns = Arrays.asList(Breakdown.builder().id(1L).build(), Breakdown.builder().id(2L).build());
        List<BreakdownDto> breakdownDtos = Arrays.asList(
                BreakdownDto.builder().id(1L).eventDescription("desc1").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("cause1").usedParts("parts1").comments("comments1").machineId(1L).build(),
                BreakdownDto.builder().id(2L).eventDescription("desc2").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("cause2").usedParts("parts2").comments("comments2").machineId(2L).build()
        );
        when(breakdownRepository.findAll()).thenReturn(breakdowns);
        when(breakdownDtoMapper.mapBreakdownsToDtos(breakdowns)).thenReturn(breakdownDtos);

        List<BreakdownDto> result = breakdownService.getAllBreakdownsDtos();

        assertEquals(breakdownDtos, result);
        verify(breakdownRepository, times(1)).findAll();
        verify(breakdownDtoMapper, times(1)).mapBreakdownsToDtos(breakdowns);
    }

    @Test
    void getPaginatedBreakdownsByIdDto_shouldReturnListOfBreakdownListItemDto() {
        int pageNumber = 0;
        Sort.Direction sortDirection = Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by(sortDirection, "id"));
        List<Breakdown> breakdowns = Arrays.asList(Breakdown.builder().id(1L).build(), Breakdown.builder().id(2L).build());
        List<BreakdownListItemDto> breakdownListItemDtos = Arrays.asList(BreakdownListItemDto.builder().id(1L).machineId(1L).build(), BreakdownListItemDto.builder().id(2L).machineId(2L).build());
        when(breakdownRepository.findAllBreakdowns(pageable)).thenReturn(breakdowns);
        when(breakdownDtoMapper.mapBreakdownsToListItemDtos(breakdowns)).thenReturn(breakdownListItemDtos);

        List<BreakdownListItemDto> result = breakdownService.getPaginatedBreakdownsByIdDto(pageNumber, sortDirection);

        assertEquals(breakdownListItemDtos, result);
        verify(breakdownRepository, times(1)).findAllBreakdowns(pageable);
        verify(breakdownDtoMapper, times(1)).mapBreakdownsToListItemDtos(breakdowns);
    }

    @Test
    void getBreakdownDetailByIdDto_shouldReturnBreakdownDetailsDto_whenBreakdownExists() {
        Long id = 1L;
        Breakdown breakdown = Breakdown.builder().id(1L).build();
        BreakdownDetailsDto breakdownDetailsDto = BreakdownDetailsDto.builder().id(1L).machineId(1L).build();
        when(breakdownRepository.findById(id)).thenReturn(Optional.of(breakdown));
        when(breakdownDtoMapper.mapBreakdownToDetailsDto(breakdown)).thenReturn(breakdownDetailsDto);

        BreakdownDetailsDto result = breakdownService.getBreakdownDetailByIdDto(id);

        assertEquals(breakdownDetailsDto, result);
        verify(breakdownRepository, times(1)).findById(id);
        verify(breakdownDtoMapper, times(1)).mapBreakdownToDetailsDto(breakdown);
    }

    @Test
    void getBreakdownDetailByIdDto_shouldThrowNotFoundException_whenBreakdownNotFound() {
        Long id = 1L;
        when(breakdownRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> breakdownService.getBreakdownDetailByIdDto(id));
        verify(breakdownRepository, times(1)).findById(id);
        verifyNoInteractions(breakdownDtoMapper);
    }

    @Test
    void getBreakdownsById_shouldReturnSetOfBreakdowns() {
        Set<Long> breakdownIds = Set.of(1L, 2L);
        List<Breakdown> breakdowns = Arrays.asList(Breakdown.builder().id(1L).build(), Breakdown.builder().id(2L).build());
        when(breakdownRepository.findAllById(breakdownIds)).thenReturn(breakdowns);

        Set<Breakdown> result = breakdownService.getBreakdownsById(breakdownIds);

        assertEquals(new HashSet<>(breakdowns), result);
        verify(breakdownRepository, times(1)).findAllById(breakdownIds);
    }

    @Test
    void saveBreakdown_shouldReturnSavedBreakdownDto() {
        BreakdownDto breakdownDto = BreakdownDto.builder().id(1L).machineId(1L).employeeIds(Set.of(1L)).build();
        Machine machine = Machine.builder().id(1L).energyConsumption(0).build();
        Set<Employee> employeeSet = Set.of(Employee.builder().id(1L).shift(0).performanceRating(0).build());
        Breakdown breakdown = Breakdown.builder().id(1L).build();
        Breakdown savedBreakdown = Breakdown.builder().id(1L).build();
        BreakdownDto savedBreakdownDto = BreakdownDto.builder().id(1L).eventDescription("desc").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("cause").usedParts("parts").comments("comments").machineId(1L).build();

        when(machineRepository.findById(breakdownDto.getMachineId())).thenReturn(Optional.of(machine));
        when(employeeRepository.findAllById(breakdownDto.getEmployeeIds())).thenReturn(List.of(employeeSet.iterator().next()));
        when(breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto, machine, employeeSet)).thenReturn(breakdown);
        when(breakdownRepository.save(breakdown)).thenReturn(savedBreakdown);
        when(breakdownDtoMapper.mapBreakdownToDto(savedBreakdown)).thenReturn(savedBreakdownDto);

        BreakdownDto result = breakdownService.saveBreakdown(breakdownDto);

        assertEquals(savedBreakdownDto, result);
        verify(machineRepository, times(1)).findById(breakdownDto.getMachineId());
        verify(employeeRepository, times(1)).findAllById(breakdownDto.getEmployeeIds());
        verify(breakdownDtoMapper, times(1)).mapBreakdownDtoToEntity(breakdownDto, machine, employeeSet);
        verify(breakdownRepository, times(1)).save(breakdown);
        verify(breakdownDtoMapper, times(1)).mapBreakdownToDto(savedBreakdown);
    }

    @Test
    void saveBreakdown_shouldReturnSavedBreakdownDto_whenMachineIdIsZeroAndEmployeeIdsIsNull() {
        BreakdownDto breakdownDto = BreakdownDto.builder().id(1L).machineId(0L).employeeIds(null).build();
        Breakdown breakdown = Breakdown.builder().id(1L).build();
        Breakdown savedBreakdown = Breakdown.builder().id(1L).build();
        BreakdownDto savedBreakdownDto = BreakdownDto.builder().id(1L).eventDescription("desc").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("cause").usedParts("parts").comments("comments").machineId(0L).build();

        when(breakdownDtoMapper.mapBreakdownDtoToEntity(breakdownDto, null, null)).thenReturn(breakdown);
        when(breakdownRepository.save(breakdown)).thenReturn(savedBreakdown);
        when(breakdownDtoMapper.mapBreakdownToDto(savedBreakdown)).thenReturn(savedBreakdownDto);

        BreakdownDto result = breakdownService.saveBreakdown(breakdownDto);

        assertEquals(savedBreakdownDto, result);
        verify(machineRepository, never()).findById(anyLong());
        verify(employeeRepository, never()).findAllById(anySet());
        verify(breakdownDtoMapper, times(1)).mapBreakdownDtoToEntity(breakdownDto, null, null);
        verify(breakdownRepository, times(1)).save(breakdown);
        verify(breakdownDtoMapper, times(1)).mapBreakdownToDto(savedBreakdown);
    }

    @Test
    void saveBreakdown_shouldThrowNotFoundException_whenMachineNotFound() {
        BreakdownDto breakdownDto = BreakdownDto.builder().id(1L).machineId(1L).build();
        when(machineRepository.findById(breakdownDto.getMachineId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> breakdownService.saveBreakdown(breakdownDto));
        verify(machineRepository, times(1)).findById(breakdownDto.getMachineId());
        verifyNoInteractions(employeeRepository);
        verifyNoInteractions(breakdownDtoMapper);
        verify(breakdownRepository, never()).save(any());
    }

    @Test
    void updateBreakdown_shouldReturnUpdatedBreakdownDto_whenBreakdownExists() {
        BreakdownDto breakdownDto = BreakdownDto.builder().id(1L).machineId(1L).employeeIds(Set.of(1L)).eventDescription("newDesc").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("newCause").usedParts("newParts").comments("newComments").build();
        Breakdown existingBreakdown = Breakdown.builder().id(1L).build();
        Machine machine = Machine.builder().id(1L).energyConsumption(0).build();
        Set<Employee> employeeSet = Set.of(Employee.builder().id(1L).shift(0).performanceRating(0).build());
        Breakdown updatedBreakdown = Breakdown.builder().id(1L).build();
        BreakdownDto updatedBreakdownDto = BreakdownDto.builder().id(1L).eventDescription("newDesc").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("newCause").usedParts("newParts").comments("newComments").machineId(1L).build();

        when(breakdownRepository.findById(breakdownDto.getId())).thenReturn(Optional.of(existingBreakdown));
        when(machineRepository.findById(breakdownDto.getMachineId())).thenReturn(Optional.of(machine));
        when(employeeRepository.findAllById(breakdownDto.getEmployeeIds())).thenReturn(List.of(employeeSet.iterator().next()));
        when(breakdownRepository.save(existingBreakdown)).thenReturn(updatedBreakdown);
        when(breakdownDtoMapper.mapBreakdownToDto(updatedBreakdown)).thenReturn(updatedBreakdownDto);

        BreakdownDto result = breakdownService.updateBreakdown(breakdownDto);

        assertEquals(updatedBreakdownDto, result);
        assertEquals(breakdownDto.getEventDescription(), existingBreakdown.getEventDescription());
        assertEquals(breakdownDto.getStartDate(), existingBreakdown.getStartDate());
        assertEquals(breakdownDto.getEndDate(), existingBreakdown.getEndDate());
        assertEquals(breakdownDto.getCause(), existingBreakdown.getCause());
        assertEquals(breakdownDto.getUsedParts(), existingBreakdown.getUsedParts());
        assertEquals(breakdownDto.getComments(), existingBreakdown.getComments());
        assertEquals(machine, existingBreakdown.getMachine());
        assertEquals(employeeSet, existingBreakdown.getEmployeeSet());

        verify(breakdownRepository, times(1)).findById(breakdownDto.getId());
        verify(machineRepository, times(1)).findById(breakdownDto.getMachineId());
        verify(employeeRepository, times(1)).findAllById(breakdownDto.getEmployeeIds());
        verify(breakdownRepository, times(1)).save(existingBreakdown);
        verify(breakdownDtoMapper, times(1)).mapBreakdownToDto(updatedBreakdown);
    }

    @Test
    void updateBreakdown_shouldReturnUpdatedBreakdownDto_whenMachineIdIsZeroAndEmployeeIdsIsNull() {
        BreakdownDto breakdownDto = BreakdownDto.builder().id(1L).machineId(0L).employeeIds(null).eventDescription("newDesc").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("newCause").usedParts("newParts").comments("newComments").build();
        Breakdown existingBreakdown = Breakdown.builder().id(1L).build();
        Breakdown updatedBreakdown = Breakdown.builder().id(1L).build();
        BreakdownDto updatedBreakdownDto = BreakdownDto.builder().id(1L).eventDescription("newDesc").startDate(LocalDate.now()).endDate(LocalDate.now()).cause("newCause").usedParts("newParts").comments("newComments").machineId(0L).build();

        when(breakdownRepository.findById(breakdownDto.getId())).thenReturn(Optional.of(existingBreakdown));
        when(breakdownRepository.save(existingBreakdown)).thenReturn(updatedBreakdown);
        when(breakdownDtoMapper.mapBreakdownToDto(updatedBreakdown)).thenReturn(updatedBreakdownDto);

        BreakdownDto result = breakdownService.updateBreakdown(breakdownDto);

        assertEquals(updatedBreakdownDto, result);
        assertEquals(breakdownDto.getEventDescription(), existingBreakdown.getEventDescription());
        assertEquals(breakdownDto.getStartDate(), existingBreakdown.getStartDate());
        assertEquals(breakdownDto.getEndDate(), existingBreakdown.getEndDate());
        assertEquals(breakdownDto.getCause(), existingBreakdown.getCause());
        assertEquals(breakdownDto.getUsedParts(), existingBreakdown.getUsedParts());
        assertEquals(breakdownDto.getComments(), existingBreakdown.getComments());
        assertNull(existingBreakdown.getMachine());
        assertNull(existingBreakdown.getEmployeeSet());

        verify(breakdownRepository, times(1)).findById(breakdownDto.getId());
        verify(machineRepository, never()).findById(anyLong());
        verify(employeeRepository, never()).findAllById(anySet());
        verify(breakdownRepository, times(1)).save(existingBreakdown);
        verify(breakdownDtoMapper, times(1)).mapBreakdownToDto(updatedBreakdown);
    }


    @Test
    void updateBreakdown_shouldThrowNotFoundException_whenBreakdownNotFound() {
        BreakdownDto breakdownDto = BreakdownDto.builder().id(1L).machineId(1L).build();
        when(breakdownRepository.findById(breakdownDto.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> breakdownService.updateBreakdown(breakdownDto));
        verify(breakdownRepository, times(1)).findById(breakdownDto.getId());
        verifyNoInteractions(machineRepository);
        verifyNoInteractions(employeeRepository);
        verifyNoInteractions(breakdownDtoMapper);
        verify(breakdownRepository, never()).save(any());
    }

    @Test
    void deleteById_shouldDeleteBreakdown_whenBreakdownExists() {
        long id = 1L;
        when(breakdownRepository.existsById(id)).thenReturn(true);

        breakdownService.deleteById(id);

        verify(breakdownRepository, times(1)).existsById(id);
        verify(breakdownRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_shouldThrowNotFoundException_whenBreakdownNotFound() {
        long id = 1L;
        when(breakdownRepository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> breakdownService.deleteById(id));
        verify(breakdownRepository, times(1)).existsById(id);
        verify(breakdownRepository, never()).deleteById(anyLong());
    }

    @Test
    void getAllBreakdownsWitShortCut_shouldReturnListOfBreakdownWithShortCutDto() {
        List<BreakdownWithShortCutProjection> projections = Arrays.asList(mock(BreakdownWithShortCutProjection.class), mock(BreakdownWithShortCutProjection.class));
        List<BreakdownWithShortCutDto> dtos = Arrays.asList(BreakdownWithShortCutDto.builder().id(1L).build(), BreakdownWithShortCutDto.builder().id(2L).build());
        when(breakdownRepository.findAllBreakdownsWithShortCut()).thenReturn(projections);
        when(breakdownDtoMapper.mapBreakdownProjectionToDtos(projections)).thenReturn(dtos);

        List<BreakdownWithShortCutDto> result = breakdownService.getAllBreakdownsWitShortCut();

        assertEquals(dtos, result);
        verify(breakdownRepository, times(1)).findAllBreakdownsWithShortCut();
        verify(breakdownDtoMapper, times(1)).mapBreakdownProjectionToDtos(projections);
    }
}