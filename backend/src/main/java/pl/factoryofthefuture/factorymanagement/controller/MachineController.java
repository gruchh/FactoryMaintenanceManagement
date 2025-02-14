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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;
    private final MachineDtoMapper machineDtoMapper;

    @GetMapping()
    public ResponseEntity<List<MachineDto>> getAllMachines() {
        try {
            List<MachineDto> machineDtos = machineDtoMapper.mapMachinesToDtos(machineService.getMachine());
            return ResponseEntity.ok(machineDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineDto> getMachine(@PathVariable long id) {
        try {
            Machine machine = machineService.getMachine(id);
            if (machine != null) {
                return ResponseEntity.ok(machineDtoMapper.mapMachineToDto(machine));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<MachineDto> saveMachine(@RequestBody MachineDto machineDto) {
        try {
            Machine savedMachine = machineService.saveMachine(machineDtoMapper.mapDtoToMachine(machineDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(machineDtoMapper.mapMachineToDto(savedMachine));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<MachineDto> updateMachine(@RequestBody MachineDto machineDto) {
        Machine editedMachine = machineService.updateMachine(machineDtoMapper.mapDtoToMachine(machineDto));
        return ResponseEntity.status(HttpStatus.OK).body(machineDtoMapper.mapMachineToDto(editedMachine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable long id) {
        try {
            machineService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
