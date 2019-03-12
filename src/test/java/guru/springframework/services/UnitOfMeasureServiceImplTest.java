package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    UnitOfMeasureServiceImpl unitOfMeasureService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(new UnitOfMeasureToUnitOfMeasureCommand(),
                unitOfMeasureRepository);
    }

    @Test
    public void findAllUoms() {
        //given
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        uom1.setDescription("each");
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(1L);
        uom2.setDescription("spoon");
        Set<UnitOfMeasure> uoms = new HashSet<>();
        uoms.add(uom1);
        uoms.add(uom2);
        when(unitOfMeasureRepository.findAll()).thenReturn(uoms);
        //when
        Set<UnitOfMeasureCommand> result = unitOfMeasureService.findAllUoms();
        //then
        assertEquals(2, result.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}