package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.MachineDtoMapper.mapToMachineDto;
import static pl.factoryofthefuture.factorymanagement.mapper.MachineDtoMapper.mapToMachineDtos;

@RestController()
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    @GetMapping()
    public List<MachineDto> getMachines() {
        return mapToMachineDtos(machineService.getMachine());
    }

    @GetMapping("/{id}")
    public MachineDto getMachine(@PathVariable Long id) {
        return mapToMachineDto(machineService.getMachine(id));
    }

}
