package stubborn.abtbta.mixin;

import net.minecraft.client.gui.hud.component.HudComponentArmorBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import stubborn.abtbta.config.ABTBTAOptions;

@Mixin(HudComponentArmorBar.class)
public abstract class HudComponentArmorBarMixin {

	@Inject(method = "isVisible", at = @At("HEAD"), cancellable = true)
	private void abtbta$hide(CallbackInfoReturnable<Boolean> cir) {
		if(ABTBTAOptions.LEGACY_ARMOR_BAR.value) {
			cir.setReturnValue(false);
		}
	}
}
