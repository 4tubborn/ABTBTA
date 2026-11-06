package stubborn.abtbta.mixin.activator;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.container.ScreenActivator;
import net.minecraft.client.gui.container.ScreenDispenser;
import net.minecraft.core.block.BlockLogicActivator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ScreenActivator.class)
public class ScreenActivatorMixin {

	//ScreenDispenser screenDispenser;
	@ModifyExpressionValue(
		method = "drawGuiContainerBackgroundLayer",
		at = @At(
			value = "CONSTANT",
			args = "stringValue=/assets/minecraft/textures/gui/container/activator.png"
		)
	)
	private String abtbta$replaceTexture(String original) {
		return "/assets/abtbta/textures/gui/container/activator.png";
	}
	// let upper bound be 0, skip the whole for loop of creating lock buttons
	@ModifyExpressionValue(
		method = "init",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/block/entity/TileEntityActivator;getContainerSize()I"
		)
	)
	private int abtbta$disableSlotButtons(int original) {
		return 0;
	}
}
