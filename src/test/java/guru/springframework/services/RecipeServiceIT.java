package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New description";
    @Autowired
    private RecipeService recipeService;

    @Test
    @Transactional
    public void saveRecipeCommand() {
        RecipeCommand command = new RecipeCommand();
        command.setDescription(NEW_DESCRIPTION);

        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);

        assertEquals(NEW_DESCRIPTION, savedRecipe.getDescription());
        assertNotNull(savedRecipe.getId());
    }
}