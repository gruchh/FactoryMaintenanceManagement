package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper.mapDtoToEmployee;
import static pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper.mapToEmployeeDto;
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

    @PutMapping()
    public ResponseEntity<MachineDto> updateMachine(@RequestBody MachineDto machineDto) {
        Machine editedMachine = machineService.updateMachine(mapDtoToMachine(machineDto));
        return ResponseEntity.status(HttpStatus.OK).body(mapToMachineDto(editedMachine));
    }

    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable long id) {
        machineService.deleteById(id);
    }
}
