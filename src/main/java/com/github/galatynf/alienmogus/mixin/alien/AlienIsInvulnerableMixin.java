package com.github.galatynf.alienmogus.mixin.alien;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class AlienIsInvulnerableMixin {
    @Inject(method="damage", at=@At("HEAD"), cancellable = true)
    private void cancelDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(MyComponents.ROLE.get(this).getRole().equals(EnumRole.ALIEN)) {
            cir.cancel();
            return;
        }
        Entity atker = source.getAttacker();

        if(atker != null) {
            if(MyComponents.ROLE.get(atker).getRole().equals(EnumRole.ALIEN))
                if(MyComponents.ROLE.get(this).getInvincibilityCooldown() > 0) {
                    cir.cancel();
                }
            else {
                MyComponents.ROLE.get(this).resetInvincibilityCooldown();
            }
        }
    }
}
