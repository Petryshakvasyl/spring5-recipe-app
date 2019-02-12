package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final long LONG_VALUE = 1L;
    public static final String RECIPE_NOTES = "notes for recipe";
    private NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }
    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }
    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setRecipeNotes(RECIPE_NOTES);
        notesCommand.setId(LONG_VALUE);

        //when
        Notes notes = converter.convert(notesCommand);
        //then
        assertNotNull(notes);
        assertEquals(LONG_VALUE, notes.getId().longValue());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());

    }
}