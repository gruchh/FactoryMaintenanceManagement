package pl.factoryofthefuture.factorymanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDetailsDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownListItemDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownWithShortCutDto;
import pl.factoryofthefuture.factorymanagement.enums.SeverityType;
import pl.factoryofthefuture.factorymanagement.security.service.JwtService;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BreakdownController.class)
public class BreakdownControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BreakdownService breakdownService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void getAllBreakdowns_ReturnsOkAndBreakdownDtos() throws Exception {
        List<BreakdownDto> breakdownDtos = Arrays.asList(
                BreakdownDto.builder()
                        .id(1L)
                        .eventDescription("Breakdown 1")
                        .startDate(LocalDate.now())
                        .severity(SeverityType.LOW)
                        .machineId(1L)
                        .build(),
                BreakdownDto.builder()
                        .id(2L)
                        .eventDescription("Breakdown 2")
                        .startDate(LocalDate.now())
                        .severity(SeverityType.LOW)
                        .machineId(2L)
                        .build()
        );
        Mockito.when(breakdownService.getAllBreakdownsDtos()).thenReturn(breakdownDtos);

        mockMvc.perform(get("/breakdowns"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(breakdownDtos)));
    }

    @Test
    @WithMockUser
    void getAllBreakdowns_ReturnsInternalServerErrorOnException() throws Exception {
        Mockito.when(breakdownService.getAllBreakdownsDtos()).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(get("/breakdowns"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void getPaginatedBreakdowns_ReturnsOkAndBreakdownListItemDtos() throws Exception {
        List<BreakdownListItemDto> breakdownDtos = Arrays.asList(
                BreakdownListItemDto.builder().id(1L).eventDescription("Breakdown 1").machineId(0).build(),
                BreakdownListItemDto.builder().id(2L).eventDescription("Breakdown 2").machineId(0).build()
        );
        Mockito.when(breakdownService.getPaginatedBreakdownsByIdDto(0, Sort.Direction.ASC)).thenReturn(breakdownDtos);

        mockMvc.perform(get("/breakdowns/page"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(breakdownDtos)));
    }

    @Test
    @WithMockUser
    void getPaginatedBreakdowns_WithPageNumberAndSort_ReturnsOkAndBreakdownListItemDtos() throws Exception {
        List<BreakdownListItemDto> breakdownDtos = Arrays.asList(
                BreakdownListItemDto.builder().id(3L).eventDescription("Breakdown 3").machineId(0).build(),
                BreakdownListItemDto.builder().id(4L).eventDescription("Breakdown 4").machineId(0).build()
        );
        Mockito.when(breakdownService.getPaginatedBreakdownsByIdDto(2, Sort.Direction.DESC)).thenReturn(breakdownDtos);

        mockMvc.perform(get("/breakdowns/page?value=3&sortDirection=DESC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(breakdownDtos)));
    }

    @Test
    @WithMockUser
    void getPaginatedBreakdowns_ReturnsInternalServerErrorOnException() throws Exception {
        Mockito.when(breakdownService.getPaginatedBreakdownsByIdDto(anyInt(), any())).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(get("/breakdowns/page"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void getBreakdown_ReturnsOkAndBreakdownDetailsDto() throws Exception {
        BreakdownDetailsDto breakdownDto = BreakdownDetailsDto.builder()
                .id(1L)
                .eventDescription("Breakdown 1")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .cause("Cause")
                .usedParts("Parts")
                .comments("Comments")
                .employeeIds(Set.of(1L, 2L))
                .machineId(1L)
                .endDate(LocalDate.now().plusDays(1))
                .build();
        Mockito.when(breakdownService.getBreakdownDetailByIdDto(1L)).thenReturn(breakdownDto);

        mockMvc.perform(get("/breakdowns/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(breakdownDto)));
    }

    @Test
    @WithMockUser
    void getBreakdown_ReturnsNotFoundForNonExistingBreakdown() throws Exception {
        Mockito.when(breakdownService.getBreakdownDetailByIdDto(1L)).thenThrow(new NoSuchElementException("Breakdown not found"));

        mockMvc.perform(get("/breakdowns/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void getBreakdown_ReturnsInternalServerErrorOnException() throws Exception {
        Mockito.when(breakdownService.getBreakdownDetailByIdDto(anyLong())).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(get("/breakdowns/1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void saveBreakdown_ReturnsCreatedAndSavedBreakdownDto() throws Exception {
        BreakdownDto breakdownDtoToSave = BreakdownDto.builder()
                .id(1L)
                .eventDescription("New Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        BreakdownDto savedBreakdownDto = BreakdownDto.builder()
                .id(1L)
                .eventDescription("New Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        Mockito.when(breakdownService.saveBreakdown(any(BreakdownDto.class))).thenReturn(savedBreakdownDto);

        mockMvc.perform(post("/breakdowns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakdownDtoToSave))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(savedBreakdownDto)));
    }

    @Test
    @WithMockUser
    void saveBreakdown_ReturnsBadRequestForDataIntegrityViolation() throws Exception {
        BreakdownDto breakdownDtoToSave = BreakdownDto.builder()
                .id(1L)
                .eventDescription("Invalid Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        Mockito.when(breakdownService.saveBreakdown(any(BreakdownDto.class))).thenThrow(new DataIntegrityViolationException("Data integrity violation"));

        mockMvc.perform(post("/breakdowns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakdownDtoToSave))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void saveBreakdown_ReturnsInternalServerErrorOnException() throws Exception {
        BreakdownDto breakdownDtoToSave = BreakdownDto.builder()
                .id(1L)
                .eventDescription("New Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        Mockito.when(breakdownService.saveBreakdown(any(BreakdownDto.class))).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(post("/breakdowns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakdownDtoToSave))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void updateBreakdown_ReturnsOkAndUpdatedBreakdownDto() throws Exception {
        BreakdownDto breakdownDtoToUpdate = BreakdownDto.builder()
                .id(1L)
                .eventDescription("Updated Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        BreakdownDto updatedBreakdownDto = BreakdownDto.builder()
                .id(1L)
                .eventDescription("Updated Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        Mockito.when(breakdownService.updateBreakdown(any(BreakdownDto.class))).thenReturn(updatedBreakdownDto);

        mockMvc.perform(put("/breakdowns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakdownDtoToUpdate))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(updatedBreakdownDto)));
    }

    @Test
    @WithMockUser
    void updateBreakdown_ReturnsNotFoundForNonExistingBreakdown() throws Exception {
        BreakdownDto breakdownDtoToUpdate = BreakdownDto.builder()
                .id(1L)
                .eventDescription("Updated Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        Mockito.when(breakdownService.updateBreakdown(any(BreakdownDto.class))).thenThrow(new NoSuchElementException("Breakdown not found"));

        mockMvc.perform(put("/breakdowns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakdownDtoToUpdate))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void updateBreakdown_ReturnsInternalServerErrorOnException() throws Exception {
        BreakdownDto breakdownDtoToUpdate = BreakdownDto.builder()
                .id(1L)
                .eventDescription("Updated Breakdown")
                .startDate(LocalDate.now())
                .severity(SeverityType.LOW)
                .machineId(1L)
                .build();
        Mockito.when(breakdownService.updateBreakdown(any(BreakdownDto.class))).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(put("/breakdowns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakdownDtoToUpdate))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void deleteBreakdown_ReturnsNoContentOnSuccessfulDeletion() throws Exception {
        Mockito.doNothing().when(breakdownService).deleteById(1L);

        mockMvc.perform(delete("/breakdowns/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void deleteBreakdown_ReturnsNotFoundForNonExistingBreakdown() throws Exception {
        Mockito.doThrow(new NoSuchElementException("Breakdown not found")).when(breakdownService).deleteById(1L);

        mockMvc.perform(delete("/breakdowns/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void deleteBreakdown_ReturnsInternalServerErrorOnException() throws Exception {
        Mockito.doThrow(new RuntimeException("Service error")).when(breakdownService).deleteById(1L);

        mockMvc.perform(delete("/breakdowns/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void getBreakdownsWithShortcut_ReturnsOkAndBreakdownWithShortCutDtos() throws Exception {
        List<BreakdownWithShortCutDto> breakdownWithShortCutDtos = Arrays.asList(
                BreakdownWithShortCutDto.builder().id(1L).shortCut("ShortCut 1").build(),
                BreakdownWithShortCutDto.builder().id(2L).shortCut("ShortCut 2").build()
        );
        Mockito.when(breakdownService.getAllBreakdownsWitShortCut()).thenReturn(breakdownWithShortCutDtos);

        mockMvc.perform(get("/breakdowns/withShortcut"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(breakdownWithShortCutDtos)));
    }

    @Test
    @WithMockUser
    void getBreakdownsWithShortcut_ReturnsInternalServerErrorOnException() throws Exception {
        Mockito.when(breakdownService.getAllBreakdownsWitShortCut()).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(get("/breakdowns/withShortcut"))
                .andExpect(status().isInternalServerError());
    }
}