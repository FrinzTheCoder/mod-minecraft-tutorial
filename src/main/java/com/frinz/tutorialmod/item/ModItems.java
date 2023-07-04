package com.frinz.tutorialmod.item;

import com.frinz.tutorialmod.TutorialMod;
import com.frinz.tutorialmod.item.custom.SatanicItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register(
            "zircon", () -> new Item(new Item.Properties()
                    .fireResistant()
                    .tab(ModCreativeModeTab.TUTORIAL_TAB))
    );
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register(
            "raw_zircon", () -> new Item(new Item.Properties()
                    .fireResistant()
                    .tab(ModCreativeModeTab.TUTORIAL_TAB))
    );
    public static final RegistryObject<Item> SATANIC = ITEMS.register(
            "satanic", () -> new SatanicItem(new Item.Properties()
                    .fireResistant()
                    .stacksTo(1)
                    .durability(1)
                    .tab(ModCreativeModeTab.TUTORIAL_TAB))
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
