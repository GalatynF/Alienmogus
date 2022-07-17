package com.github.galatynf.alienmogus.mixin.alien;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AlienCantUseBlocksMixin {

    @Shadow protected abstract Block asBlock();

    @Inject(method = "onUse", at=@At("HEAD"), cancellable = true)
    private void cancelAlienBlockUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if(MyComponents.ROLE.get(player).getRole().equals(EnumRole.ALIEN)
                && this.asBlock().equals(Blocks.CHEST)) {
            cir.setReturnValue(ActionResult.PASS);
        }
    }
}
