package io.github.nagol2003.dimension;

import io.github.nagol2003.AddonConfig;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;

public class AddonDimensions {

	public static DimensionType dimPlanetOne;
	public static DimensionType dimPlanetTwo;
	public static DimensionType dimMoonPlanetOne;
	public static DimensionType dimSpaceStationPlanetTwo;

	public static void init() {

		AddonDimensions.dimPlanetOne = WorldUtil.getDimensionTypeById(AddonConfig.addon_dimension.idPlanetOne);
		AddonDimensions.dimPlanetTwo = WorldUtil.getDimensionTypeById(AddonConfig.addon_dimension.idPlanetTwo);
		AddonDimensions.dimMoonPlanetOne = WorldUtil.getDimensionTypeById(AddonConfig.addon_dimension.idMoon);
		AddonDimensions.dimSpaceStationPlanetTwo = WorldUtil.getDimensionTypeById(AddonConfig.addon_dimension.idSpaceStation);

	}
}
