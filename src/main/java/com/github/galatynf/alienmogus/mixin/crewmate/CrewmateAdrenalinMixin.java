package com.github.galatynf.alienmogus.mixin.crewmate;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class CrewmateAdrenalinMixin extends LivingEntity {
    @Shadow public abstract boolean isCreative();

    protected CrewmateAdrenalinMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at=@At("HEAD"))
    private void getFasterOnHit(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(!this.world.isClient()
            && !source.equals(DamageSource.DROWN)
            && MyComponents.ROLE.get(this).getRole().equals(EnumRole.CREWMATE)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 3));
        }
    }
}
