package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.mapper.MachineDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;

@RestController()
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;
    private final MachineDtoMapper machineDtoMapper;

    @GetMapping()
    public List<MachineDto> getMachines() {
        return machineDtoMapper.mapMachinesToDtos(machineService.getMachine());
    }

    @GetMapping("/{id}")
    public MachineDto getMachine(@PathVariable long id) {
        return machineDtoMapper.mapMachineToDto(machineService.getMachine(id));
    }

    @PostMapping()
    public ResponseEntity<MachineDto> saveMachine(@RequestBody MachineDto machineDto) {
        Machine saveMachine = machineService.saveMachine(machineDtoMapper.mapDtoToMachine(machineDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(machineDtoMapper.mapMachineToDto(saveMachine));
    }

    @PutMapping()
    public ResponseEntity<MachineDto> updateMachine(@RequestBody MachineDto machineDto) {
        Machine editedMachine = machineService.updateMachine(machineDtoMapper.mapDtoToMachine(machineDto));
        return ResponseEntity.status(HttpStatus.OK).body(machineDtoMapper.mapMachineToDto(editedMachine));
    }

    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable long id) {
        machineService.deleteById(id);
    }
}
