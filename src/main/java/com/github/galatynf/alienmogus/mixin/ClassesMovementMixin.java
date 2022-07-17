package com.github.galatynf.alienmogus.mixin;

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
public abstract class ClassesMovementMixin extends LivingEntity {
    protected ClassesMovementMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method="tick", at = @At("TAIL"))
    private void speedEffectOnCrawl(CallbackInfo ci) {
        EnumRole myRole = MyComponents.ROLE.get(this).getRole();
        if(!world.isClient()) {
            if (myRole.equals(EnumRole.ALIEN)) {
                if(this.isInSwimmingPose()) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 6));
                }
                else if(this.isSprinting()) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 2));
                }
                else {
                    // The alien is slower when walking
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1, 2));
                }
            }
            else if (myRole.equals(EnumRole.IMPOSTOR)) {
                if(this.isInSwimmingPose()) {
                    // The impostor is venting
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 3));
                }
            }

        }
    }
}
