package stubborn.abtbta.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.core.entity.IArmorWearing;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import stubborn.abtbta.config.ABTBTAOptions;

@Mixin(IArmorWearing.class)
public interface IArmorWearingMixin {
	// mimic Beta 1.7.3's armor protection system,
	// where damage reduction depends on the armor's remaining durability.
	@WrapOperation(
		method = "getTotalProtectionAmount",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/item/material/ArmorMaterial;getProtection(Lnet/minecraft/core/util/helper/DamageType;)F"
		)
	)
	private float abtbta$modifyProtection(
		ArmorMaterial material,
		DamageType damageType,
		Operation<Float> original,
		@Local(name = "armorItem") ItemStack armorItem,
		@Share("maxDurability") LocalIntRef maxDurability,
		@Share("totalDamage") LocalIntRef totalDamage
	) {
		if(!ABTBTAOptions.LEGACY_ARMOR_PROTECTION.value) return original.call(material, damageType);
		int max = armorItem.getMaxDamage();

		if (max > 0) {
			// total_max+=cur
			maxDurability.set(maxDurability.get() + max);
			totalDamage.set(totalDamage.get() + Math.min(
				Math.max(armorItem.getItemDamageForDisplay(), 0),
				max
			));
		}

		return original.call(material, damageType);
	}

	@ModifyReturnValue(
		method = "getTotalProtectionAmount",
		at = @At("RETURN")
	)
	private float abtbta$modifyProtection2(
		float original,
		@Share("maxDurability") LocalIntRef maxDurability,
		@Share("totalDamage") LocalIntRef totalDamage
	) {
		if(!ABTBTAOptions.LEGACY_ARMOR_PROTECTION.value) return original;

		int max = maxDurability.get();

		if (max == 0 || original <= 0.0F) {
			return 0.0F;
		}

		float remaining = (float) (max - totalDamage.get()) / max;

		return (original - 0.04F) * remaining + 0.04F;
	}
}
