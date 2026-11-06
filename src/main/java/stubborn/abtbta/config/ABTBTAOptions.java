package stubborn.abtbta.config;

import net.minecraft.client.option.*;

import java.util.List;

public class ABTBTAOptions {

	private static boolean initialOverrideRecipes = false;

	private ABTBTAOptions() {}

	public static final OptionBoolean OVERRIDE_RECIPES = new OptionBoolean("abtbta.overrideRecipes", false);
	public static final OptionBoolean LEGACY_ARMOR_PROTECTION = new OptionBoolean("abtbta.legacyArmorProtection", true);
	public static final OptionBoolean VERTICAL_ARMOR_BAR  = new OptionBoolean("abtbta.verticalArmorBar", false);
	public static final OptionBoolean FLIP_ARMOR_BAR  = new OptionBoolean("abtbta.flipArmorBar", false);
	public static final OptionBoolean LEGACY_ARMOR_BAR  = new OptionBoolean("abtbta.legacyArmorBar", true);

	private static final List<Option<?>> OPTIONS = List.of(
		OVERRIDE_RECIPES,
		LEGACY_ARMOR_PROTECTION,
		LEGACY_ARMOR_BAR,
		VERTICAL_ARMOR_BAR,
		FLIP_ARMOR_BAR
	);

	public static void register() {
		OPTIONS.forEach(GameSettings::register);
	}

	public static void captureInitialValues() {
		initialOverrideRecipes = OVERRIDE_RECIPES.value;
		//System.out.println("[ABTBTA] Initial overrideRecipes = " + initialOverrideRecipes);
	}

	public static boolean requiresRestart() {
		return
			initialOverrideRecipes != OVERRIDE_RECIPES.value
			;
	}
}
