package pl.factoryofthefuture.factorymanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.factoryofthefuture.factorymanagement.service.DepartmentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    @Test
    @WithAnonymousUser
    public void givenUnauthenticatedUser_whenGetDepartment_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/departments")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenUser_whenGetDepartment_thenReturnOk() throws Exception {
        mockMvc.perform(get("/departments")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenAdmin_whenGetDepartment_thenReturnForbidden() throws Exception {
        mockMvc.perform(get("/departments")).andExpect(status().isForbidden());
    }
}
