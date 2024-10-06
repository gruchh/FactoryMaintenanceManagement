package pl.factoryofthefuture.factorymanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.Entity.Machine;
import pl.factoryofthefuture.factorymanagement.Service.MachineService;

import java.util.List;

@RestController()
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {

    @Autowired
    MachineService machineService;

    @GetMapping()
    public List<Machine> getMachines() {
        return machineService.getMachine();
    }

    @GetMapping("/{id}")
    public Machine getMachine(@PathVariable Long id) {
        return machineService.getMachine(id);
    }


}
