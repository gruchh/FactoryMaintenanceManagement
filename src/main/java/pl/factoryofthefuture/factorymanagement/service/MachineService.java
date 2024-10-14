package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.repository.MachineRepository;

import java.util.List;
import java.util.NoSuchElementException;

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


}
