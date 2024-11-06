package pl.factoryofthefuture.factorymanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BreakdownServiceTest {

    @Mock
    private BreakdownRepository breakdownRepository;

    @InjectMocks
    private BreakdownService breakdownService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenBreakdownBeforeUpdate_whenUpdateBreakdown_thanSaveUpdatedBreakdown() {
        Breakdown breakdownBeforeUpdate = Breakdown.builder()
                .id(1L)
                .cause("Sensor")
                .build();

        Breakdown modifiedBreakdown = Breakdown.builder()
                .id(1L) // Ensure the same ID as breakdownBeforeUpdate
                .cause("Test")
                .build();

        when(breakdownRepository.findById(1L)).thenReturn(Optional.of(breakdownBeforeUpdate));
        when(breakdownRepository.save(any(Breakdown.class))).thenReturn(modifiedBreakdown);

        Breakdown updatedBreakdown = breakdownService.updateBreakdown(modifiedBreakdown);

        assertNotNull(modifiedBreakdown);
        assertEquals(modifiedBreakdown.getEventDescription(), updatedBreakdown.getEventDescription());
        verify(breakdownRepository, times(1)).findById(1L);
        verify(breakdownRepository, times(1)).save(any(Breakdown.class));
    }

    @Test
    void givenNewBreakdown_whenDatabaseNotFound_thaRollbackTransaction() {
        Breakdown newBreakdown = Breakdown.builder()
                .id(99L)
                .build();
        when(breakdownRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            breakdownService.updateBreakdown(newBreakdown);
        });
        verify(breakdownRepository, times(1)).findById(99L);
        verify(breakdownRepository, times(0)).save(any(Breakdown.class));
    }

}