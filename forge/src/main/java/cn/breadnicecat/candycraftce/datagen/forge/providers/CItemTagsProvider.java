package cn.breadnicecat.candycraftce.datagen.forge.providers;

import cn.breadnicecat.candycraftce.CandyCraftCE;
import cn.breadnicecat.candycraftce.registration.item.CItemTags;
import cn.breadnicecat.candycraftce.registration.item.ItemEntry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static cn.breadnicecat.candycraftce.registration.item.CItemTags.CANDY_ARROWS;
import static cn.breadnicecat.candycraftce.registration.item.CItems.*;
import static cn.breadnicecat.candycraftce.utils.CommonUtils.receive;
import static net.minecraft.tags.ItemTags.*;

/**
 * Created in 2023/9/9 21:39
 * Project: candycraftce
 *
 * @author <a href="https://github.com/BreadNiceCat">Bread_NiceCat</a>
 */
public class CItemTagsProvider extends ItemTagsProvider {
	public CItemTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2, @Nullable ExistingFileHelper existingFileHelper) {
		super(arg, completableFuture, completableFuture2, CandyCraftCE.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider arg) {
		add(MUSIC_DISCS, RECORD_1, RECORD_2, RECORD_3, RECORD_4, RECORD_WWWOOOWWW);
		add(CItemTags.HONEYCOMB, HONEYCOMB);
		add(CItemTags.LICORICE, LICORICE);
		add(CItemTags.PEZ, PEZ);
		add(CItemTags.EMBLEM,
				GINGERBREAD_EMBLEM,
				JELLY_EMBLEM,
				SKY_EMBLEM,
				CHEWING_GUM_EMBLEM,
				HONEYCOMB_EMBLEM,
				CRANBERRY_EMBLEM,
				NESSIE_EMBLEM,
				SUGUARD_EMBLEM
		);
		add(CANDY_ARROWS, HONEYCOMB_ARROW);
		add(SWORDS, MARSHMALLOW_SWORD, LICORICE_SWORD, HONEYCOMB_SWORD, PEZ_SWORD);
		add(HOES, MARSHMALLOW_HOE, LICORICE_HOE, HONEYCOMB_HOE, PEZ_HOE);
		add(AXES, MARSHMALLOW_AXE, LICORICE_AXE, HONEYCOMB_AXE, PEZ_AXE);
		add(PICKAXES, MARSHMALLOW_PICKAXE, LICORICE_PICKAXE, HONEYCOMB_PICKAXE, PEZ_PICKAXE);
		add(SHOVELS, MARSHMALLOW_SHOVEL, LICORICE_SHOVEL, HONEYCOMB_SHOVEL, PEZ_SHOVEL);
	}

	private void add(TagKey<Item> tagKey, ItemEntry<?>... ie) {
		IntrinsicTagAppender<Item> tag = tag(tagKey);
		receive(i -> tag.add(i.getItem()), ie);
	}

}
