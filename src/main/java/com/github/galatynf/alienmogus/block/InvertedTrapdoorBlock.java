package com.github.galatynf.alienmogus.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class InvertedTrapdoorBlock extends TrapdoorBlock {
    public InvertedTrapdoorBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(5f, 5f).sounds(BlockSoundGroup.METAL).nonOpaque());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction direction = ctx.getSide();
        if (!ctx.canReplaceExisting() && direction.getAxis().isHorizontal()) {
            blockState = (BlockState)((BlockState)blockState.with(FACING, direction)).with(HALF, ctx.getHitPos().y - (double)ctx.getBlockPos().getY() > 0.5 ? BlockHalf.TOP : BlockHalf.BOTTOM);
        } else {
            blockState = (BlockState)((BlockState)blockState.with(FACING, ctx.getPlayerFacing().getOpposite())).with(HALF, direction == Direction.UP ? BlockHalf.BOTTOM : BlockHalf.TOP);
        }

        if (ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos())) {
            blockState = (BlockState)((BlockState)blockState.with(OPEN, false)).with(POWERED, true);
        }
        else {
            blockState = (BlockState)((BlockState)blockState.with(OPEN, true));
        }

        return (BlockState)blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean bl = world.isReceivingRedstonePower(pos);
            if (bl != (Boolean)state.get(POWERED)) {
                if ((Boolean)state.get(OPEN) == bl) {
                    state = (BlockState)state.with(OPEN, !bl);
                    this.playToggleSound((PlayerEntity)null, world, pos, bl);
                }

                world.setBlockState(pos, (BlockState)state.with(POWERED, bl), 2);
                if ((Boolean)state.get(WATERLOGGED)) {
                    world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
                }
            }

        }
    }
}
