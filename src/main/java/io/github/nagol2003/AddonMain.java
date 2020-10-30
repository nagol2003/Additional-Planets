package io.github.nagol2003;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.nagol2003.dimension.AddonDimensions;
import io.github.nagol2003.planets.AddonCelestialBodies;
import io.github.nagol2003.proxy.Proxy;
import io.github.nagol2003.util.Utils;

@Mod(
		modid = Const.modID,
		name = Const.modName,
		version = Const.modVersion,
		dependencies = Const.DEPENDENCIES_FORGE + Const.DEPENDENCIES_MODS,
		certificateFingerprint = Const.CERTIFICATEFINGERPRINT)
public class AddonMain {

	public static final Logger LOGGER   = LogManager.getLogger(Const.modID);
	public static AddonMain    INSTANCE = new AddonMain();

	@SidedProxy(
			clientSide = "io.github.nagol2003.proxy.ClientProxy",
			serverSide = "io.github.nagol2003.proxy.ServerProxy")
	private static Proxy proxy;

	@EventHandler
	public static void onFingerprintViolation (final FMLFingerprintViolationEvent event) {
		if (!Utils.developerEnvironment) {
			// This complains if jar not signed, even if certificateFingerprint is blank
			// But only when not in our Development Environment
			LOGGER.warn("Invalid Fingerprint");
		}
	}

	@EventHandler
	public static void preInit (final FMLPreInitializationEvent event) {

		// call this in the preInit (MAKE SURE YOU REGISTER ANY BLOCKS OR ITEMS FIRST)
		AddonCelestialBodies.init();

		proxy.preInit(event);
	}

	@EventHandler
	public static void init (final FMLInitializationEvent event) {
		proxy.init(event);

	}

	@EventHandler
	public static void receiveIMC (final IMCEvent event) {
		proxy.receiveIMC(event);
	}

	@EventHandler
	public static void postInit (final FMLPostInitializationEvent event) {
		// Register addons dimensions used by planets/moonds/etc.. in postInit
		AddonDimensions.init();
		proxy.postInit(event);
	}

	public static World getWorld () {
		return proxy.getWorld();
	}
}
