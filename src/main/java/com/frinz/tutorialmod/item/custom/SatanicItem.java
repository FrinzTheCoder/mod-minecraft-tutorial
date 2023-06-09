package com.frinz.tutorialmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SatanicItem extends Item {
    public SatanicItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){
            Vec3 currentPos = player.position();

            addEffectToPlayer(player);
            spawnLightningBoltOnPlayer(player,level,currentPos);
            summonHorseAndRiddenByPlayer(player,level,currentPos);

            player.sendSystemMessage(Component.literal("Behold! The Power Of Satan!"));
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            components.add(Component.literal("Right click to activate ").withStyle(ChatFormatting.WHITE).
                    append("SATAN POWER").withStyle(ChatFormatting.RED));
        }
        else{
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    private void addEffectToPlayer(Player player){
        player.setHealth(4);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,400,4));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,400,4));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,400,4));
        player.addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SPEED,400,1)));
        player.getCooldowns().addCooldown(this,20);
    }

    private void spawnLightningBoltOnPlayer(Player player, Level level, Vec3 currentPos){
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.moveTo(currentPos);
        level.addFreshEntity(lightningBolt);
    }

    private void summonHorseAndRiddenByPlayer(Player player, Level level ,Vec3 currentPos){
        Horse horse = new Horse(EntityType.HORSE,level);
        horse.moveTo(currentPos);
        horse.setTamed(true);
        horse.equipSaddle(null);
        level.addFreshEntity(horse);
        player.startRiding(horse);
    }
}
