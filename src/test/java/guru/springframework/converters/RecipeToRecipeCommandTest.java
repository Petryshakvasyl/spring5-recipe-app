package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static guru.springframework.domain.Difficulty.EASY;
import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
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
    RecipeToRecipeCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand());
    }
    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }
    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDescription(DESCRIPTION);
        recipe.setCookTime(COOK_TIME);
        recipe.setDifficulty(EASY);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDirections(DIRECTIONS);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setRecipe(recipe);
        recipe.getIngredients().add(ingredient);

        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.getRecipes().add(recipe);
        recipe.getCategories().add(category);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertEquals(RECIPE_ID, recipeCommand.getId().longValue());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(COOK_TIME, recipeCommand.getCookTime().longValue());
        assertEquals(EASY, recipeCommand.getDifficulty());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime().longValue());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId().longValue());

        assertEquals(1, recipeCommand.getCategories().size());
        CategoryCommand categoryCommand = recipeCommand.getCategories().iterator().next();
        assertEquals(CATEGORY_ID, categoryCommand.getId().longValue());

        assertEquals(1, recipeCommand.getIngredients().size());
        IngredientCommand ingredientCommand = recipeCommand.getIngredients().iterator().next();
        assertEquals(INGREDIENT_ID, ingredientCommand.getId().longValue());
        assertEquals(RECIPE_ID, ingredientCommand.getRecipeId().longValue());

    }
}