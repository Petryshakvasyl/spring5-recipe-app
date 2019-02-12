package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand ,Category > {
    @Nullable
    @Synchronized
    @Override
    public Category convert(CategoryCommand source) {
        if(source == null){
            return null;
        }
        final Category result = new Category();
        result.setId(source.getId());
        result.setDescription(source.getDescription());

        return result;
    }
}
