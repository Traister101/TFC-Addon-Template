package com.example.examplemod;

import com.example.examplemod.providers.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ExampleMod.MOD_ID)
public final class DataGenerators {

	@SubscribeEvent
	private static void gatherData(final GatherDataEvent event) {
		final var generator = event.getGenerator();
		final var lookupProvider = event.getLookupProvider();
		final var existingFileHelper = event.getExistingFileHelper();
		final var packOutput = generator.getPackOutput();

		generator.addProvider(event.includeServer(), new BuiltInItemSizes(packOutput, lookupProvider));
		generator.addProvider(event.includeServer(), new BuiltInRecipes(packOutput, lookupProvider));

		generator.addProvider(event.includeClient(), new BuiltInLanguage(packOutput));
		generator.addProvider(event.includeClient(), new BuiltInItemModels(packOutput, existingFileHelper));
	}
}