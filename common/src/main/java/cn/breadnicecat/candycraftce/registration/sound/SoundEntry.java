package cn.breadnicecat.candycraftce.registration.sound;

import cn.breadnicecat.candycraftce.registration.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

/**
 * Created in 2023/10/14 19:00
 * Project: candycraftce
 *
 * @author <a href="https://github.com/BreadNiceCat">Bread_NiceCat</a>
 * <p>
 */
public class SoundEntry extends RegistryEntry {
	private final Supplier<SoundEvent> entry;

	public SoundEntry(ResourceLocation name, Supplier<SoundEvent> entry) {
		super(name);
		this.entry = entry;
	}

	public SoundEvent getSound() {
		return entry.get();
	}
}
