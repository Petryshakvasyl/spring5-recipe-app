package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand result = new RecipeCommand();
        result.setId(source.getId());
        result.setDescription(source.getDescription());
        result.setCookTime(source.getCookTime());
        result.setPrepTime(source.getPrepTime());
        result.setDifficulty(source.getDifficulty());
        result.setDirections(source.getDirections());
        result.setImage(source.getImage());
        result.setServings(source.getServings());
        result.setSource(source.getSource());
        result.setUrl(source.getUrl());
        result.setNotes(notesConverter.convert(source.getNotes()));
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            result.setIngredients(source.getIngredients().stream()
                    .map(ingredientConverter::convert)
                    .collect(Collectors.toSet())
            );
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            result.setCategories(source.getCategories().stream()
                    .map(categoryConverter::convert)
                    .collect(Collectors.toSet())
            );
        }

        return result;
    }
}
