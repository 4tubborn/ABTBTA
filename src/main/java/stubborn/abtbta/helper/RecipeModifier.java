
package stubborn.abtbta.helper;

import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.helper.recipeBuilders.modifiers.WorkbenchModifier;

import java.util.function.UnaryOperator;

public final class RecipeModifier {

	public static void shaped(
		boolean override,
		String namespace,
		String recipeId,
		ItemStack output,
		UnaryOperator<RecipeBuilderShaped> configure
	) {
		if (override) {
			new WorkbenchModifier(namespace).removeRecipe(recipeId);
		}

		configure.apply(
			RecipeBuilder.Shaped(namespace)
		).create("abtbta_"+recipeId, output);
	}
}
