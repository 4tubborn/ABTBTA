
package stubborn.abtbta.gui.hud.component;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.hud.HudIngame;
import net.minecraft.client.gui.hud.component.ComponentAnchor;
import net.minecraft.client.gui.hud.component.HudComponentMovable;
import net.minecraft.client.gui.hud.component.HudComponents;
import net.minecraft.client.gui.hud.component.layout.Layout;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.State;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.DamageType;
import stubborn.abtbta.config.ABTBTAOptions;

// Import DamageType from your BTA version.

@Environment(EnvType.CLIENT)
public class HudComponentLegacyArmorBar extends HudComponentMovable {

	// from bta asset, vertical mode icon is designed with Blockbench
	private final IconCoordinate armor =
		TextureRegistry.getTexture("abtbta:gui/hud/armor/full");
	private final IconCoordinate armorHalf =
		TextureRegistry.getTexture("abtbta:gui/hud/armor/half");
	private final IconCoordinate armorHalfFlipped =
		TextureRegistry.getTexture("abtbta:gui/hud/armor/half_flipped");
	private final IconCoordinate armorHalfVertical =
		TextureRegistry.getTexture("abtbta:gui/hud/armor/half_vertical");
	private final IconCoordinate armorHalfVerticalFlipped =
		TextureRegistry.getTexture("abtbta:gui/hud/armor/half_vertical_flipped");
	private final IconCoordinate armorEmpty =
		TextureRegistry.getTexture("abtbta:gui/hud/armor/empty");

	public HudComponentLegacyArmorBar(String key, Layout layout) {
		super(key, 81, 9, layout);
	}

	private boolean isVertical() {
		return ABTBTAOptions.VERTICAL_ARMOR_BAR.value;
	}

	private boolean isFlipped() {
		return ABTBTAOptions.FLIP_ARMOR_BAR.value;
	}

	@Override
	public int getDisplayedXSize() {
		return isVertical() ? super.getDisplayedYSize() : super.getDisplayedXSize();
	}

	@Override
	public int getDisplayedYSize() {
		return isVertical() ? super.getDisplayedXSize() : super.getDisplayedYSize();
	}

	@Override
	public int getTrueXSize() {
		return isVertical() ? super.getTrueYSize() : super.getTrueXSize();
	}

	@Override
	public int getTrueYSize() {
		return isVertical() ? super.getTrueXSize() : super.getTrueYSize();
	}

	@Override
	public boolean isVisible() {
		if (mc.thePlayer == null) {
			return GameSettings.IMMERSIVE_MODE.drawHotbar();
		}

		return ABTBTAOptions.LEGACY_ARMOR_BAR.value && GameSettings.IMMERSIVE_MODE.drawHotbar()
			&& !mc.thePlayer.getGamemode().hasInvulnerablePlayer()
			&& mc.thePlayer.getTotalProtectionAmount(DamageType.COMBAT) > 0.0F;
	}

	@Override
	public void render(
		HudIngame hud,
		int xSizeScreen,
		int ySizeScreen,
		float partialTick
	) {
		int x = getLayout().getComponentX(this, xSizeScreen);
		int y = getLayout().getComponentY(this, ySizeScreen);

		GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GLRenderer.enableState(State.BLEND);

		// use damage type of combat, but other damage types are missing
		// the mod has unified the damage protection except falling damage type.
		float protection = mc.thePlayer
			.getTotalProtectionAmount(DamageType.COMBAT);

		int armorValue = Math.round(
			Math.max(0.0F, Math.min(protection, 1.0F)) * 20.0F
		);

		int numFull = armorValue / 2;
		int numHalf = armorValue % 2;

		boolean vertical = isVertical();
		boolean flipped = isFlipped();

		IconCoordinate halfIcon = vertical
			? (flipped ? armorHalfVerticalFlipped : armorHalfVertical)
			: (flipped ? armorHalfFlipped : armorHalf);

		for (int i = 0; i < 10; ++i) {
			int xIcon;
			int yIcon;

			if (vertical) {
				xIcon = x;
				yIcon = flipped ? y + i * 8 : y + (9 - i) * 8;
			} else {
				xIcon = flipped ? x + (9 - i) * 8 : x + i * 8;
				yIcon = y;
			}

			hud.drawGuiIcon(
				xIcon,
				yIcon,
				9,
				9,
				i < numFull ? armor
					: i < numFull + numHalf ? halfIcon
					: armorEmpty
			);
		}
	}

	@Override
	public void renderPreview(
		Gui gui,
		Layout layout,
		int xSizeScreen,
		int ySizeScreen
	) {
		int x = layout.getComponentX(this, xSizeScreen);
		int y = layout.getComponentY(this, ySizeScreen);

		GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		renderTranslucency();
		GLRenderer.enableState(State.BLEND);

		boolean vertical = isVertical();
		boolean flipped = isFlipped();

		int numFull = 5;
		int numHalf = 1;

		IconCoordinate halfIcon = vertical
			? (flipped ? armorHalfVerticalFlipped : armorHalfVertical)
			: (flipped ? armorHalfFlipped : armorHalf);

		for (int i = 0; i < 10; ++i) {
			int xIcon;
			int yIcon;

			if (vertical) {
				xIcon = x;
				yIcon = flipped ? y + i * 8 : y + (9 - i) * 8;
			} else {
				xIcon = flipped ? x + (9 - i) * 8 : x + i * 8;
				yIcon = y;
			}

			gui.drawGuiIcon(
				xIcon,
				yIcon,
				9,
				9,
				i < numFull ? armor
					: i < numFull + numHalf ? halfIcon
					: armorEmpty
			);
		}
	}
}
