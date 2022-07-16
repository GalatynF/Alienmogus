package com.github.galatynf.alienmogus.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class TeleportToIronBlockMixin {
    private double euclideanDistance(BlockPos pos1, Position pos2) {
        return Math.sqrt(
                Math.pow(pos1.getX()+0.5-pos2.getX(), 2) +
                Math.pow(pos1.getY()+0.5-(pos2.getY()+2), 2) +
                Math.pow(pos1.getZ()+0.5-pos2.getZ(), 2));
    }


    @Inject(at=@At("HEAD"), method="onUse")
    private void rightClickToTeleport(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if(state.getBlock() == Blocks.IRON_BLOCK &&
            euclideanDistance(pos, player.getPos()) <= 2 &&
            !player.isInSwimmingPose()) {
            player.setPose(EntityPose.SWIMMING);
            player.teleport(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5);
            // Playsound metal step
        }
    }
}
