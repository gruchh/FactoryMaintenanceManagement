package pl.factoryofthefuture.factorymanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.factoryofthefuture.factorymanagement.security.filter.JwtFilter;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BreakdownController.class)
public class BreakdownControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private BreakdownService breakdownService;

    @Test
    @WithAnonymousUser
    public void givenUnauthenticatedUser_whenGetEmployees_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/breakdowns")).andExpect(status().isUnauthorized());
    }
}
