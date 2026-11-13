package stubborn.abtbta.gui;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.options.ScreenOptions;
import net.minecraft.client.gui.options.components.BooleanOptionComponent;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.core.block.Blocks;
import stubborn.abtbta.ArmorMaterialsModifier;
import stubborn.abtbta.config.ABTBTAOptions;


public class OptionPage {

	public static final OptionsPage abtbtaOptions = new OptionsPage("options.abtbta.title", Blocks.GRASS_RETRO.getDefaultStack())
		.withComponent(new OptionsCategory("options.abtbta.category.general")
			.withComponent(new BooleanOptionComponent(ABTBTAOptions.OVERRIDE_RECIPES))
			.withComponent(new BooleanOptionComponent(ABTBTAOptions.LEGACY_ARMOR_PROTECTION.addOnChangeCallback((minecraft, option) -> {
				ArmorMaterialsModifier.modify(!option.value);
			})))
			.withComponent(new BooleanOptionComponent(ABTBTAOptions.LEGACY_ARMOR_BAR))
		);

	public static void register() {
		OptionsPages.register(abtbtaOptions);
	}
	public static Screen getOptionsPage(Screen parent) {
		return new ScreenOptions(parent, abtbtaOptions);
	}
}
