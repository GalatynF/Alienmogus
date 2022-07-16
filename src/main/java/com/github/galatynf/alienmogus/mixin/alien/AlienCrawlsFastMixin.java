package com.github.galatynf.alienmogus.mixin.alien;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class AlienCrawlsFastMixin extends LivingEntity {
    protected AlienCrawlsFastMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method="tick", at = @At("TAIL"))
    private void speedEffectOnCrawl(CallbackInfo ci) {
        if(!world.isClient()
                && this.isInSwimmingPose()) {
            if(MyComponents.ROLE.get(this).getRole().equals(EnumRole.ALIEN)) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 6));
            }
            else if(MyComponents.ROLE.get(this).getRole().equals(EnumRole.IMPOSTOR)) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 3));
            }
        }
    }
}
