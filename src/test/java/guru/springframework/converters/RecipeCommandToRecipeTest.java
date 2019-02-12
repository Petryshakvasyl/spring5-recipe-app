package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static guru.springframework.domain.Difficulty.EASY;
import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final long RECIPE_ID = 1L;
    public static final String SOURCE = "Source";
    public static final String URL = "http://simplerecipe.com";
    public static final String DESCRIPTION = "Description";
    public static final int COOK_TIME = 10;
    public static final int PREP_TIME = 10;
    public static final String DIRECTIONS = "directions";
    public static final long INGREDIENT_ID = 2L;
    public static final long CATEGORY_ID = 3L;
    public static final long NOTES_ID = 4L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(),
                new CategoryCommandToCategory());
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
    //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDifficulty(EASY);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDirections(DIRECTIONS);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setRecipeId(RECIPE_ID);
        recipeCommand.getIngredients().add(ingredientCommand);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);
        recipeCommand.getCategories().add(categoryCommand);

        //when
        Recipe recipe = converter.convert(recipeCommand);
        //then
        assertEquals(RECIPE_ID, recipe.getId().longValue());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(COOK_TIME, recipe.getCookTime().longValue());
        assertEquals(EASY, recipe.getDifficulty());
        assertEquals(PREP_TIME, recipe.getPrepTime().longValue());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(1, recipe.getIngredients().size());
        assertEquals(INGREDIENT_ID, recipe.getIngredients().iterator().next().getId().longValue());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(CATEGORY_ID, recipe.getCategories().iterator().next().getId().longValue());

    }
}