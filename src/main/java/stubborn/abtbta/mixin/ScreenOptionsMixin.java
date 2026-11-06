package stubborn.abtbta.mixin;

import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.options.ScreenOptions;
import net.minecraft.core.lang.I18n;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import stubborn.abtbta.config.ABTBTAOptions;

import java.util.List;

@Mixin(ScreenOptions.class)
public abstract class ScreenOptionsMixin extends Screen {

	@Inject(method = "render", at = @At("HEAD"))
	private void abtbta$updateDoneButton(
		int mouseX,
		int mouseY,
		float partialTicks,
		CallbackInfo ci
	) {
		List<ButtonElement> buttons = this.buttons;

		for (ButtonElement button : buttons) {
			if (button.id == 0) {
				button.displayString = ABTBTAOptions.requiresRestart() ?
					I18n.getInstance().translateKey("abtbta.gui.options.button.done.requires_restart") :
					I18n.getInstance().translateKey("gui.options.button.done");
				break;
			}
		}
	}
}
