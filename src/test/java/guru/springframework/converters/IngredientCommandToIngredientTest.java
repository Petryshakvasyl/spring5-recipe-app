package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    public static final long ID_VALUE = 1L;
    public static final long UOM_ID = 2L;
    public static final String DESCRIPTION = "Description";
    public static final String UOM_DESCRIPTION = "Spoon";
    public static final BigDecimal AMOUNT = new BigDecimal(12);
    public static final long RECIPE_ID = 3L;
    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convertWhitnNullUoM() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);
        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId().longValue());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }

    @Test
    public void convert() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);
        ingredientCommand.setUom(uom);
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setRecipeId(RECIPE_ID);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);
        //then
        assertNotNull(ingredient.getRecipe());
        assertEquals(RECIPE_ID, ingredient.getRecipe().getId().longValue());
        assertEquals(ID_VALUE, ingredient.getId().longValue());
        assertEquals(UOM_ID, ingredient.getUom().getId().longValue());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_DESCRIPTION, ingredient.getUom().getDescription());
    }
}