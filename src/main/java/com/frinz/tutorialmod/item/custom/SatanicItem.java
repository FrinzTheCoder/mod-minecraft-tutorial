package com.frinz.tutorialmod.item.custom;

import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SatanicItem extends Item {
    public SatanicItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){
            player.setHealth(4);
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,400,4));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,400,4));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,400,4));
            player.addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SPEED,400,1)));
            player.getCooldowns().addCooldown(this,20);

            Vec3 currentPos = player.position();

            LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            lightningBolt.moveTo(currentPos);
            level.addFreshEntity(lightningBolt);

            Horse horse = new Horse(EntityType.HORSE,level);
            horse.moveTo(currentPos);
            horse.setTamed(true);
            horse.equipSaddle(null);
            level.addFreshEntity(horse);
            player.startRiding(horse);

            player.sendSystemMessage(Component.literal("Behold! The Power Of Satan!"));
        }

        return super.use(level, player, interactionHand);
    }
}
