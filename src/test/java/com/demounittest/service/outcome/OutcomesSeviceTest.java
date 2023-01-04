package com.demounittest.service.outcome;

import com.demounittest.model.Outcomes;
import com.demounittest.repository.IOutcomesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OutcomesSeviceTest {
    private IOutcomesRepository outcomesRepository = Mockito.mock(IOutcomesRepository.class);
    private IOutComesService outComesService = new OutComesService(outcomesRepository);

    private static Outcomes outcomes;
    private static List<Outcomes> emptyOutComes;
    private static List<Outcomes> outcomesList;

    @BeforeEach
    void init(){
        outcomes = new Outcomes();
        outcomes.setTitle("Phần 1: Lập trình căn bản");
        when(outcomesRepository.save(outcomes)).thenReturn(outcomes);

    }
    @Test
    @DisplayName("save outcome call outcomeRepo 1")
    void whensave_thenCallOutcomRepo(){
        outComesService.save(outcomes);
        verify(outcomesRepository, times(1)).save(outcomes);
    }

    @DisplayName("findAll can return list has 0 outcomes")
    @Test
    void whenFindAll_thenReturn0Outcomes() {
        when(outComesService.findAll()).thenReturn(emptyOutComes);
        List<Outcomes> result = (List<Outcomes>) outComesService.findAll();
        verify(outcomesRepository).findAll();
        assertEquals(emptyOutComes, result);
    }

    @DisplayName("find All can return 1 outcomes")
    @Test
    void whenFindAll_thenReturn1Element(){
        when(outcomesRepository.findAll()).thenReturn(outcomesList);
        List<Outcomes> result = (List<Outcomes>) outComesService.findAll();
        verify(outcomesRepository).findAll();
        assertEquals(outcomesList, result);
    }
    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void remove() {
    }

    @Test
    void findByTitle() {
    }
}
