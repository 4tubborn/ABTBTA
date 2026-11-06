package stubborn.abtbta.mixin.activator;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.block.entity.TileEntityActivator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityActivator.class)
public class TileEntityActivatorMixin {

	//TileEntityDispenser dispenser;
	// disable locked slots
	@ModifyExpressionValue(
		method = "readAdditionalData",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/nbt/tags/CompoundTag;getShort(Ljava/lang/String;)S"
		)
	)
	private short abtbta$unlockAllSlots(short original) {
		return 0;
	}

	@Inject(method = "locked", at = @At("HEAD"), cancellable = true)
	private void abtbta$disableLocking(
		int slot,
		CallbackInfoReturnable<Boolean> cir
	) {
		cir.setReturnValue(false);
	}
}
