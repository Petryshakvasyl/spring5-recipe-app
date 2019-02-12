package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    public static final String DESCRIPTION = "Desc";
    public static final long LONG_VALUE = 1L;
    UnitOfMeasureCommandToUnitOfMeasure converter;
    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
        //given
       UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setDescription(DESCRIPTION);
        command.setId(LONG_VALUE);

        //when
        UnitOfMeasure converted = converter.convert(command);
        //then
        assertNotNull(converted);
        assertEquals(LONG_VALUE, converted.getId().longValue());
        assertEquals(DESCRIPTION, converted.getDescription());

    }
}