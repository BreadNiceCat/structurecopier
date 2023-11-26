package cn.breadnicecat.candycraftce;

import cn.breadnicecat.candycraftce.registration.item.CItems;
import cn.breadnicecat.candycraftce.registration.sound.CSoundEvents;
import cn.breadnicecat.candycraftce.utils.CLogUtils;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.util.profiling.jfr.Environment;
import org.slf4j.Logger;

import java.io.File;
import java.util.LinkedList;
import java.util.function.BooleanSupplier;

import static cn.breadnicecat.candycraftce.utils.CLogUtils.getModLogger;

public class CandyCraftCE {
	public static final String MOD_ID = "candycraftce";
	public static final String MOD_NAME = "CandyCraft CE";

	private static final Logger LOGGER = getModLogger();

	private static final boolean inDev = new File(new File("").getAbsoluteFile().getParentFile(), "src").exists();

	static {
		CLogUtils.sign();
		LOGGER.info("=".repeat(50));
		LOGGER.info(MOD_ID + " Running in " + getEnvironment() + " with " + getPlatform());
		if (inDev) LOGGER.info("Here running IDE mode!If you're not a developer, Please report the issue!");
		LOGGER.info("=".repeat(50));
	}

	private static LinkedList<Runnable> bootstrapHooks = new LinkedList<>();

	private static boolean postBootstrap = false;
	private static boolean preBootstrap = false;


	/**
	 * bootstrap here
	 */
	public CandyCraftCE() {
		if (isBootstrapped()) {
			throw new IllegalStateException("bootstrapped!");
		}
		preBootstrap = true;

		CItems.init();//->CBlocks
		CSoundEvents.init();
//		CBlocks.init();

		bootstrapHooks.forEach(Runnable::run);
		bootstrapHooks = null;

		preBootstrap = false;
		postBootstrap = true;
	}

	public static void hookPostBootstrap(Runnable runnable, BooleanSupplier... predicate) {
		bootstrapHooks.add(predicate.length == 0 ? runnable : () -> {
			for (BooleanSupplier supplier : predicate) {
				if (!supplier.getAsBoolean()) return;
			}
			runnable.run();
		});
	}

	/**
	 * Forge: FMLCommonSetupEvent
	 * Fabric 委托给{@link #hookPostBootstrap}
	 */
	@ExpectPlatform
	public static void hookMinecraftSetup(Runnable runnable, BooleanSupplier... predicate) {
		throw new AssertionError();
	}

	/**
	 * 如果是在开发环境里，返回空的Runnable
	 */
	public static Runnable devImmune(Runnable runnable) {
		return isInDev() ? () -> {
		} : runnable;
	}


	/**
	 * @return 完成bootstrap
	 */
	public static boolean isBootstrapped() {
		return postBootstrap;
	}

	/**
	 * @return 在bootstrap中
	 */
	public static boolean isBootstrapping() {
		return preBootstrap && !postBootstrap;
	}


	public static boolean isInDev() {
		return inDev;
	}

	@ExpectPlatform
	public static Environment getEnvironment() {
		throw new AssertionError();
	}

	public static boolean isClient() {
		return getEnvironment() == Environment.CLIENT;
	}

	/**
	 * @deprecated 应当淡化平台理念
	 */
	@Deprecated
	@ExpectPlatform
	public static ModPlatform getPlatform() {
		throw new AssertionError();
	}

	public enum ModPlatform {
		FORGE, FABRIC, QUILT//Quilt兼容Fabric,不单独开发
	}
}
