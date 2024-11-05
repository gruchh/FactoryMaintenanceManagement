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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class BreakdownControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BreakdownService breakdownService;

    @Test
    @WithAnonymousUser
    public void givenUnauthenticatedUser_whenGetEmployees_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/breakdowns")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenUser_whenGetEmployees_thenReturnOk() throws Exception {
        mockMvc.perform(get("/breakdowns")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenAdmin_whenGetBreakdowns_thenReturnForbidden() throws Exception {
        mockMvc.perform(get("/breakdowns")).andExpect(status().isForbidden());
    }
}
