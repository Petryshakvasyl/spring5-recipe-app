package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<RecipeCommand> getRecipes();

    RecipeCommand findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
