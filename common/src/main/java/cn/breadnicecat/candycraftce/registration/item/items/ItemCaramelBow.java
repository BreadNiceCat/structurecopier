package cn.breadnicecat.candycraftce.registration.item.items;

import cn.breadnicecat.candycraftce.registration.item.CItemTags;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * Created in 2023/10/29 8:40
 * Project: candycraftce
 *
 * @author <a href="https://github.com/BreadNiceCat">Bread_NiceCat</a>
 * <p>
 */
public class ItemCaramelBow extends BowItem {

	public static final Predicate<ItemStack> CANDY_ARROW_ONLY = itemStack -> itemStack.is(CItemTags.CANDY_ARROWS);

	public ItemCaramelBow(Properties properties) {
		super(properties);
	}

	@Override
	public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
		return CANDY_ARROW_ONLY;
	}
}
