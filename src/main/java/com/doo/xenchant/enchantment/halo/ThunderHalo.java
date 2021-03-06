package com.doo.xenchant.enchantment.halo;

import com.doo.xenchant.Enchant;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;

import java.util.List;

/**
 * 雷霆光环
 */
public class ThunderHalo extends HaloEnchantment {

    public static final String NAME = "thunder";

    public ThunderHalo() {
        super(NAME, false);
    }

    @Override
    public boolean isTreasure() {
        return Enchant.option.thunderHaloIsTreasure;
    }

    @Override
    protected boolean needTick() {
        return Enchant.option.thunderHalo;
    }

    @Override
    public void onTarget(PlayerEntity player, Integer level, List<LivingEntity> targets) {
        targets.forEach(e -> {
            if (player.getRandom().nextInt(100) < Enchant.option.thunderHaloStruckChance) {
                e.onStruckByLightning((ServerWorld) player.world, new LightningEntity(EntityType.LIGHTNING_BOLT, e.world));
                e.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
                e.setAttacker(player);
            }
        });
    }
}
