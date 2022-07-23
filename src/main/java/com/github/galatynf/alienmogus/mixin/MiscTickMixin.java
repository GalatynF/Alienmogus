package com.github.galatynf.alienmogus.mixin;

import com.github.galatynf.alienmogus.component.MyComponents;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class MiscTickMixin {
    @Inject(method="tick", at=@At("HEAD"))
    private void miscTickOperations(CallbackInfo ci) {
        MyComponents.ROLE.get(this).decrementInvincibilityCooldown();
    }
}
