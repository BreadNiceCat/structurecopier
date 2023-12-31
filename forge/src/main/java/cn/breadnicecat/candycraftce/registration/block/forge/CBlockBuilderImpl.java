package cn.breadnicecat.candycraftce.registration.block.forge;

import cn.breadnicecat.candycraftce.forge.CandyCraftCEImpl;
import cn.breadnicecat.candycraftce.registration.block.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static cn.breadnicecat.candycraftce.utils.CommonUtils.assertTrue;

/**
 * Created in 2023/12/10 10:34
 * Project: candycraftce
 *
 * @author <a href="https://github.com/BreadNiceCat">Bread_NiceCat</a>
 * <p>
 */
public class CBlockBuilderImpl {
	public static final DeferredRegister<Block> REGISTER = CandyCraftCEImpl.createRegister(ForgeRegistries.BLOCKS);

	public static <B extends Block> BlockEntry<B> register(@NotNull ResourceLocation name, Supplier<B> sup) {
		RegistryObject<B> object = REGISTER.register(name.getPath(), sup);
		assertTrue(name.equals(object.getId()), "Unmatched ResourceLocation");
		return new BlockEntry<>(name) {
			@Override
			public B getBlock() {
				return object.get();
			}
		};
	}
}
