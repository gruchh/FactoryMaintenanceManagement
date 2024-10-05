package pl.factoryofthefuture.factorymanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.Model.Breakdown;
import pl.factoryofthefuture.factorymanagement.Service.BreakdownService;

import java.util.List;

@RestController
@RequestMapping("/breakdowns")
@RequiredArgsConstructor
public class BreakdownController {

    @Autowired
    BreakdownService breakdownService;

    @GetMapping("/breakdowns")
    public List<Breakdown> getBreakdowns() {
        return breakdownService.getBreakdowns();
    }

    @GetMapping("/breakdowns/{id}")
    public Breakdown getBreakdown(@PathVariable Long id) {
        return breakdownService.getBreakdown(id);
    }


}
