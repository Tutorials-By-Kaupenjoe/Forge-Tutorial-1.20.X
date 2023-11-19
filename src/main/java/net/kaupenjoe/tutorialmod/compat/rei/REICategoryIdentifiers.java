package net.kaupenjoe.tutorialmod.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.kaupenjoe.tutorialmod.compat.rei.displays.GemPolishingREIDisplay;
import net.kaupenjoe.tutorialmod.recipe.GemPolishingRecipe;

public class REICategoryIdentifiers {
    public static final CategoryIdentifier<GemPolishingREIDisplay> GEM_POLISHING_REI_DISPLAY_CATEGORY_IDENTIFIER = CategoryIdentifier.of(GemPolishingRecipe.Type.ID);

    private REICategoryIdentifiers() {

    }
}