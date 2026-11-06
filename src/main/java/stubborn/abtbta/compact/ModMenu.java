package stubborn.abtbta.compact;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.Screen;
import stubborn.abtbta.ABTBTA;
import stubborn.abtbta.gui.OptionPage;

import java.util.function.Function;

public class ModMenu implements ModMenuApi {

	@Override
	public String getModId() {
		return ABTBTA.MOD_ID;
	}

	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return OptionPage::getOptionsPage;
	}
}
