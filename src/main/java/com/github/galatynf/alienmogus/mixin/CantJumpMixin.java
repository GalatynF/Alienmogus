package com.github.galatynf.alienmogus.mixin;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class CantJumpMixin {
    @Shadow public abstract boolean isCreative();

    @Inject(at=@At("HEAD"), method = "jump", cancellable = true)
    private void preventJumping(CallbackInfo ci) {
        if(!this.isCreative()) {
            ci.cancel();
        }
    }
}
