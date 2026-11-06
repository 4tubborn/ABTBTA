package stubborn.abtbta;

import net.minecraft.core.item.Items;
import stubborn.abtbta.config.ABTBTAOptions;
import stubborn.abtbta.helper.RecipeModifier;

public class RecipesModifier {
	public static void register() {
		/*RecipeModifier.shaped(ABTBTAOptions.abtbta$overrideRecipes.value)
			.setShape(
				"WWW",
				"PPP"
			)
			.addInput('W', "minecraft:wools")
			.addInput('P', "minecraft:planks")
			.create(
				"bed",
				Items.BED.getDefaultStack()
			);*/

		RecipeModifier.shaped(
			ABTBTAOptions.OVERRIDE_RECIPES.value,
			"minecraft",
			"bed",
			Items.BED.getDefaultStack(),
			builder -> builder
				.setShape(
				"WWW",
				"PPP"
				)
				.addInput('W', "minecraft:wools")
				.addInput('P', "minecraft:planks")
		);

		/*
			RecipeBuilder.Shaped(NamespaceID.DEFAULT_NAMESPACE)
				.setShape(
					"WWW",
					"PPP"
				)
				.addInput('W', "minecraft:wools")
				.addInput('P', "minecraft:planks")
				.create(
					"retro_bed",
					Items.BED.getDefaultStack()
				);
		*/
	}
}
