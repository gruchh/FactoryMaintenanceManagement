package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.DepartmentDtoMapper;
import pl.factoryofthefuture.factorymanagement.mapper.MachineDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.DepartmentRepository;
import pl.factoryofthefuture.factorymanagement.repository.MachineRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MachineService {

    private final MachineRepository machineRepository;
    private final MachineDtoMapper machineDtoMapper;
    private final DepartmentRepository departmentRepository;
    private final DepartmentDtoMapper departmentDtoMapper;

    public List<MachineDto> getAllMachinesDtos() {
        List<Machine> allMachines = machineRepository.findAll();
        return machineDtoMapper.mapMachinesToDtos(allMachines);
    }

    public MachineDto getMachineDtoById(Long id) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return machineDtoMapper.mapMachineToDto(machine);
    }

    public Machine getMachineById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Set<Machine> getMachinesByIds(Set<Long> machineIds) {
        return new HashSet<>(machineRepository.findAllById(machineIds));
    }

    public MachineDto saveMachine(MachineDto machineDto) {
        Machine machine = machineDtoMapper.mapDtoToMachine(machineDto);
        Machine savedMachine = machineRepository.save(machine);
        return machineDtoMapper.mapMachineToDto(savedMachine);
    }

    @Transactional
    public MachineDto updateMachine(MachineDto machineDto) {
        Machine updatedMachine = machineRepository.findById(machineDto.getId())
                .orElseThrow(() -> new NotFoundException(machineDto.getId()));
        updatedMachine.setName(machineDto.getName());
        updatedMachine.setManufacturer(machineDto.getManufacturer());
        updatedMachine.setProductionDate(machineDto.getProductionDate());
        updatedMachine.setLastMaintenanceDate(machineDto.getLastMaintenanceDate());
        updatedMachine.setEnergyConsumption(machineDto.getEnergyConsumption());
        if (machineDto.getDepartmentId() != 0) {
            Department department = departmentRepository.findById(machineDto.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException(machineDto.getDepartmentId()));
            updatedMachine.setDepartment(department);
        } else {
            updatedMachine.setDepartment(null);
        }
        return machineDtoMapper.mapMachineToDto(updatedMachine);
    }

    @Transactional
    public void deleteMachineById(long id) {
        if (!machineRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        machineRepository.deleteById(id);
    }
}