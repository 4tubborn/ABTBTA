package stubborn.abtbta.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.logging.CategorizedLogger;
import net.minecraft.client.gui.hud.component.ComponentAnchor;
import net.minecraft.client.gui.hud.component.HudComponents;
import net.minecraft.client.gui.hud.component.layout.LayoutSnap;
import net.minecraft.client.gui.options.components.BooleanOptionComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import stubborn.abtbta.config.ABTBTAOptions;
import stubborn.abtbta.gui.hud.component.HudComponentLegacyArmorBar;

@Mixin(HudComponents.class)
public class HudComponentsMixin {

	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void abtbta$registerArmor(CallbackInfo ci) {
		HudComponents.register(
			new HudComponentLegacyArmorBar(
				"abtbta:legacyArmor",
				new LayoutSnap(
					HudComponents.HOTBAR,
					ComponentAnchor.TOP_RIGHT, ComponentAnchor.BOTTOM_RIGHT,
					0, -1
				)
			).addAttachedOption(
					ABTBTAOptions.VERTICAL_ARMOR_BAR,
					() -> new BooleanOptionComponent(
						ABTBTAOptions.VERTICAL_ARMOR_BAR
					)
				)
				.addAttachedOption(
					ABTBTAOptions.FLIP_ARMOR_BAR,
					() -> new BooleanOptionComponent(
						ABTBTAOptions.FLIP_ARMOR_BAR
					)
				)
		);
	}
}
