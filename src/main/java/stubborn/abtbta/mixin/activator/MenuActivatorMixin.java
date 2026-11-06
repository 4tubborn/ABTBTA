package stubborn.abtbta.mixin.activator;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.player.inventory.menu.MenuActivator;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MenuActivator.class)
public class MenuActivatorMixin {
	/*
	0 1 2
	3 4 5
	6 7 8
	 */
	@ModifyExpressionValue(
		method = "<init>",
		at = @At(
			value = "NEW",
			target = "Lnet/minecraft/core/player/inventory/slot/Slot;",
			ordinal = 0
		)
	)
	private Slot abtbta$modifyActivatorSlot(Slot original, @Local(name = "i") int i) {

		original.x = 62 + (i % 3) * 18;
		original.y = 17 + (i / 3) * 18;

		return original;
	}
}
