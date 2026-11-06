package stubborn.abtbta;

import net.fabricmc.api.ClientModInitializer;
import stubborn.abtbta.config.ABTBTAOptions;
import stubborn.abtbta.gui.OptionPage;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.OptionsInitEntrypoint;

import turniplabs.halplibe.util.dependency.Key;

public class ABTBTAClient  implements ClientModInitializer, OptionsInitEntrypoint {
	@Override
	public void onInitializeClient() {
		ClientEvents.AFTER_CLIENT_START.listen(
			Key.of(ABTBTA.MOD_ID),
			this::onAfterClientStart
		);
	}

	private void onAfterClientStart() {
		OptionPage.register();
		ABTBTAOptions.captureInitialValues();
		// GameSettingsModifier.set(ABTBTAOptions.abtbta$hideKeyHotbarSwitch.value);
	}

	@Override
	public void initOptions() {
		ABTBTAOptions.register();
		//ABTBTAOptions.captureInitialValues();
	}
}
