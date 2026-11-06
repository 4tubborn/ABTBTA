package stubborn.abtbta;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stubborn.abtbta.config.ABTBTAOptions;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class ABTBTA implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("abtbta", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		//CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.RECIPES_READY.listen(
			Key.of(MOD_ID), this::onRecipesReady
		);

		CommonEvents.AFTER_GAME_START.listen(
			Key.of(MOD_ID), this::onAfterGameStart
		);
		// need ensure initOptions executes before onInitialize

		LOGGER.info("ABTBTA initialized.");
	}

	private void onRecipesReady() {
		RecipesModifier.register();
		LOGGER.info("RecipesModifier modified.");
	}

	private void onAfterGameStart() {
		ArmorMaterialsModifier.captureInitialArmorMaterials();
		ArmorMaterialsModifier.modify(ABTBTAOptions.LEGACY_ARMOR_PROTECTION.value);
	}
}
