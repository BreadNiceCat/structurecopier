package cn.breadnicecat.candycraftce.registration.item.forge;

import cn.breadnicecat.candycraftce.forge.CandyCraftCEImpl;
import cn.breadnicecat.candycraftce.registration.item.ItemEntry;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static cn.breadnicecat.candycraftce.utils.CommonUtils.assertTrue;
import static net.minecraft.world.item.CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

/**
 * Created in 2023/10/28 23:46
 * Project: candycraftce
 *
 * @author <a href="https://github.com/BreadNiceCat">Bread_NiceCat</a>
 * <p>
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CItemBuilderImpl {
	public static final DeferredRegister<Item> REGISTER = CandyCraftCEImpl.createRegister(ForgeRegistries.ITEMS);
	public static final Map<Pair<ResourceKey<CreativeModeTab>, Supplier<ItemStack>>, Either<Supplier<ItemStack>, Supplier<ItemStack>>> EX_TABS = new HashMap<>();

	public static <I extends Item> ItemEntry<I> register(ResourceLocation name, Supplier<I> sup) {

		RegistryObject<I> object = REGISTER.register(name.getPath(), sup);
		assertTrue(name.equals(object.getId()), "Unmatched ResourceLocation");
		return new ItemEntry<>(name) {
			@Override
			public I getItem() {
				return object.get();
			}
		};
	}

	public static void registerTabEntry(Pair<ResourceKey<CreativeModeTab>, Supplier<ItemStack>> tab, @Nullable Either<Supplier<ItemStack>, Supplier<ItemStack>> pos) {
		EX_TABS.put(tab, pos);
	}

	@SubscribeEvent
	public static void onModifyTab(BuildCreativeModeTabContentsEvent evt) {
		if (!evt.hasPermissions()) {
			return;
		}
		EX_TABS.entrySet().stream()
				.filter(entry -> evt.getTabKey() == entry.getKey().getFirst())
				.forEach(entry -> {
					Either<ItemStack, ItemStack> pos = entry.getValue().mapBoth(Supplier::get, Supplier::get);
					ItemStack itemStack = entry.getKey().getSecond().get();
					var entries = evt.getEntries();
					pos.ifLeft((k) -> {
								if (k == ItemStack.EMPTY) {
									entries.put(itemStack, PARENT_AND_SEARCH_TABS);
								} else {
									entries.putBefore(k, itemStack, PARENT_AND_SEARCH_TABS);
								}
							})
							.ifRight((k) -> {
								if (k == ItemStack.EMPTY) {
									entries.putFirst(itemStack, PARENT_AND_SEARCH_TABS);
								} else {
									entries.putAfter(k, itemStack, PARENT_AND_SEARCH_TABS);
								}
							});
				});
	}

}
