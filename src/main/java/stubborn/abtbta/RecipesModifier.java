package stubborn.abtbta;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.Items;
import stubborn.abtbta.config.ABTBTAOptions;
import stubborn.abtbta.helper.RecipeModifier;
import turniplabs.halplibe.helper.recipeBuilders.modifiers.WorkbenchModifier;

public class RecipesModifier {

	public static void register() {

		boolean hide = ABTBTAOptions.OVERRIDE_RECIPES.value;
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
			hide,
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

		RecipeModifier.shaped(
			hide,
			"minecraft",
			"bread",
			Items.FOOD_BREAD.getDefaultStack(),
			builder -> builder
				.setShape(
					"WWW"
				)
				.addInput('W', Items.WHEAT)
		);

		RecipeModifier.shaped(
			hide,
			"minecraft",
			"cake",
			Items.FOOD_CAKE.getDefaultStack(),
			builder -> builder
				.setShape(
					"BBB",
					"SES",
					"WWW"
				)
				.addInput('B', "minecraft:milk_buckets")
				.addInput('S', Items.DUST_SUGAR)
				.addInput('E', Items.EGG_CHICKEN)
				.addInput('W', Items.WHEAT)
		);

		RecipeModifier.shaped(
			hide,
			"minecraft",
			"pumpkin_pie",
			Items.FOOD_PUMPKIN_PIE.getDefaultStack(),
			builder -> builder
				.setShape(
					"BBB",
					"SES",
					"WWW"
				)
				.addInput('B', "minecraft:milk_buckets")
				.addInput('S', Items.DUST_SUGAR)
				.addInput('E', Blocks.PUMPKIN)
				.addInput('W', Items.WHEAT)
		);

		RecipeModifier.shaped(
			hide,
			"minecraft",
			"pumpkin_pie",
			Items.FOOD_COOKIE.getDefaultStack(),
			builder -> builder
				.setShape(
					"WCW"
				)
				.addInput('W', Items.WHEAT)
				// Cocoa bean
				.addInput('C', Items.DYE, 3)
		);

		if(hide){
			new WorkbenchModifier("minecraft").removeRecipe("dough");
		}

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
