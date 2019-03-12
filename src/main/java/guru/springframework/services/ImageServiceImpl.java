package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile imageFile) {
        log.debug("saving image");
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            if (recipeOptional.isPresent()) {
                Byte[] imageBytes = wrapToByteArray(imageFile);
                Recipe recipe = recipeOptional.get();
                recipe.setImage(imageBytes);
                recipeRepository.save(recipe);
            } else {
                log.debug("not found recipe with id: " + recipeId);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private Byte[] wrapToByteArray(MultipartFile imageFile) throws IOException {
        Byte[] imageBytes = new Byte[imageFile.getBytes().length];
        int i = 0;
        for (Byte b : imageFile.getBytes()) {
            imageBytes[i++] = b;
        }
        return imageBytes;
    }
}
