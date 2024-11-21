package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }

    public Set<Machine> findMachinesById(Set<Long> machineIds) {
        return new HashSet<>(machineRepository.findAllById(machineIds));
    }

    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public Machine updateMachine(Machine machine) {
        Machine updatedMachine = machineRepository.findById(machine.getId())
                .orElseThrow(() -> new NoSuchElementException("Machine not found with id: " + machine.getId()));
        updatedMachine.setId(updatedMachine.getId());

        return machineRepository.save(updatedMachine);
    }

    public void deleteById(long id) {
        machineRepository.deleteById(id);
    }
}
