package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;
import pl.factoryofthefuture.factorymanagement.service.BudgetService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BudgetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BudgetService budgetService;

    @Test
    @WithAnonymousUser
    public void givenUnauthenticatedUser_whenGetBreakdowns_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/budget")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenUserRole_whenGetEmployees_thenReturnForbidden() throws Exception {
        mockMvc.perform(get("/budget")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenAdminRole_whenGetBreakdowns_thenReturnOk() throws Exception {
        mockMvc.perform(get("/budget")).andExpect(status().isOk());
    }
}
