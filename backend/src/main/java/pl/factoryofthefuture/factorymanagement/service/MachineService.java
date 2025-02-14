package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.repository.MachineRepository;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public List<Machine> getMachine() {
        return machineRepository.findAll();
    }

    public Machine getMachine(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such machine with id:  " + id));
    }

    public Set<Machine> findMachines(Set<Long> machineIds) {
        return new HashSet<>(machineRepository.findAllById(machineIds));
    }

    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    @Transactional
    public Machine updateMachine(Machine machine) {
        Machine updatedMachine = machineRepository.findById(machine.getId())
                .orElseThrow(() -> new NoSuchElementException("Machine not found with id: " + machine.getId()));
        updatedMachine.setName(machine.getName());
        updatedMachine.setManufacturer(machine.getManufacturer());
        updatedMachine.setProductionDate(machine.getProductionDate());
        updatedMachine.setLastMaintenanceDate(machine.getLastMaintenanceDate());
        updatedMachine.setEnergyConsumption(machine.getEnergyConsumption());
        updatedMachine.setDepartment(machine.getDepartment());
        return machineRepository.save(updatedMachine);
    }

    public void deleteById(long id) {
        machineRepository.deleteById(id);
    }
}
