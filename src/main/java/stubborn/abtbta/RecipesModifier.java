package stubborn.abtbta;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShaped;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import stubborn.abtbta.config.ABTBTAOptions;
import stubborn.abtbta.helper.RecipeModifier;
import turniplabs.halplibe.helper.recipeBuilders.modifiers.WorkbenchModifier;

import java.util.Set;

public class RecipesModifier {
	private RecipesModifier() {}

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

		/*RecipeModifier.shaped(
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
		);*/

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

		modify();

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

	private static final String FABRIC_GROUP = "abtbta:fabric";

	private static final Set<String> TARGET_IDS = Set.of(
		Blocks.WOOL.getKey(),
		Items.CLOTH.getKey()
	);

	private static void modify() {
		//int replacements = 0;

		for (var entry : Registries.RECIPES.getAllRecipes()) {

			if (!(entry instanceof RecipeEntryCraftingShaped recipe)) {
				continue;
			}

			RecipeSymbol[] inputs = recipe.getInput();

			for (int i = 0; i < inputs.length; i++) {
				RecipeSymbol original = inputs[i];

				if (original == null || original.getStack() == null || original.getItemGroup() != null) {
					continue;
				}

				ItemStack stack = original.getStack();

				if (!isTarget(stack)) {
					continue;
				}

				inputs[i] = new RecipeSymbol(original.getSymbol(),
					null,
					FABRIC_GROUP,
					original.getAmount()
				);

				//replacements++;
			}
		}

		//System.out.println("ABTBTA: Replaced " + replacements + " recipe ingredients");
	}

	private static boolean isTarget(ItemStack stack) {
		String id = stack.getItem().getKey();
		return TARGET_IDS.contains(id);
	}
}
