package com.frinz.tutorialmod.event;

import com.frinz.tutorialmod.TutorialMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void testEvent(LivingHurtEvent livingHurtEvent){
            Entity entity = livingHurtEvent.getEntity();
            if(entity instanceof Llama){
                if(livingHurtEvent.getSource().getEntity() instanceof Player player){
                    entity.setInvulnerable(true);
                    entity.setCustomName(Component.literal("LOL"));
                }
            }
        }

        @SubscribeEvent
        public static void boatRideRestrictionEvent(EntityMountEvent entityMountEvent){
            if(entityMountEvent.getEntityBeingMounted() instanceof Boat boat){
                boat.dismountTo(entityMountEvent);
            }
        }
    }
}
