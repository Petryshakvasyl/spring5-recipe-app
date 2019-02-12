package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    public static final long ID_VALUE = 1L;
    public static final long UOM_ID = 2L;
    public static final String DESCRIPTION = "Description";
    public static final String UOM_DESCRIPTION = "Spoon";
    public static final BigDecimal AMOUNT = new BigDecimal(12);
    public static final long RECIPE_ID = 3L;

    IngredientToIngredientCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullParameter0() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(UOM_DESCRIPTION);
        uom.setId(UOM_ID);
        ingredient.setUom(uom);
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        ingredient.setRecipe(recipe);
        recipe.addIngredient(ingredient);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId().longValue());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());

        assertEquals(UOM_ID, ingredientCommand.getUom().getId().longValue());
        assertEquals(UOM_DESCRIPTION, ingredientCommand.getUom().getDescription());
        assertEquals(RECIPE_ID, ingredientCommand.getRecipeId().longValue());
    }
}