package stubborn.abtbta;

import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.util.helper.DamageType;

import java.util.HashMap;
import java.util.Map;

public class ArmorMaterialsModifier {

	private static final Map<ArmorMaterial, Map<DamageType, Float>> INITIAL_ARMOR_MATERIALS = new HashMap<>();

	public static void modify(boolean restore) {
		if (restore) {
			restore();
		} else {
			set();
		}
	}

	private static void set(){
		unify(ArmorMaterial.LEATHER, 28.0F);
		unify(ArmorMaterial.CHAINMAIL, 48.0F);
		unify(ArmorMaterial.IRON, 60.0F);
		unify(ArmorMaterial.GOLD, 44.0F);
		unify(ArmorMaterial.DIAMOND, 80.0F);
		unify(ArmorMaterial.STEEL, 70.0F);
	}

	public static void captureInitialArmorMaterials() {

		for (ArmorMaterial material : ArmorMaterial.getArmorMaterials()) {
			Map<DamageType, Float> values = new HashMap<>();

			for (DamageType type : DamageType.values()) {
				values.put(type, material.getProtection(type));
			}

			INITIAL_ARMOR_MATERIALS.put(material, values);
		}
	}

	private static void restore() {
		INITIAL_ARMOR_MATERIALS.forEach((material, values) -> {
			values.forEach((type, protection) -> {
				// *100.0F may cause inaccuracy
				material.withProtectionPercentage(type, protection * 100.0F);
			});
		});
	}

	private static void unify(ArmorMaterial material, float percentage) {
		material.withProtectionPercentage(DamageType.COMBAT, percentage);
		material.withProtectionPercentage(DamageType.BLAST, percentage);
		material.withProtectionPercentage(DamageType.FIRE, percentage);
		material.withProtectionPercentage(DamageType.FALL, percentage * 0.6F);
	}
}
