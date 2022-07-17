package com.github.galatynf.alienmogus.mixin.alien;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class AlienIsInvulnerableMixin {
    @Inject(method="damage", at=@At("HEAD"))
    private void cancelDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(MyComponents.ROLE.get(this).getRole().equals(EnumRole.ALIEN)) {
            cir.cancel();
        }
    }
}
