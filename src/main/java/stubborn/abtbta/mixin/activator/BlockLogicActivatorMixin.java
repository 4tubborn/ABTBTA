package stubborn.abtbta.mixin.activator;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.container.ScreenCrafting;
import net.minecraft.core.block.BlockLogicActivator;
import net.minecraft.core.block.BlockLogicDispenser;
import net.minecraft.core.block.BlockLogicWorkbench;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(BlockLogicActivator.class)
public class BlockLogicActivatorMixin {

	//ScreenCrafting

	// randomly use
	@WrapOperation(
		method = "useItem",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/block/entity/TileEntityActivator;getNextStack()Lnet/minecraft/core/item/ItemStack;"
		)
	)
	private ItemStack abtbta$randomItem(
		TileEntityActivator activator,
		Operation<ItemStack> original,
		World world,
		TilePosc tilePos,
		Random random
	)  {
		ItemStack selected = null;
		int count = 0;

		for (int i = 0; i < TileEntityActivator.CONTAINER_SIZE; i++) {
			ItemStack stack = activator.getItem(i);

			if (stack != null && stack.stackSize > 0) {
				if (random.nextInt(++count) == 0) {
					selected = stack;
				}
			}
		}

		return selected;
	}
	// always try to use items and also skip  activator.shiftSelector();
	@ModifyExpressionValue(
		method = "useItem",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/block/entity/TileEntityActivator;locked(I)Z"
		)
	)
	private boolean abtbta$disableLockedSlots(boolean original) {
		return false;
	}
}
