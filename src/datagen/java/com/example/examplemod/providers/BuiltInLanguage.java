package com.example.examplemod.providers;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.common.item.*;
import net.neoforged.neoforge.common.data.LanguageProvider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BuiltInLanguage extends LanguageProvider {

	public BuiltInLanguage(final PackOutput output) {
		super(output, ExampleMod.MOD_ID, "en_us");
	}

	/**
	 * Takes a string like 'dark_oak' and converts it to 'Dark Oak'.
	 */
	public static String langify(final String serializedName) {
		return Arrays.stream(serializedName.split("_"))
				.map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
				.collect(Collectors.joining(" "));
	}

	@Override
	protected void addTranslations() {
		// Same as
		// add(ExampleModItems.EXAMPLE_ITEM, "Example Item");
		addSimpleItem(ExampleModItems.EXAMPLE_ITEM);

		add(ExampleItem.EXAMPLE_TOOLTIP, "Example Tooltip");
	}

	private void add(final ItemLike itemLike, final String name) {
		add(itemLike.asItem(), name);
	}

	/**
	 * Helper for items that should be named their registry name using {@link #langify(String)} to convert
	 */
	private void addSimpleItem(final ItemLike itemLike) {
		add(itemLike, langify(BuiltInRegistries.ITEM.getKey(itemLike.asItem()).getPath()));
	}
}