package fr.romax.medievalcom.common.registry;

import java.util.ArrayList;

import fr.romax.medievalcom.MedievalCommunications;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = MedievalCommunications.MODID)
public class ItemsRegistry {

	public static final ArrayList<Item> ITEMS = new ArrayList<>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ITEMS.addAll(BlocksRegistry.BLOCKS.values());
		ITEMS.forEach(event.getRegistry()::register);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ITEMS.forEach(item -> {
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		});
	}

	public static void register(Item item, String name) {
		item.setRegistryName(new ResourceLocation(MedievalCommunications.MODID, name));
		item.setUnlocalizedName(name);
		item.setCreativeTab(MedievalCommunications.TAB);
		ITEMS.add(item);
	}
	
}