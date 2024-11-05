package pl.factoryofthefuture.factorymanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.factoryofthefuture.factorymanagement.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeService EmployeeService;

    @Test
    @WithAnonymousUser
    public void givenUnauthenticatedUser_whenGetEmployee_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenUser_whenGetEmployee_thenReturnOk() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenAdmin_whenGetEmployee_thenReturnForbidden() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }
}
