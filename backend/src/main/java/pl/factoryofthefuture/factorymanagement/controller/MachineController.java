package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    @GetMapping()
    public ResponseEntity<List<MachineDto>> getAllMachines() {
        try {
            List<MachineDto> machineDtos = machineService.getAllMachinesDtos();
            return ResponseEntity.ok(machineDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineDto> getMachine(@PathVariable long id) {
        try {
            MachineDto machineDto = machineService.getMachineDtoById(id);
            if (machineDto != null) {
                return ResponseEntity.ok(machineDto);
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
            MachineDto savedMachineDto = machineService.saveMachine(machineDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMachineDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<MachineDto> updateMachine(@RequestBody MachineDto machineDto) {
        MachineDto updatedMachineDto = machineService.updateMachine(machineDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMachineDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable long id) {
        try {
            machineService.deleteMachineById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}