package net.kaupenjoe.tutorialmod.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Arrow;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.compat.rei.category.GemPolishingREICategory;
import net.kaupenjoe.tutorialmod.compat.rei.displays.GemPolishingREIDisplay;
import net.kaupenjoe.tutorialmod.recipe.GemPolishingRecipe;
import net.kaupenjoe.tutorialmod.screen.GemPolishingStationScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

import java.util.List;

@REIPluginClient
public class REIPlugin implements REIClientPlugin {

    public static final int SLOT_SIZE = 19;
    public REIPlugin() {
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new GemPolishingREICategory());
        registry.addWorkstations(REICategoryIdentifiers.GEM_POLISHING_REI_DISPLAY_CATEGORY_IDENTIFIER, EntryStacks.of(ModBlocks.GEM_POLISHING_STATION.get()));


    }

    @Override
    public void registerDisplays(DisplayRegistry helper) {
        helper.registerRecipeFiller(GemPolishingRecipe.class, GemPolishingRecipe.Type.INSTANCE, GemPolishingREIDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(53, 30, 40, 10), GemPolishingStationScreen.class, GemPolishingREIDisplay.ID);
    }
    /**
     * Creates an input slot at a specified point with the specified ingredient index.
     *
     * @param display the display to get recipe data from.
     * @param index the index of the input ingredient to display.
     * @param x the x-coordinate of the top left corner of the slot.
     * @param y the y-coordinate of the top left corner of the slot.
     * @return a widget object to be added to display widget list.
     */
    public static Slot createInputSlot(BasicDisplay display, int index, int x, int y) {
        if (index >= display.getInputEntries().size()) {
            return Widgets.createSlot(new Point(x, y));
        }
        EntryIngredient ingredient = display.getInputEntries().get(index);
        return Widgets.createSlot(new Point(x, y)).entries(ingredient).markInput();
    }

    /**
     * Creates an output slot at a specified point with the specified ingredient index.
     *
     * @param display the display to get recipe data from.
     * @param index the index of the output ingredient to display.
     * @param x the x-coordinate of the top left corner of the slot.
     * @param y the y-coordinate of the top left corner of the slot.
     * @return a widget object to be added to display widget list.
     */
    public static Slot createOutputSlot(BasicDisplay display, int index, int x, int y) {
        if (index >= display.getOutputEntries().size()) {
            return Widgets.createSlot(new Point(x, y));
        }
        EntryIngredient outputIngredient = display.getOutputEntries().get(index);
        return Widgets.createSlot(new Point(x, y)).entries(outputIngredient).disableBackground().markOutput();
    }

    /**
     * Creates an animated progress bar starting at point x,y.
     *
     * @param x the x-coordinate of the starting point.
     * @param y the y-coordinate of the starting point.
     * @return arrow widget object to be added to display widget list.
     */
    public static Arrow createAnimatedArrow(int x, int y) {
        return Widgets.createArrow(new Point(x, y)).animationDurationTicks(20*3);
    }
}