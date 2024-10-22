package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.MachineDtoMapper.*;

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
    public MachineDto getMachine(@PathVariable long id) {
        return mapToMachineDto(machineService.getMachine(id));
    }

    @PostMapping()
    public ResponseEntity<MachineDto> saveMachine(@RequestBody MachineDto machineDto) {
        Machine saveMachine = machineService.saveMachine(mapDtoToMachine(machineDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToMachineDto(saveMachine));
    }
}
