package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "notes for recipe";
    NotesToNotesCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setRecipeNotes(RECIPE_NOTES);
        notes.setId(ID_VALUE);

        //when
        NotesCommand notesCommand = converter.convert(notes);
        //then
        assertNotNull(notesCommand);
        assertEquals(ID_VALUE, notesCommand.getId().longValue());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());

    }
}