package pl.factoryofthefuture.factorymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

@SpringBootApplication
public class FactoryManagementApplication {

	@Autowired
	BreakdownService breakdownService;

	public static void main(String[] args) {
		SpringApplication.run(FactoryManagementApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		breakdownService.getBreakdownsWithEmployees(0);
	}

}
